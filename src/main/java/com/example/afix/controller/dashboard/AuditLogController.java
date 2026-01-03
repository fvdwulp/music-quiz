package com.example.afix.controller.dashboard;


import com.example.afix.model.AuditLog;
import com.example.afix.service.audit_log.AuditService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/logs")
public class AuditLogController {

    private final AuditService auditService;

    public AuditLogController(AuditService auditService){
        this.auditService = auditService;
    }

    @GetMapping("")
    public String logs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            Model model
    ) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.DESC, "createdAt")
        );

        Page<AuditLog> logPage = auditService.findPage(pageable);
        model.addAttribute("logPage", logPage);
        model.addAttribute("logs", logPage.getContent());
        model.addAttribute("pageTitle", "Logs");

        return "dashboard/logs/list";
    }

}
