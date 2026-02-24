package project.dlss.service;

package service;

import model.User;
import repository.ProjectRepository;

public interface ProjectService {

    private ProjectRepository projectRepository = new ProjectRepository();
    private ActivityLogService logService = new ActivityLogService();

    public void createProject(User user,
                              String name,
                              String description) {
        if (!user.getRole().equals("admin")
                && !user.getRole().equals("manager")) {

            System.out.println("Permission denied!");
            return;
        }

        long projectId = projectRepository.save(name, description);

        if (projectId != -1) {
            logService.log(
                    user.getId(),
                    "CREATE_PROJECT",
                    "project",
                    projectId
            );

            System.out.println("Project created successfully!");
        }
    }

    public void overview(long projectId) {
        projectRepository.overview(projectId);
    }
}
