package project.dlss.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 100)
    private String username;

    @Column(name = "email", unique = true, length = 150)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 50)
    private Role role;

    @Column(name = "status", length = 20)
    private String status = "active";

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    private List<ProjectMember> projectMembers;

    @OneToMany(mappedBy = "user")
    private List<DatasetAssignment> datasetAssignments;

    @OneToMany(mappedBy = "user")
    private List<DataItemAssignment> dataItemAssignments;

    @OneToMany(mappedBy = "user")
    private List<Annotation> annotations;

    @OneToMany(mappedBy = "reviewer")
    private List<Review> reviews;

    @OneToMany(mappedBy = "decidedBy")
    private List<FinalResult> finalResults;

    @OneToMany(mappedBy = "createdBy")
    private List<ExportJob> exportJobs;

    @OneToMany(mappedBy = "user")
    private List<ActivityLog> activityLogs;

    public enum Role {
        admin,
        manager,
        annotator,
        reviewer
    }
}
