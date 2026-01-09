package com.example.music_quiz.audit;

import com.example.music_quiz.service.audit_log.AuditService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class AuditAspect {

    private final AuditService auditService;

    public AuditAspect(AuditService auditService) {
        this.auditService = auditService;
    }

    @AfterReturning(
            pointcut = "@annotation(audit)",
            returning = "result"
    )
    public void afterSuccess(JoinPoint joinPoint,
                             Audit audit,
                             Object result) {

        Integer entityId = resolveEntityId(joinPoint, audit, result);

        auditService.log(
                audit.action(),
                audit.entity(),
                entityId,
                buildDetails(joinPoint)
        );
    }

    private Integer resolveEntityId(JoinPoint jp,
                                    Audit audit,
                                    Object result) {

        if (result != null) {
            try {
                Method getId = result.getClass().getMethod("getId");
                Object id = getId.invoke(result);
                if (id instanceof Integer) {
                    return (Integer) id;
                }
            } catch (Exception ignored) {}
        }

        if (!audit.idParam().isBlank()) {
            MethodSignature sig = (MethodSignature) jp.getSignature();
            String[] names = sig.getParameterNames();
            Object[] args = jp.getArgs();

            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(audit.idParam())
                        && args[i] instanceof Integer) {
                    return (Integer) args[i];
                }
            }
        }

        return null;
    }

    private String buildDetails(JoinPoint jp) {
        return jp.getSignature().toShortString();
    }
}

