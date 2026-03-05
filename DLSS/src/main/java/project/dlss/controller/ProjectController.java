package project.dlss.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.dlss.dto.CreateProjectRequest;
import project.dlss.dto.ProjectDTO;
import project.dlss.dto.ProjectOverviewDTO;
import project.dlss.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ProjectDTO createProject(@RequestBody CreateProjectRequest request) {
        return projectService.createProject(request);
    }

    @GetMapping
    public List<ProjectDTO> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ProjectDTO getProject(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @GetMapping("/{id}/overview")
    public ProjectOverviewDTO getOverview(@PathVariable Long id) {
        return projectService.getProjectOverview(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}