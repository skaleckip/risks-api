package org.norman.ui.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api")
public class UiController {
    @GetMapping("/ui/system-versions")
    public Boolean showSystemVersions() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication)) return false;
        return authentication
                .getAuthorities()
                .stream()
                .anyMatch(authority ->
                        Objects.equals(authority.getAuthority(), "ROLE_auditor"));
    }
}
