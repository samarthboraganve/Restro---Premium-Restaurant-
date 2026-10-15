package com.restro.controller;

import com.restro.dto.ApiResponse;
import com.restro.model.Dish;
import com.restro.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Dish>>> getDishes(
            @RequestParam(required = false) String category,
            @RequestParam(required = false, defaultValue = "false") boolean featuredOnly) {
        
        List<Dish> dishes;
        if (featuredOnly) {
            dishes = dishService.getFeaturedDishes();
        } else if (category != null && !category.isBlank()) {
            dishes = dishService.getDishesByCategory(category);
        } else {
            dishes = dishService.getAllAvailableDishes();
        }
        return ResponseEntity.ok(ApiResponse.success(dishes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Dish>> getDishById(@PathVariable Long id) {
        Dish dish = dishService.getDishById(id);
        return ResponseEntity.ok(ApiResponse.success(dish));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Dish>> createDish(@RequestBody Dish dish) {
        Dish created = dishService.createDish(dish);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Dish created successfully", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Dish>> updateDish(@PathVariable Long id, @RequestBody Dish dish) {
        Dish updated = dishService.updateDish(id, dish);
        return ResponseEntity.ok(ApiResponse.success("Dish updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.ok(ApiResponse.success("Dish deleted successfully", null));
    }
}
