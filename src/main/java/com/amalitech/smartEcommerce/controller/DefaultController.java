package com.amalitech.smartEcommerce.controller;

import com.amalitech.smartEcommerce.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Home")
public class DefaultController {

    @GetMapping("/")
    public ApiResponse<String> home() {
        return ApiResponse.success("API is running");
    }

}
