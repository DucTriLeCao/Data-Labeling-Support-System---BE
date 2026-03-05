package project.dlss.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.dlss.dto.CreateProjectRequest;
import project.dlss.dto.ProjectDTO;
import project.dlss.dto.ProjectOverviewDTO;
import project.dlss.entity.Project;
import project.dlss.repository.ProjectRepository;
import project.dlss.service.ProjectService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public ProjectDTO createProject(CreateProjectRequest request) {

        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setCreatedAt(LocalDateTime.now());

        Project saved = projectRepository.save(project);

        return mapToDTO(saved);
    }

    @Override
    public List<ProjectDTO> getAllProjects() {

        return projectRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

    }

    @Override
    public ProjectDTO getProjectById(Long id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        return mapToDTO(project);

    }

    @Override
    public ProjectOverviewDTO getProjectOverview(Long id) {
        return projectRepository.getProjectOverview(id);
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    private ProjectDTO mapToDTO(Project project) {

        ProjectDTO dto = new ProjectDTO();

        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setStatus(project.getStatus());

        return dto;
    }
}