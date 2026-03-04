package project.dlss.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.dlss.entity.ActivityLog;
import project.dlss.entity.User;
import project.dlss.repository.ActivityLogRepository;
import project.dlss.repository.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {

    private final ActivityLogRepository activityLogRepository;
    private final UserRepository userRepository;

    @Override
    public void log(Long userId, String action, String entityType, Long entityId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return;

        ActivityLog log = new ActivityLog();
        log.setUser(user);
        log.setAction(action);
        log.setEntityType(entityType);
        log.setEntityId(entityId);
        log.setCreatedAt(LocalDateTime.now());
        activityLogRepository.save(log);
    }
}
