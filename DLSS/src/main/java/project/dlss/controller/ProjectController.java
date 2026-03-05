package project.dlss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.dlss.entity.Project;
import project.dlss.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public Project create(@RequestBody Project project){
        return projectService.createProject(project);
    }

    @GetMapping
    public List<Project> getAll(){
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getById(@PathVariable Long id){
        return projectService.getProjectById(id);
    }

    @PutMapping("/{id}")
    public Project update(@PathVariable Long id, @RequestBody Project project){
        return projectService.updateProject(id, project);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        projectService.deleteProject(id);
    }
}