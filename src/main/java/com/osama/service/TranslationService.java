package com.osama.service;

import com.osama.model.dto.TranslationDTO;

import java.util.List;

public interface TranslationService {
    TranslationDTO createTranslation(TranslationDTO translationDTO);
    TranslationDTO updateTranslation(Long id, TranslationDTO translationDTO);
    TranslationDTO getTranslation(Long id);
    List<TranslationDTO> searchTranslations(String keyword);
    List<TranslationDTO> getTranslationsByLocale(String locale);
    String exportTranslationsAsJson(String locale);
}
