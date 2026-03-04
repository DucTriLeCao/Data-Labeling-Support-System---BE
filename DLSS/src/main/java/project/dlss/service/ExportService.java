package project.dlss.service;

import project.dlss.dto.ExportRequest;

import java.util.List;
import java.util.Map;

public interface ExportService {

    List<Map<String, Object>> exportApprovedData(Long projectId, Long datasetId, String format);
}
