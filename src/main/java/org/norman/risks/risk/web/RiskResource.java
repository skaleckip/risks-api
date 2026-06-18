package org.norman.risks.risk.web;

import org.norman.risks.risk.dto.RiskDto;
import org.norman.risks.risk.svc.RiskService;
import org.norman.risks.shared.dto.PageOfDto;
import org.norman.risks.shared.web.BadRequestException;
import org.norman.risks.shared.web.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "api")
public class RiskResource {
    private final Logger logger = LoggerFactory.getLogger(RiskResource.class);
    private final RiskService service;

    public RiskResource(RiskService service) {
        this.service = service;
    }

    @GetMapping(value = "risks/{id}")
    public RiskDto getRiskById(@PathVariable(value = "id") UUID id) {
        logger.trace("getRiskById: id={}", id);
        return service.findRiskByIdOptional(id)
                .orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = "risks")
    public PageOfDto<RiskDto> pageRisksByQuery(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "pageIndex") int pageIndex,
            @RequestParam(value = "pageSize") int pageSize) {
        logger.trace("pageRisksByQuery: name={}, pageIndex={}, pageSize={}",
                name, pageIndex, pageSize);
        
        if (pageIndex < 0) {
            logger.warn("pageRisksByQuery.pageIndex: negative");
            throw new BadRequestException();
        }
        
        if (pageSize < 1) {
            logger.warn("pageRisksByQuery.pageSize: zero or negative");
            throw new BadRequestException();
        }
        
        if (name != null && !name.isEmpty()) {
            return service.pageRisksByNames(name, pageIndex, pageSize);
        }
        
        return service.pageAllRisks(pageIndex, pageSize);
    }
}