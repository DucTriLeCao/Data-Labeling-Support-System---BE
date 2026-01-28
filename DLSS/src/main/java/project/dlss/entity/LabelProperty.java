package project.dlss.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "label_properties")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LabelProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "label_property_id")
    private Long labelPropertyId;

    @Column(name = "property_name", nullable = false, length = 100)
    private String propertyName;

    @Column(name = "property_category", length = 50)
    private String propertyCategory;

    @ManyToOne
    @JoinColumn(name = "label_id", nullable = false)
    private Label label;
}
