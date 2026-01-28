package project.dlss.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "labels")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "label_id")
    private Long labelId;

    @Column(name = "label_name", nullable = false, length = 100)
    private String labelName;

    @Column(name = "label_type", length = 50)
    private String labelType;

    @Column(name = "color", length = 20)
    private String color;

    @Column(name = "guidelines", columnDefinition = "TEXT")
    private String guidelines;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @OneToMany(mappedBy = "label")
    private List<Result> results;

    @OneToMany(mappedBy = "label")
    private List<LabelProperty> properties;
}
