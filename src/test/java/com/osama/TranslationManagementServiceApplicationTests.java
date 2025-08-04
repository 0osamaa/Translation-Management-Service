package com.osama;

import com.osama.model.dto.TranslationDTO;
import com.osama.service.TranslationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TranslationManagementServiceApplicationTests {

    @Autowired
    private TranslationService translationService;

    @Test
    public void testCreateTranslation() {
        TranslationDTO dto = new TranslationDTO();
        dto.setKey("test");
        dto.setLocale("en");
        dto.setContent("Test Content");
        dto.setTags("web");

        TranslationDTO result = translationService.createTranslation(dto);
        assertNotNull(result.getId());
        assertEquals("test", result.getKey());
    }

}
