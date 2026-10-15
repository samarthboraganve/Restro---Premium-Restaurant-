package com.restro.controller;

import com.restro.dto.ApiResponse;
import com.restro.model.Faq;
import com.restro.service.FaqService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/faqs")
public class FaqController {

    private final FaqService faqService;

    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Faq>>> getFaqs() {
        List<Faq> faqs = faqService.getActiveFaqs();
        return ResponseEntity.ok(ApiResponse.success(faqs));
    }
}
