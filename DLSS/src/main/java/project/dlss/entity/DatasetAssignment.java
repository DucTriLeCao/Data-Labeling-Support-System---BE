package project.dlss.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "dataset_assignments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DatasetAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dataset_id", nullable = false)
    private Dataset dataset;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 50)
    private AssignmentRole role;

    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;

    public enum AssignmentRole {
        annotator,
        reviewer
    }
}
