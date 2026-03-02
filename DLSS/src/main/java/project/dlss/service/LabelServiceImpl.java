package project.dlss.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.dlss.entity.Label;
import project.dlss.entity.Project;
import project.dlss.exception.BadRequestException;
import project.dlss.exception.ResourceNotFoundException;
import project.dlss.repository.LabelRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository;

    @Override
    public Label createLabel(Long projectId, String name, Long parentId) {

        labelRepository.findByNameAndProjectId(name, projectId)
                .ifPresent(l -> {
                    throw new BadRequestException("Label already exists in project");
                });

        Label label = new Label();

        Project project = new Project();
        project.setId(projectId);
        label.setProject(project);

        label.setName(name);

        if (parentId != null) {
            Label parent = labelRepository.findById(parentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Parent label not found"));
            if (parent.getProject() == null || !projectId.equals(parent.getProject().getId())) {
                throw new BadRequestException("Parent label does not belong to the same project");
            }
            label.setParent(parent);
        }

        return labelRepository.save(label);
    }

    @Override
    public List<Label> getLabelsByProject(Long projectId) {
        return labelRepository.findByProjectId(projectId);
    }

    @Override
    public boolean validateLabel(Long projectId, String labelName) {
        return labelRepository
                .findByNameAndProjectId(labelName, projectId)
                .isPresent();
    }

    @Override
    public void deleteLabel(Long labelId) {
        labelRepository.deleteById(labelId);
    }
}