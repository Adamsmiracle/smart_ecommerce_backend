package com.amalitech.smartEcommerce.domain.user.controller;

import com.amalitech.smartEcommerce.common.dto.PageResponse;
import com.amalitech.smartEcommerce.common.response.ApiResponse;
import com.amalitech.smartEcommerce.domain.user.dto.CreateUserRequest;
import com.amalitech.smartEcommerce.domain.user.dto.UserResponse;
import com.amalitech.smartEcommerce.domain.user.entity.AppUser;
import com.amalitech.smartEcommerce.domain.user.mappers.UserMapper;
import com.amalitech.smartEcommerce.domain.user.service.AppUserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final AppUserService userService;

    public UserController(AppUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Create user")
    public ResponseEntity<ApiResponse<UserResponse>> create(@RequestBody CreateUserRequest u) {
        AppUser createdUser = userService.create(UserMapper.toUserEntity(u));
        UserResponse response = UserMapper.toUserResponse(createdUser);
        return ResponseEntity.ok(ApiResponse.success("Created", response));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    public ResponseEntity<ApiResponse<UserResponse>> getById(@PathVariable("id") UUID id) {
        UserResponse userResponse = UserMapper.toUserResponse(userService.getById(id));
        return ResponseEntity.ok(ApiResponse.success(userResponse));
    }

    @GetMapping
    @Operation(summary = "List users (paged)")
    public ResponseEntity<ApiResponse<PageResponse<UserResponse>>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<AppUser> userPage = userService.listPaged(page, size);
        PageResponse<UserResponse> response = PageResponse.from(userPage, UserMapper::toUserResponse);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
