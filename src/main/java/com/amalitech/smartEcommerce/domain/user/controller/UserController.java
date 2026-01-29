package com.amalitech.smartEcommerce.domain.user.controller;

import com.amalitech.smartEcommerce.common.dto.PageResponse;
import com.amalitech.smartEcommerce.common.response.ApiResponse;
import com.amalitech.smartEcommerce.domain.user.dto.CreateUserRequest;
import com.amalitech.smartEcommerce.domain.user.dto.UserResponse;
import com.amalitech.smartEcommerce.domain.user.entity.AppUser;
import com.amalitech.smartEcommerce.domain.user.mappers.UserMapper;
import com.amalitech.smartEcommerce.domain.user.service.AppUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "Operations related to application users")
public class UserController {

    private final AppUserService userService;

    public UserController(AppUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Create user", description = "Creates a new user and returns the created user payload")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User created successfully"),
//            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload")
    })
    public ResponseEntity<ApiResponse<UserResponse>> create(@Valid @RequestBody CreateUserRequest u) {
        AppUser createdUser = userService.create(UserMapper.toUserEntity(u));
        UserResponse response = UserMapper.toUserResponse(createdUser);
        return ResponseEntity.ok(ApiResponse.success("Created", response));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id", description = "Retrieves a user by their unique identifier")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<ApiResponse<UserResponse>> getById(
            @Parameter(description = "User ID", required = true) @PathVariable("id") UUID id) {
        UserResponse userResponse = UserMapper.toUserResponse(userService.getById(id));
        return ResponseEntity.ok(ApiResponse.success(userResponse));
    }

    @GetMapping
    @Operation(summary = "List users (paged)", description = "Returns a paginated list of users")
    public ResponseEntity<ApiResponse<PageResponse<UserResponse>>> list(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size) {
        Page<AppUser> userPage = userService.listPaged(page, size);
        PageResponse<UserResponse> response = PageResponse.from(userPage, UserMapper::toUserResponse);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", description = "Deletes a user and related payment methods")
    public ResponseEntity<ApiResponse<Void>> delete(@Parameter(description = "User ID", required = true)
                                                    @PathVariable("id") UUID id) {
        userService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("User deleted", null));
    }
}
