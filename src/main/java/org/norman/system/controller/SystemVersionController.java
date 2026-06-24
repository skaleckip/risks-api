package org.norman.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.norman.system.dto.SystemVersionDto;
import org.norman.system.service.SystemVersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "Norman")
public class SystemVersionController {
    private final Logger logger = LoggerFactory.getLogger(SystemVersionController.class);
    private final SystemVersionService systemVersionService;

    public SystemVersionController(SystemVersionService systemVersionService) {
        this.systemVersionService = systemVersionService;
    }

    @GetMapping("/system-versions")
    @Operation(summary = "List all the system versions")
    public List<SystemVersionDto> listAllSystemVersions() {
        logger.trace("listAllSystemVersions");
        return systemVersionService.listAllSystemVersions();
    }
}
