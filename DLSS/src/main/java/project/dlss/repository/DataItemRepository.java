package project.dlss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.dlss.entity.DataItem;

import java.util.List;

public interface DataItemRepository extends JpaRepository<DataItem, Long> {

    List<DataItem> findByDatasetId(Long datasetId);

}