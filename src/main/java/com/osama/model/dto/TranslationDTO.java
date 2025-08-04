package com.osama.model.dto;
import lombok.Data;

@Data
public class TranslationDTO {
    private Long id;
    private String key;
    private String locale;
    private String content;
    private String tags;
}
