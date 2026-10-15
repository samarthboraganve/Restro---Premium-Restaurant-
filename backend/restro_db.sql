-- ==========================================================
-- RESTRO DATABASE COMPLETE SETUP (MySQL 8.0+)
-- Database: restro_db
-- ==========================================================

DROP DATABASE IF EXISTS restro_db;
CREATE DATABASE restro_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE restro_db;

-- ----------------------------------------------------------
-- 1. Table: dishes (Menu Items)
-- ----------------------------------------------------------
CREATE TABLE dishes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    category VARCHAR(50) DEFAULT 'Main Course',
    image_url VARCHAR(255) NOT NULL,
    is_featured BOOLEAN DEFAULT TRUE,
    is_available BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_dish_category (category),
    INDEX idx_dish_available (is_available)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------------------------------------
-- 2. Table: reservations (Table Bookings)
-- ----------------------------------------------------------
CREATE TABLE reservations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    customer_email VARCHAR(150) NOT NULL,
    customer_phone VARCHAR(20) NOT NULL,
    reservation_date DATE NOT NULL,
    reservation_time VARCHAR(20) NOT NULL,
    guest_count INT NOT NULL DEFAULT 2,
    special_requests TEXT,
    status ENUM('PENDING', 'CONFIRMED', 'CANCELLED', 'COMPLETED') DEFAULT 'CONFIRMED',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_reservation_date (reservation_date),
    INDEX idx_reservation_email (customer_email),
    INDEX idx_reservation_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------------------------------------
-- 3. Table: testimonials (Customer Reviews)
-- ----------------------------------------------------------
CREATE TABLE testimonials (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    author_name VARCHAR(100) NOT NULL,
    location VARCHAR(100),
    avatar_url VARCHAR(255),
    rating INT NOT NULL CHECK (rating >= 1 AND rating <= 5),
    review TEXT NOT NULL,
    is_approved BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------------------------------------
-- 4. Table: restaurant_timings (Operating Hours)
-- ----------------------------------------------------------
CREATE TABLE restaurant_timings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    day_of_week VARCHAR(20) NOT NULL,
    opening_hours VARCHAR(50) NOT NULL,
    is_closed BOOLEAN DEFAULT FALSE,
    display_order INT NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------------------------------------
-- 5. Table: faqs (Frequently Asked Questions)
-- ----------------------------------------------------------
CREATE TABLE faqs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    question VARCHAR(255) NOT NULL,
    answer TEXT NOT NULL,
    display_order INT NOT NULL DEFAULT 0,
    is_active BOOLEAN DEFAULT TRUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------------------------------------
-- 6. Table: contact_messages (Customer Inquiries)
-- ----------------------------------------------------------
CREATE TABLE contact_messages (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    subject VARCHAR(200),
    message TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ==========================================================
-- SEED DATA
-- ==========================================================

INSERT INTO dishes (id, title, description, price, category, image_url, is_featured, is_available) VALUES
(1, 'Grilled Chicken Alfredo', 'Tender grilled chicken tossed in creamy parmesan garlic alfredo sauce over fettuccine.', 24.00, 'Pasta', '/assets/dish1.png', TRUE, TRUE),
(2, 'Mushroom Risotto', 'Slow-cooked Arborio rice with wild forest mushrooms, herbs, and aged parmesan.', 22.00, 'Risotto', '/assets/dish2.png', TRUE, TRUE),
(3, 'Caprese Salad', 'Fresh heirloom tomatoes, fresh mozzarella, sweet basil, and aged balsamic glaze.', 16.00, 'Salad', '/assets/dish3.png', TRUE, TRUE),
(4, 'Spaghetti & Meatballs', 'Hand-rolled prime beef meatballs simmered in traditional San Marzano marinara.', 23.00, 'Pasta', '/assets/dish4.png', TRUE, TRUE),
(5, 'Caesar Salad', 'Crisp romaine leaves, garlic herb croutons, parmesan crisps, and house Caesar dressing.', 15.00, 'Salad', '/assets/dish5.png', TRUE, TRUE),
(6, 'Grilled Atlantic Salmon', 'Pan-seared Atlantic salmon fillet with citrus herb butter and seasonal greens.', 29.00, 'Seafood', '/assets/dish6.png', TRUE, TRUE),
(7, 'Grilled Ribeye Steak', 'Prime USDA ribeye char-grilled to perfection with rosemary garlic compound butter.', 39.00, 'Steak', '/assets/dish7.png', TRUE, TRUE),
(8, 'Seafood Linguine', 'Fresh tiger prawns, calamari, and mussels tossed in white wine garlic butter broth.', 29.00, 'Pasta', '/assets/dish8.png', TRUE, TRUE);

INSERT INTO restaurant_timings (id, day_of_week, opening_hours, is_closed, display_order) VALUES
(1, 'Monday', '10 AM - 09 PM', FALSE, 1),
(2, 'Tuesday', '11 AM - 10 PM', FALSE, 2),
(3, 'Wednesday', '10 AM - 09 PM', FALSE, 3),
(4, 'Thursday', '10 AM - 10 PM', FALSE, 4),
(5, 'Friday', 'Closed', TRUE, 5),
(6, 'Saturday', '11 AM - 10 PM', FALSE, 6),
(7, 'Sunday', '12 AM - 9 PM', FALSE, 7);

INSERT INTO testimonials (id, author_name, location, avatar_url, rating, review, is_approved) VALUES
(1, 'Aarav Sharma', 'Mumbai, India', 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?q=80&w=120&h=120&auto=format&fit=crop', 5, 'Every dish was fresh, flavorful, and beautifully presented. The service was outstanding, and we will definitely be back again.', TRUE),
(2, 'Rohan Mehta', 'Bangalore, India', 'https://images.unsplash.com/photo-1500648767791-00dcc994a43e?q=80&w=120&h=120&auto=format&fit=crop', 5, 'Amazing food, quick service, and a welcoming atmosphere. Every meal exceeded our expectations from start to finish.', TRUE),
(3, 'Emily Carter', 'London, UK', 'https://images.unsplash.com/photo-1494790108377-be9c29b29330?q=80&w=120&h=120&auto=format&fit=crop', 5, 'A wonderful dining experience with delicious food, friendly staff, and an atmosphere that made us feel right at home.', TRUE),
(4, 'Daniel Kim', 'Seoul, South Korea', 'https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?q=80&w=120&h=120&auto=format&fit=crop', 5, 'The flavors were authentic, every dish was perfectly prepared, and the presentation made the experience even more enjoyable.', TRUE),
(5, 'Sofia Martinez', 'Barcelona, Spain', 'https://images.unsplash.com/photo-1438761681033-6461ffad8d80?q=80&w=120&h=120&auto=format&fit=crop', 5, 'Beautiful presentation, fresh ingredients, and outstanding service. It is easily one of my favorite places to dine.', TRUE),
(6, 'Daniel Wong', 'Sydney, Australia', 'https://images.unsplash.com/photo-1522075469751-3a6694fb2f61?q=80&w=120&h=120&auto=format&fit=crop', 5, 'Great food, attentive staff, and a cozy ambiance. Every visit has been memorable, and I highly recommend it.', TRUE);

INSERT INTO faqs (id, question, answer, display_order, is_active) VALUES
(1, 'Do you offer vegetarian and vegan options?', 'Yes, we have a dedicated selection of flavorful vegetarian and vegan options prepared with fresh ingredients. Look for the tags on our menu or ask your server.', 1, TRUE),
(2, 'Can I reserve a table online?', 'Yes! You can reserve your table online directly through our website by selecting your date, time and party size to receive instant confirmation.', 2, TRUE),
(3, 'Do you offer takeaway and delivery?', 'Absolutely. We offer takeaway for easy pickup, as well as delivery through our online platform partners. Select Order Online to begin.', 3, TRUE),
(4, 'Are your ingredients fresh and locally sourced?', 'Yes, we partner with local farms and trusted organic suppliers daily to secure the freshest, highest quality seasonal ingredients for all our dishes.', 4, TRUE),
(5, 'Do you host private events or celebrations?', 'Yes, we host private parties, anniversaries, corporate events and large gatherings. Please contact our team via phone or email to discuss details.', 5, TRUE);
