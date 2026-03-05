package project.dlss.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.dlss.dto.ActivityLogDTO;
import project.dlss.dto.CreateActivityLogRequest;
import project.dlss.entity.ActivityLog;
import project.dlss.entity.User;
import project.dlss.repository.ActivityLogRepository;
import project.dlss.repository.UserRepository;
import project.dlss.service.ActivityLogService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {

    private final ActivityLogRepository activityLogRepository;
    private final UserRepository userRepository;

    @Override
    public ActivityLogDTO createLog(CreateActivityLogRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        ActivityLog log = new ActivityLog();

        log.setUser(user);
        log.setAction(request.getAction());
        log.setEntityType(request.getEntityType());
        log.setEntityId(request.getEntityId());
        log.setCreatedAt(LocalDateTime.now());

        ActivityLog saved = activityLogRepository.save(log);

        return mapToDTO(saved);
    }

    @Override
    public List<ActivityLogDTO> getAllLogs() {

        return activityLogRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

    }

    private ActivityLogDTO mapToDTO(ActivityLog log) {

        ActivityLogDTO dto = new ActivityLogDTO();

        dto.setId(log.getId());
        dto.setUserId(log.getUser().getId());
        dto.setAction(log.getAction());
        dto.setEntityType(log.getEntityType());
        dto.setEntityId(log.getEntityId());
        dto.setCreatedAt(log.getCreatedAt());

        return dto;
    }
}