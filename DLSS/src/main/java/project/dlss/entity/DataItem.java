package project.dlss.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "data_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DataItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dataset_id", nullable = false)
    private Dataset dataset;

    @Column(name = "content", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String content;

    @Column(name = "status", length = 20)
    private String status = "new";

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "dataItem")
    private List<DataItemAssignment> dataItemAssignments;

    @OneToMany(mappedBy = "dataItem")
    private List<Annotation> annotations;

    @OneToMany(mappedBy = "dataItem")
    private List<FinalResult> finalResults;

    @OneToMany(mappedBy = "dataItem")
    private List<ExportItem> exportItems;
}
