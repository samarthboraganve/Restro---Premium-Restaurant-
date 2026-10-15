package com.restro.model;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurant_timings")
public class RestaurantTiming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day_of_week", nullable = false, length = 20)
    private String day;

    @Column(name = "opening_hours", nullable = false, length = 50)
    private String hours;

    @Column(name = "is_closed")
    private Boolean isClosed = false;

    @Column(name = "display_order", nullable = false)
    private Integer displayOrder = 0;

    public RestaurantTiming() {
    }

    public RestaurantTiming(String day, String hours, Boolean isClosed, Integer displayOrder) {
        this.day = day;
        this.hours = hours;
        this.isClosed = isClosed != null ? isClosed : false;
        this.displayOrder = displayOrder != null ? displayOrder : 0;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public Boolean getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(Boolean isClosed) {
        this.isClosed = isClosed;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
}
