package project.dlss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.dlss.entity.Annotation;

import java.util.List;

@Repository
public interface AnnotationRepository
        extends JpaRepository<Annotation, Long> {

    List<Annotation> findByDataItemId(Long dataItemId);

    List<Annotation> findByUserId(Long userId);

    List<Annotation> findByStatus(String status);
}