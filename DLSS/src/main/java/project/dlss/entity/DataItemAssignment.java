package project.dlss.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "data_item_assignments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DataItemAssignment {
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

    @Column(name = "status", length = 20)
    private String status = "assigned";

    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;
}
