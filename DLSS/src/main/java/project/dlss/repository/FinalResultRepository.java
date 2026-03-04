package project.dlss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.dlss.entity.FinalResult;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinalResultRepository extends JpaRepository<FinalResult, Long> {

    Optional<FinalResult> findByDataItem_Id(Long dataItemId);

    List<FinalResult> findByAnnotation_DataItem_Dataset_Project_Id(Long projectId);

    List<FinalResult> findByAnnotation_DataItem_Dataset_Id(Long datasetId);
}
