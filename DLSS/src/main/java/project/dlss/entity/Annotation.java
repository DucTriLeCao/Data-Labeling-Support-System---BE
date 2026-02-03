package project.dlss.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "annotations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Annotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "data_item_id", nullable = false)
    private DataItem dataItem;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "label_value", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String labelValue; // JSON stored as text

    @Column(name = "status", length = 20)
    private String status = "submitted";

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "annotation")
    private List<Review> reviews;

    @OneToMany(mappedBy = "annotation")
    private List<FinalResult> finalResults;

    @OneToMany(mappedBy = "annotation")
    private List<ExportItem> exportItems;
}
