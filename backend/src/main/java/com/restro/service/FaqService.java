package com.restro.service;

import com.restro.model.Faq;
import com.restro.repository.FaqRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaqService {

    private final FaqRepository faqRepository;

    public FaqService(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    public List<Faq> getActiveFaqs() {
        return faqRepository.findByIsActiveTrueOrderByDisplayOrderAsc();
    }
}
