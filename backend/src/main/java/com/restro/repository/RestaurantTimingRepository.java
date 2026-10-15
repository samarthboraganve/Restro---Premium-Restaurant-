package com.restro.repository;

import com.restro.model.RestaurantTiming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantTimingRepository extends JpaRepository<RestaurantTiming, Long> {
    List<RestaurantTiming> findAllByOrderByDisplayOrderAsc();
}
