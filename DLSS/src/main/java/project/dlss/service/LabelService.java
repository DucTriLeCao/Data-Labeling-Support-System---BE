package project.dlss.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.dlss.dto.LabelRequestDTO;
import project.dlss.entity.Label;
import project.dlss.entity.Project;
import project.dlss.repository.LabelRepository;
import project.dlss.repository.ProjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabelService {

    private final LabelRepository labelRepository;
    private final ProjectRepository projectRepository;

    public Label create(LabelRequestDTO dto) {

        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Label label = new Label();
        label.setName(dto.getName());
        label.setProject(project);

        if(dto.getParentId() != null){
            Label parent = labelRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent label not found"));
            label.setParent(parent);
        }

        return labelRepository.save(label);
    }

    public List<Label> getLabelsByProject(Long projectId){
        return labelRepository.findByProjectId(projectId);
    }

    public void delete(Long id){
        labelRepository.deleteById(id);
    }

}