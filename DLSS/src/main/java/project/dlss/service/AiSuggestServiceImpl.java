package project.dlss.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.dlss.entity.DataItem;
import project.dlss.entity.Label;
import project.dlss.exception.ResourceNotFoundException;
import project.dlss.repository.DataItemRepository;
import project.dlss.repository.LabelRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AiSuggestServiceImpl implements AiSuggestService {

    private final DataItemRepository dataItemRepository;
    private final LabelRepository labelRepository;

    /**
     * (Optional) AI gợi ý nhãn.
     * Hiện tại: trả về danh sách labels của project chứa data item.
     * Có thể tích hợp model AI thực tế sau.
     */
    @Override
    public List<String> suggestLabel(Long dataItemId, String content) {
        DataItem item = dataItemRepository.findById(dataItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Data item not found"));

        Long projectId = item.getDataset().getProject().getId();
        List<Label> labels = labelRepository.findByProject_Id(projectId);

        List<String> suggestions = new ArrayList<>();
        for (Label label : labels) {
            if (label.getParent() == null) {
                suggestions.add(label.getName());
            }
        }
        return suggestions;
    }
}
