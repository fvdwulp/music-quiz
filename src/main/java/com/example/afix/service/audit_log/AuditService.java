package com.example.afix.service.audit_log;

import com.example.afix.audit.AuditAction;
import com.example.afix.model.AuditLog;
import com.example.afix.repository.AuditLogRepository;
import com.example.afix.service.AbstractUserAwareService;
import com.example.afix.service.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditService extends AbstractUserAwareService {

    private final AuditLogRepository repository;

    public AuditService(AuditLogRepository repository,
                        UserService userService) {
        super(userService);
        this.repository = repository;
    }

    public List<AuditLog> findAll() {
        return repository.findAll();
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

