package project.dlss.service;

import project.dlss.entity.Label;

import java.util.List;

public interface LabelService {

    Label createLabel(Long projectId, String name, Long parentId);

    List<Label> getLabelsByProject(Long projectId);

    boolean validateLabel(Long projectId, String labelName);

    void deleteLabel(Long labelId);
}