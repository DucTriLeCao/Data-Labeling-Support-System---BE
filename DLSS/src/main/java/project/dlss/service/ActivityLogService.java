package project.dlss.service;

import project.dlss.dto.ActivityLogDTO;
import project.dlss.dto.CreateActivityLogRequest;

import java.util.List;

public interface ActivityLogService {

    ActivityLogDTO createLog(CreateActivityLogRequest request);

    List<ActivityLogDTO> getAllLogs();

}