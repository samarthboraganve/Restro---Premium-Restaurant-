package com.restro.config;

import com.restro.model.Dish;
import com.restro.model.Faq;
import com.restro.model.RestaurantTiming;
import com.restro.model.Testimonial;
import com.restro.repository.DishRepository;
import com.restro.repository.FaqRepository;
import com.restro.repository.RestaurantTimingRepository;
import com.restro.repository.TestimonialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    private final DishRepository dishRepository;
    private final RestaurantTimingRepository timingRepository;
    private final TestimonialRepository testimonialRepository;
    private final FaqRepository faqRepository;

    public DataSeeder(DishRepository dishRepository,
                      RestaurantTimingRepository timingRepository,
                      TestimonialRepository testimonialRepository,
                      FaqRepository faqRepository) {
        this.dishRepository = dishRepository;
        this.timingRepository = timingRepository;
        this.testimonialRepository = testimonialRepository;
        this.faqRepository = faqRepository;
    }

    @Override
    public void run(String... args) {
        seedDishes();
        seedTimings();
        seedTestimonials();
        seedFaqs();
    }

    private void seedDishes() {
        if (dishRepository.count() == 0) {
            log.info("Seeding initial dishes data...");
            dishRepository.saveAll(List.of(
                    new Dish("Grilled Chicken Alfredo", "Tender grilled chicken tossed in creamy parmesan garlic alfredo sauce over fettuccine.", new BigDecimal("24.00"), "Pasta", "/assets/dish1.png"),
                    new Dish("Mushroom Risotto", "Slow-cooked Arborio rice with wild forest mushrooms, herbs, and aged parmesan.", new BigDecimal("22.00"), "Risotto", "/assets/dish2.png"),
                    new Dish("Caprese Salad", "Fresh heirloom tomatoes, fresh mozzarella, sweet basil, and aged balsamic glaze.", new BigDecimal("16.00"), "Salad", "/assets/dish3.png"),
                    new Dish("Spaghetti & Meatballs", "Hand-rolled prime beef meatballs simmered in traditional San Marzano marinara.", new BigDecimal("23.00"), "Pasta", "/assets/dish4.png"),
                    new Dish("Caesar Salad", "Crisp romaine leaves, garlic herb croutons, parmesan crisps, and house Caesar dressing.", new BigDecimal("15.00"), "Salad", "/assets/dish5.png"),
                    new Dish("Grilled Atlantic Salmon", "Pan-seared Atlantic salmon fillet with citrus herb butter and seasonal greens.", new BigDecimal("29.00"), "Seafood", "/assets/dish6.png"),
                    new Dish("Grilled Ribeye Steak", "Prime USDA ribeye char-grilled to perfection with rosemary garlic compound butter.", new BigDecimal("39.00"), "Steak", "/assets/dish7.png"),
                    new Dish("Seafood Linguine", "Fresh tiger prawns, calamari, and mussels tossed in white wine garlic butter broth.", new BigDecimal("29.00"), "Pasta", "/assets/dish8.png")
            ));
            log.info("Dishes seeded successfully!");
        }
    }

    private void seedTimings() {
        if (timingRepository.count() == 0) {
            log.info("Seeding restaurant timings...");
            timingRepository.saveAll(List.of(
                    new RestaurantTiming("Monday", "10 AM - 09 PM", false, 1),
                    new RestaurantTiming("Tuesday", "11 AM - 10 PM", false, 2),
                    new RestaurantTiming("Wednesday", "10 AM - 09 PM", false, 3),
                    new RestaurantTiming("Thursday", "10 AM - 10 PM", false, 4),
                    new RestaurantTiming("Friday", "Closed", true, 5),
                    new RestaurantTiming("Saturday", "11 AM - 10 PM", false, 6),
                    new RestaurantTiming("Sunday", "12 AM - 9 PM", false, 7)
            ));
            log.info("Timings seeded successfully!");
        }
    }

    private void seedTestimonials() {
        if (testimonialRepository.count() == 0) {
            log.info("Seeding customer testimonials...");
            testimonialRepository.saveAll(List.of(
                    new Testimonial("Aarav Sharma", "Mumbai, India", "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?q=80&w=120&h=120&auto=format&fit=crop", 5, "Every dish was fresh, flavorful, and beautifully presented. The service was outstanding, and we will definitely be back again."),
                    new Testimonial("Rohan Mehta", "Bangalore, India", "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?q=80&w=120&h=120&auto=format&fit=crop", 5, "Amazing food, quick service, and a welcoming atmosphere. Every meal exceeded our expectations from start to finish."),
                    new Testimonial("Emily Carter", "London, UK", "https://images.unsplash.com/photo-1494790108377-be9c29b29330?q=80&w=120&h=120&auto=format&fit=crop", 5, "A wonderful dining experience with delicious food, friendly staff, and an atmosphere that made us feel right at home."),
                    new Testimonial("Daniel Kim", "Seoul, South Korea", "https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?q=80&w=120&h=120&auto=format&fit=crop", 5, "The flavors were authentic, every dish was perfectly prepared, and the presentation made the experience even more enjoyable."),
                    new Testimonial("Sofia Martinez", "Barcelona, Spain", "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?q=80&w=120&h=120&auto=format&fit=crop", 5, "Beautiful presentation, fresh ingredients, and outstanding service. It is easily one of my favorite places to dine."),
                    new Testimonial("Daniel Wong", "Sydney, Australia", "https://images.unsplash.com/photo-1522075469751-3a6694fb2f61?q=80&w=120&h=120&auto=format&fit=crop", 5, "Great food, attentive staff, and a cozy ambiance. Every visit has been memorable, and I highly recommend it.")
            ));
            log.info("Testimonials seeded successfully!");
        }
    }

    private void seedFaqs() {
        if (faqRepository.count() == 0) {
            log.info("Seeding FAQs...");
            faqRepository.saveAll(List.of(
                    new Faq("Do you offer vegetarian and vegan options?", "Yes, we have a dedicated selection of flavorful vegetarian and vegan options prepared with fresh ingredients. Look for the tags on our menu or ask your server.", 1),
                    new Faq("Can I reserve a table online?", "Yes! You can reserve your table online directly through our website by selecting your date, time and party size to receive instant confirmation.", 2),
                    new Faq("Do you offer takeaway and delivery?", "Absolutely. We offer takeaway for easy pickup, as well as delivery through our online platform partners. Select 'Order Online' to begin.", 3),
                    new Faq("Are your ingredients fresh and locally sourced?", "Yes, we partner with local farms and trusted organic suppliers daily to secure the freshest, highest quality seasonal ingredients for all our dishes.", 4),
                    new Faq("Do you host private events or celebrations?", "Yes, we host private parties, anniversaries, corporate events and large gatherings. Please contact our team via phone or email to discuss details.", 5)
            ));
            log.info("FAQs seeded successfully!");
        }
    }
}
