package project.dlss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.dlss.entity.DataItem;

import java.util.List;

@Repository
public interface DataItemRepository extends JpaRepository<DataItem, Long> {

    List<DataItem> findByDatasetId(Long datasetId);

    List<DataItem> findByStatus(String status);
}