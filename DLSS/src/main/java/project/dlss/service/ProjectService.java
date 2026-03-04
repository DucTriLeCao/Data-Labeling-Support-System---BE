package project.dlss.service;

import project.dlss.entity.User;

public interface ProjectService {

    void createProject(User user, String name, String description);

    void overview(long projectId);
}
