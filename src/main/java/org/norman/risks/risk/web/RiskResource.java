package org.norman.risks.risk.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.norman.risks.risk.dto.RiskDto;
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
public class RiskResource {
    private final Logger logger = LoggerFactory.getLogger(RiskResource.class);
    private final RiskService service;

    public RiskResource(RiskService service) {
        this.service = service;
    }

    @Operation(
            summary = "Get risk",
            security = {@SecurityRequirement(name = "Norman")}
    )
    @GetMapping(value = "risks/{id}")
    public RiskDto getRiskById(@PathVariable UUID id) {
        logger.trace("getRiskById: id={}", id);
        return service.findRiskByIdOptional(id)
                .orElseThrow(NotFoundException::new);
    }

    @Operation(
            summary = "Get risks paged",
            security = {@SecurityRequirement(name = "Norman")}
    )
    @GetMapping(value = "risks")
    public PageOfDto<RiskDto> pageRisksByQuery(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "pageNumber") int pageNumber,
            @RequestParam(value = "pageSize") int pageSize) {
        logger.trace("pageRisksByQuery: name={}, pageNumber={}, pageSize={}",
                name, pageNumber, pageSize);

        if (pageNumber < 1) {
            logger.warn("pageRisksByQuery.pageNumber: zero or negative");
            throw new BadRequestException();
        }

        if (pageSize < 1) {
            logger.warn("pageRisksByQuery.pageSize: zero or negative");
            throw new BadRequestException();
        }

        if (name != null && !name.isEmpty()) {
            return service.pageRisksByNames(name, pageNumber, pageSize);
        }

        return service.pageAllRisks(pageNumber, pageSize);
    }
}