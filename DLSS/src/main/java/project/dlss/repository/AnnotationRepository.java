package project.dlss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.dlss.entity.Annotation;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnnotationRepository
        extends JpaRepository<Annotation, Long> {

    List<Annotation> findByDataItem_Id(Long dataItemId);

    List<Annotation> findByUser_Id(Long userId);

    Optional<Annotation> findByDataItem_IdAndUser_Id(Long dataItemId, Long userId);

    List<Annotation> findByStatus(String status);
}