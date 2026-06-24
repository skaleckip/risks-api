package org.norman.system.service;

import org.norman.system.dto.SystemVersionDto;
import org.norman.system.repository.SystemVersionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SystemVersionService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final SystemVersionRepository systemVersionRepository;

    public SystemVersionService(SystemVersionRepository systemVersionRepository) {
        this.systemVersionRepository = systemVersionRepository;
    }

    @Transactional(readOnly = true)
    public List<SystemVersionDto> listAllSystemVersions() {
        logger.trace("listAllSystemVersions");
        return SystemVersionDto.fromList(systemVersionRepository.findAll());
    }
}
