package com.osama.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TranslationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateTranslation() throws Exception {
        String json = "{\"key\":\"test\",\"locale\":\"en\",\"content\":\"Test Content\",\"tags\":\"web\"}";
        mockMvc.perform(post("/api/translations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value("test"));
    }
}
