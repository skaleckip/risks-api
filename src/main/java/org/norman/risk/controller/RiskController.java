package org.norman.risk.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.norman.risk.dto.RiskWideDto;
import org.norman.risk.service.RiskService;
import org.norman.shared.dto.PageDto;
import org.norman.shared.controller.BadRequestException;
import org.norman.shared.controller.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "api")
@SecurityRequirement(name = "Norman")
public class RiskController {
    private final Logger logger = LoggerFactory.getLogger(RiskController.class);
    private final RiskService service;

    public RiskController(RiskService service) {
        this.service = service;
    }

    @Operation(summary = "Get risk by id")
    @GetMapping(value = "risks/{id}")
    public RiskWideDto getRiskWideById(@PathVariable UUID id) {
        logger.trace("getRiskById: id={}", id);
        return service.findRiskWideByIdOptional(id)
                .orElseThrow(NotFoundException::new);
    }

    @Operation(summary = "Find all the risks by system version paged")
    @GetMapping(value = "system-versions/{systemVersionId}/risks")
    public PageDto<RiskWideDto> pageRisksWideBySystemVersionId(
            @PathVariable UUID systemVersionId,
            @RequestParam(value = "pageNumber") int pageNumber,
            @RequestParam(value = "pageSize") int pageSize) {
        logger.trace("pageRisksWideBySystemVersionId: systemVersionId={}, pageNumber={}, pageSize={}",
                systemVersionId, pageNumber, pageSize);

        if (systemVersionId == null) {
            logger.warn("pageRisksWideBySystemVersionId.systemVersionId: null");
            throw new BadRequestException();
        }

        if (pageNumber < 0) {
            logger.warn("pageRisksWideBySystemVersionId.pageNumber: negative");
            throw new BadRequestException();
        }

        if (pageSize < 1) {
            logger.warn("pageRisksWideBySystemVersionId.pageSize: zero or negative");
            throw new BadRequestException();
        }

        return service.pageRisksBySystemVersionId(systemVersionId, pageNumber, pageSize);
    }

    @Operation(summary = "Find all the risks by owner paged")
    @GetMapping(value = "/risks-by-owner/{ownerUsername}")
    public PageDto<RiskWideDto> pageRisksWideByOwnerUsername(
            @PathVariable String ownerUsername,
            @RequestParam(value = "pageNumber") int pageNumber,
            @RequestParam(value = "pageSize") int pageSize) {
        logger.trace("pageRisksWideByOwnerUsername: pageRisksWideByOwnerUsername={}, pageNumber={}, pageSize={}",
                ownerUsername, pageNumber, pageSize);

        if (ownerUsername == null || ownerUsername.isBlank()) {
            logger.warn("pageRisksWideByOwnerUsername.ownerUsername: null or blank");
            throw new BadRequestException();
        }

        if (pageNumber < 0) {
            logger.warn("pageRisksWideByOwnerUsername.pageNumber: negative");
            throw new BadRequestException();
        }

        if (pageSize < 1) {
            logger.warn("pageRisksWideByOwnerUsername.pageSize: zero or negative");
            throw new BadRequestException();
        }

        return service.pageRisksByOwnerUsername(ownerUsername, pageNumber, pageSize);
    }
}