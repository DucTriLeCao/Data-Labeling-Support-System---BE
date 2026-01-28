package project.dlss.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "error_logs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ErrorLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "error_id")
    private Long errorId;

    @Column(name = "error_name", nullable = false, length = 100)
    private String errorName;

    @Column(name = "error_description", columnDefinition = "TEXT")
    private String errorDescription;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
