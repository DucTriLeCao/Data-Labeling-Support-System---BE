package project.dlss.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dataset_id", nullable = false)
    private Dataset dataset;

    @Column(name = "content", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private DataItemStatus status;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "dataItem")
    private List<DataItemAssignment> dataItemAssignments;

    @OneToMany(mappedBy = "dataItem",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Annotation> annotations;

    @OneToMany(mappedBy = "dataItem")
    private List<FinalResult> finalResults;

    @OneToMany(mappedBy = "dataItem")
    private List<ExportItem> exportItems;

    public enum DataItemStatus {
        NEW,
        ASSIGNED,
        ANNOTATED,
        REVIEWED,
        FINAL
    }
}
