package com.restro.service;

import com.restro.model.Testimonial;
import com.restro.repository.TestimonialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestimonialService {

    private final TestimonialRepository testimonialRepository;

    public TestimonialService(TestimonialRepository testimonialRepository) {
        this.testimonialRepository = testimonialRepository;
    }

    public List<Testimonial> getApprovedTestimonials() {
        return testimonialRepository.findByIsApprovedTrueOrderByRatingDesc();
    }

    public Testimonial addTestimonial(Testimonial testimonial) {
        return testimonialRepository.save(testimonial);
    }
}
