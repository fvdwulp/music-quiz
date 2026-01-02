package com.example.afix.controller.dashboard;


import com.example.afix.model.AuditLog;
import com.example.afix.service.audit_log.AuditService;
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
    public String logs(Model model) {
        List<AuditLog> allLogs = auditService.findAll();
        model.addAttribute("logs", allLogs);
        model.addAttribute("pageTitle", "Logs");

        return "dashboard/logs/list";
    }

}
