package org.norman.risks.user.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.norman.risks.user.dto.UserDto;
import org.norman.risks.user.svc.UsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/users")
    @Operation(
            summary = "List users",
            security = {@SecurityRequirement(name = "Norman")}
    )
    public List<UserDto> listUsers() {
        return usersService.listUsers();
    }
}
