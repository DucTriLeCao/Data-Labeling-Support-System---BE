package project.dlss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dlss.entity.Project;
import project.dlss.repository.ProjectRepository;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // CREATE
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    // READ ALL
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // READ BY ID
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    // UPDATE
    public Project updateProject(Long id, Project newProject) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setName(newProject.getName());
        project.setDescription(newProject.getDescription());
        project.setStatus(newProject.getStatus());

        return projectRepository.save(project);
    }

    // DELETE
    public void deleteProject(Long id) {

        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Project not found");
        }

        projectRepository.deleteById(id);
    }
}