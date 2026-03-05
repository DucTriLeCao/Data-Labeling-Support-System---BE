package project.dlss.service;

import project.dlss.dto.CreateProjectRequest;
import project.dlss.dto.ProjectDTO;
import project.dlss.dto.ProjectOverviewDTO;

import java.util.List;

public interface ProjectService {

    ProjectDTO createProject(CreateProjectRequest request);

    List<ProjectDTO> getAllProjects();

    ProjectDTO getProjectById(Long id);

    ProjectOverviewDTO getProjectOverview(Long id);

    void deleteProject(Long id);

}