package project.dlss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.dlss.entity.Project;
import project.dlss.dto.ProjectOverviewDTO;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("""
            SELECT new project.dlss.dto.ProjectOverviewDTO(
                p.name,
                COUNT(DISTINCT d.id),
                COUNT(di.id)
            )
            FROM Project p
            LEFT JOIN p.datasets d
            LEFT JOIN d.dataItems di
            WHERE p.id = :projectId
            GROUP BY p.name
           """)
    ProjectOverviewDTO getProjectOverview(Long projectId);

}