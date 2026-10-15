package com.restro.repository;

import com.restro.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByIsAvailableTrue();
    List<Dish> findByIsFeaturedTrueAndIsAvailableTrue();
    List<Dish> findByCategoryIgnoreCaseAndIsAvailableTrue(String category);
}
