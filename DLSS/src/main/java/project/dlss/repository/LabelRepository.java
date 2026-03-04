package project.dlss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.dlss.entity.Label;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {

    List<Label> findByProject_Id(Long projectId);

    Optional<Label> findByNameAndProject_Id(String name, Long projectId);

    List<Label> findByParent_Id(Long parentId);
}