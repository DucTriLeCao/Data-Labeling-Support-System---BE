package project.dlss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.dlss.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
