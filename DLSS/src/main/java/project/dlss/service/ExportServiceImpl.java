package project.dlss.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.dlss.entity.FinalResult;
import project.dlss.repository.FinalResultRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExportServiceImpl implements ExportService {

    private final FinalResultRepository finalResultRepository;

    @Override
    public List<Map<String, Object>> exportApprovedData(Long projectId, Long datasetId, String format) {
        List<FinalResult> results;

        if (datasetId != null) {
            results = finalResultRepository.findByAnnotation_DataItem_Dataset_Id(datasetId);
        } else if (projectId != null) {
            results = finalResultRepository.findByAnnotation_DataItem_Dataset_Project_Id(projectId);
        } else {
            results = finalResultRepository.findAll();
        }

        return results.stream()
                .map(this::toExportMap)
                .collect(Collectors.toList());
    }

    private Map<String, Object> toExportMap(FinalResult fr) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("dataItemId", fr.getDataItem().getId());
        map.put("content", fr.getDataItem().getContent());
        map.put("labelValue", fr.getAnnotation().getLabelValue());
        map.put("annotationId", fr.getAnnotation().getId());
        map.put("decidedBy", fr.getDecidedBy().getId());
        map.put("decidedAt", fr.getDecidedAt());
        return map;
    }
}
