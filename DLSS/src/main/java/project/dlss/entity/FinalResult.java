package project.dlss.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "final_results")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FinalResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "data_item_id", nullable = false, unique = true)
    private DataItem dataItem;

    @ManyToOne
    @JoinColumn(name = "annotation_id", nullable = false)
    private Annotation annotation;

    @ManyToOne
    @JoinColumn(name = "decided_by", nullable = false)
    private User decidedBy;

    @Column(name = "decided_at")
    private LocalDateTime decidedAt;

    @OneToMany(mappedBy = "finalResult")
    private List<ExportItem> exportItems;
}
