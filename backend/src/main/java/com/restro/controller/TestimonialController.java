package com.restro.controller;

import com.restro.dto.ApiResponse;
import com.restro.model.Testimonial;
import com.restro.service.TestimonialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testimonials")
public class TestimonialController {

    private final TestimonialService testimonialService;

    public TestimonialController(TestimonialService testimonialService) {
        this.testimonialService = testimonialService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Testimonial>>> getTestimonials() {
        List<Testimonial> testimonials = testimonialService.getApprovedTestimonials();
        return ResponseEntity.ok(ApiResponse.success(testimonials));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Testimonial>> addTestimonial(@RequestBody Testimonial testimonial) {
        Testimonial saved = testimonialService.addTestimonial(testimonial);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Testimonial submitted successfully", saved));
    }
}
