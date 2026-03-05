package project.dlss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.dlss.entity.Label;

import java.util.List;

public interface LabelRepository extends JpaRepository<Label, Long> {

    List<Label> findByProjectId(Long projectId);

}