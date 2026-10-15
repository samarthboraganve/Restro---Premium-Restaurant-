package com.restro.service;

import com.restro.exception.ResourceNotFoundException;
import com.restro.model.Dish;
import com.restro.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> getAllAvailableDishes() {
        return dishRepository.findByIsAvailableTrue();
    }

    public List<Dish> getFeaturedDishes() {
        return dishRepository.findByIsFeaturedTrueAndIsAvailableTrue();
    }

    public List<Dish> getDishesByCategory(String category) {
        return dishRepository.findByCategoryIgnoreCaseAndIsAvailableTrue(category);
    }

    public Dish getDishById(Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found with id: " + id));
    }

    public Dish createDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public Dish updateDish(Long id, Dish updatedDish) {
        Dish existing = getDishById(id);
        existing.setTitle(updatedDish.getTitle());
        existing.setDescription(updatedDish.getDescription());
        existing.setPrice(updatedDish.getPrice());
        existing.setCategory(updatedDish.getCategory());
        existing.setImageUrl(updatedDish.getImageUrl());
        existing.setIsFeatured(updatedDish.getIsFeatured());
        existing.setIsAvailable(updatedDish.getIsAvailable());
        return dishRepository.save(existing);
    }

    public void deleteDish(Long id) {
        Dish dish = getDishById(id);
        dishRepository.delete(dish);
    }
}
