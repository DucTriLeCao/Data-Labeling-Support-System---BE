package project.dlss.service;

public interface ActivityLogService {

    void log(Long userId, String action, String entityType, Long entityId);
}
