package org.norman.risks.risk.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.norman.risks.risk.dto.RiskWideDto;
import org.norman.risks.risk.svc.RiskService;
import org.norman.risks.shared.dto.PageOfDto;
import org.norman.risks.shared.web.BadRequestException;
import org.norman.risks.shared.web.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "api")
public class RiskController {
    private final Logger logger = LoggerFactory.getLogger(RiskController.class);
    private final RiskService service;

    public RiskController(RiskService service) {
        this.service = service;
    }

    @Operation(
            summary = "Get risk by id",
            security = {@SecurityRequirement(name = "Norman")}
    )
    @GetMapping(value = "risks/{id}")
    public RiskWideDto getRiskWideById(@PathVariable UUID id) {
        logger.trace("getRiskById: id={}", id);
        return service.findRiskWideByIdOptional(id)
                .orElseThrow(NotFoundException::new);
    }

    @Operation(
            summary = "Find all the risks paged",
            security = {@SecurityRequirement(name = "Norman")}
    )
    @GetMapping(value = "system-versions/{systemVersionId}/risks")
    public PageOfDto<RiskWideDto> pageRisksWideBySystemVersionId(
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
}