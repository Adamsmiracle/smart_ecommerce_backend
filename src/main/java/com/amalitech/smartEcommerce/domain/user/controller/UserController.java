package com.amalitech.smartEcommerce.domain.user.controller;

import com.amalitech.smartEcommerce.common.response.ApiResponse;
import com.amalitech.smartEcommerce.domain.user.entity.AppUser;
import com.amalitech.smartEcommerce.domain.user.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final AppUserService userService;

    public UserController(AppUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AppUser>> create(@RequestBody AppUser u) {
        AppUser created = userService.create(u);
        return ResponseEntity.ok(ApiResponse.success("Created", created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AppUser>> getById(@PathVariable("id") java.util.UUID id) {
        AppUser u = userService.getById(id);
        return ResponseEntity.ok(ApiResponse.success(u));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AppUser>>> list(@RequestParam(defaultValue = "10") int limit,
                                                            @RequestParam(defaultValue = "0") int offset) {
        return ResponseEntity.ok(ApiResponse.success(userService.list(limit, offset)));
    }
}
