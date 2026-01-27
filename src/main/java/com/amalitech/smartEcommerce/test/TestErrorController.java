package com.amalitech.smartEcommerce.test;

import com.amalitech.smartEcommerce.exception.ResourceNotFoundException;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("test")
@RestController
public class TestErrorController {
    @GetMapping("/test/error/notfound")
    public void notFound() {
        throw new ResourceNotFoundException("test-resource-not-found");
    }
}

