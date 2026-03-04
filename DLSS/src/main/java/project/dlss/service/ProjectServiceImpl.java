package project.dlss.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.dlss.entity.Project;
import project.dlss.entity.User;
import project.dlss.repository.ProjectRepository;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ActivityLogService logService;

    @Override
    public void createProject(User user, String name, String description) {
        if (user.getRole() != User.Role.admin && user.getRole() != User.Role.manager) {
            System.out.println("Permission denied!");
            return;
        }

        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setStatus("active");

        Project saved = projectRepository.save(project);

        if (saved.getId() != null) {
            logService.log(user.getId(), "CREATE_PROJECT", "project", saved.getId());
            System.out.println("Project created successfully!");
        }
    }

    @Override
    public void overview(long projectId) {
        projectRepository.findById(projectId)
                .ifPresentOrElse(
                        p -> System.out.println("Project: " + p.getName() + " - " + p.getDescription()),
                        () -> System.out.println("Project not found")
                );
    }
}
