package com.restro.controller;

import com.restro.dto.ApiResponse;
import com.restro.model.RestaurantTiming;
import com.restro.service.RestaurantTimingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/timings")
public class RestaurantTimingController {

    private final RestaurantTimingService timingService;

    public RestaurantTimingController(RestaurantTimingService timingService) {
        this.timingService = timingService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RestaurantTiming>>> getTimings() {
        List<RestaurantTiming> timings = timingService.getAllTimings();
        return ResponseEntity.ok(ApiResponse.success(timings));
    }
}
