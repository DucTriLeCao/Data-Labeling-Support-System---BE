package project.dlss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.dlss.entity.DataItemAssignment;

import java.util.List;

@Repository
public interface DataItemAssignmentRepository
        extends JpaRepository<DataItemAssignment, Long> {

    List<DataItemAssignment> findByUser_IdOrderByAssignedAtDesc(Long userId);

    List<DataItemAssignment> findByUser_IdAndStatus(Long userId, String status);

    List<DataItemAssignment> findByDataItem_Id(Long dataItemId);

    List<DataItemAssignment> findByStatus(String status);
}