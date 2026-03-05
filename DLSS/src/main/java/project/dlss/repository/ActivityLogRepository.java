package project.dlss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.dlss.entity.ActivityLog;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
}