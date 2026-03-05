package project.dlss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.dlss.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}