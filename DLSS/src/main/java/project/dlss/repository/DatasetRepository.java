package project.dlss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.dlss.entity.Dataset;

import java.util.List;

public interface DatasetRepository extends JpaRepository<Dataset, Long> {

    List<Dataset> findByProjectId(Long projectId);

}