package com.osama.controller;

import com.osama.model.dto.TranslationDTO;
import com.osama.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/translations")
public class TranslationController {
    private final TranslationService translationService;

    @Autowired
    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @PostMapping
    public ResponseEntity<TranslationDTO> createTranslation(@RequestBody TranslationDTO translationDTO) {
        return ResponseEntity.ok(translationService.createTranslation(translationDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TranslationDTO> updateTranslation(@PathVariable Long id, @RequestBody TranslationDTO translationDTO) {
        return ResponseEntity.ok(translationService.updateTranslation(id, translationDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TranslationDTO> getTranslation(@PathVariable Long id) {
        return ResponseEntity.ok(translationService.getTranslation(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TranslationDTO>> searchTranslations(@RequestParam String keyword) {
        return ResponseEntity.ok(translationService.searchTranslations(keyword));
    }

    @GetMapping("/locale/{locale}")
    public ResponseEntity<List<TranslationDTO>> getTranslationsByLocale(@PathVariable String locale) {
        return ResponseEntity.ok(translationService.getTranslationsByLocale(locale));
    }

    @GetMapping("/export/{locale}")
    public ResponseEntity<String> exportTranslations(@PathVariable String locale) {
        return ResponseEntity.ok(translationService.exportTranslationsAsJson(locale));
    }
}