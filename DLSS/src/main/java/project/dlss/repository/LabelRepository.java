package project.dlss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.dlss.entity.Label;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {

    List<Label> findByProjectId(Long projectId);

    Optional<Label> findByNameAndProjectId(String name, Long projectId);

    List<Label> findByParentId(Long parentId);
}