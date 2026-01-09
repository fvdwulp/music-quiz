package com.example.music_quiz.service.audit_log;

import com.example.music_quiz.audit.AuditAction;
import com.example.music_quiz.domain.AuditLog;
import com.example.music_quiz.repository.AuditLogRepository;
import com.example.music_quiz.service.AbstractUserAwareService;
import com.example.music_quiz.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@PreAuthorize("hasRole('ADMIN')")
public class AuditService extends AbstractUserAwareService {

    private final AuditLogRepository repository;

    public AuditService(AuditLogRepository repository,
                        UserService userService) {
        super(userService);
        this.repository = repository;
    }

    public Page<AuditLog> findPage(Pageable page) {
        return repository.findAll(page);
    }

    public void log(AuditAction action,
                    String entityType,
                    Integer entityId,
                    String details) {

        AuditLog log = new AuditLog();

        try {
            log.setUserId(getCurrentUser().getId());
            log.setUsername(getCurrentUser().getUsername());
        } catch (Exception ignored) {
            // anonymous / system action
        }

        log.setAction(action.name());
        log.setEntityType(entityType);
        log.setEntityId(entityId);
        log.setDetails(details);

        repository.save(log);
    }
}

