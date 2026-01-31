package project.dlss.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "export_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ExportItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "export_job_id", nullable = false)
    private ExportJob exportJob;

    @ManyToOne
    @JoinColumn(name = "data_item_id", nullable = false)
    private DataItem dataItem;

    @ManyToOne
    @JoinColumn(name = "final_result_id", nullable = false)
    private FinalResult finalResult;
}
