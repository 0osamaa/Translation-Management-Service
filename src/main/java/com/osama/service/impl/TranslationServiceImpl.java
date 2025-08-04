package com.osama.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osama.model.Translation;
import com.osama.model.dto.TranslationDTO;
import com.osama.repository.TranslationRepository;
import com.osama.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TranslationServiceImpl implements TranslationService {
    private final TranslationRepository repository;
    private final ObjectMapper objectMapper;

    @Autowired
    public TranslationServiceImpl(TranslationRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public TranslationDTO createTranslation(TranslationDTO dto) {
        Translation translation = mapToEntity(dto);
        return mapToDTO(repository.save(translation));
    }

    @Override
    public TranslationDTO updateTranslation(Long id, TranslationDTO dto) {
        Translation translation = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Translation not found"));
        translation.setKey(dto.getKey());
        translation.setLocale(dto.getLocale());
        translation.setContent(dto.getContent());
        translation.setTags(dto.getTags());
        return mapToDTO(repository.save(translation));
    }

    @Override
    public TranslationDTO getTranslation(Long id) {
        Translation translation = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Translation not found"));
        return mapToDTO(translation);
    }

    @Override
    public List<TranslationDTO> searchTranslations(String keyword) {
        return repository.searchTranslations(keyword).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TranslationDTO> getTranslationsByLocale(String locale) {
        return repository.findByLocale(locale).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String exportTranslationsAsJson(String locale) {
        try {
            List<TranslationDTO> translations = getTranslationsByLocale(locale);
            return objectMapper.writeValueAsString(translations);
        } catch (Exception e) {
            throw new RuntimeException("Failed to export translations", e);
        }
    }

    private TranslationDTO mapToDTO(Translation translation) {
        TranslationDTO dto = new TranslationDTO();
        dto.setId(translation.getId());
        dto.setKey(translation.getKey());
        dto.setLocale(translation.getLocale());
        dto.setContent(translation.getContent());
        dto.setTags(translation.getTags());
        return dto;
    }

    private Translation mapToEntity(TranslationDTO dto) {
        Translation translation = new Translation();
        translation.setKey(dto.getKey());
        translation.setLocale(dto.getLocale());
        translation.setContent(dto.getContent());
        translation.setTags(dto.getTags());
        return translation;
    }
}