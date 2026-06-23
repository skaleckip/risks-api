package org.norman.metadata.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.norman.metadata.dto.RiskAreaDto;
import org.norman.metadata.service.MetaService;
import org.norman.shared.controller.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "api")
@SecurityRequirement(name = "Norman")
public class MetaController {
    private final Logger logger = LoggerFactory.getLogger(MetaController.class);
    private final MetaService service;

    public MetaController(MetaService service) {
        this.service = service;
    }

    @Operation(summary = "Get risks area by ID")
    @GetMapping(value = "risk-areas/id:{id}")
    public RiskAreaDto getRiskAreaById(@PathVariable UUID id) {
        logger.trace("getRiskAreaById: id={}", id);
        return service.findRiskAreaByIdOptional(id)
                .orElseThrow(NotFoundException::new);
    }

    @Operation(summary = "Get risks area by Code")
    @GetMapping(value = "risk-areas/code:{code}")
    public RiskAreaDto getRiskAreaByCode(@PathVariable String code) {
        logger.trace("getRiskAreaByCode: code={}", code);
        return service.findRiskAreaByCodeOptional(code)
                .orElseThrow(NotFoundException::new);
    }

    @Operation(summary = "List all risk area")
    @GetMapping(value = "risk-areas")
    public List<RiskAreaDto> listAllRiskAreas() {
        logger.trace("listAllRiskAreas");
        return service.listAllRiskAreas();
    }
}