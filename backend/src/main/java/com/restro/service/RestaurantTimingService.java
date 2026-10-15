package com.restro.service;

import com.restro.model.RestaurantTiming;
import com.restro.repository.RestaurantTimingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantTimingService {

    private final RestaurantTimingRepository timingRepository;

    public RestaurantTimingService(RestaurantTimingRepository timingRepository) {
        this.timingRepository = timingRepository;
    }

    public List<RestaurantTiming> getAllTimings() {
        return timingRepository.findAllByOrderByDisplayOrderAsc();
    }
}
