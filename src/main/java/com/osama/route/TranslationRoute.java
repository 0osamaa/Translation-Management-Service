package com.osama.route;

import com.osama.model.AuthRequest;
import com.osama.model.User;
import com.osama.model.dto.TranslationDTO;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TranslationRoute extends RouteBuilder {
    @Override
    public void configure() {
        rest("/api")
                .post("/translations").type(TranslationDTO.class).to("bean:translationService?method=createTranslation")
                .put("/translations/{id}").type(TranslationDTO.class).to("bean:translationService?method=updateTranslation(${header.id}, ${body})")
                .get("/translations/{id}").to("bean:translationService?method=getTranslation(${header.id})")
                .get("/translations/search").to("bean:translationService?method=searchTranslations(${header.keyword})")
                .get("/translations/locale/{locale}").to("bean:translationService?method=getTranslationsByLocale(${header.locale})")
                .get("/translations/export/{locale}").produces("application/json")
                .to("bean:translationService?method=exportTranslationsAsJson(${header.locale})")
                .post("/auth/login").type(AuthRequest.class).to("bean:authController?method=login")
                .post("/auth/signup").type(User.class).to("bean:authController?method=signup");
    }
}
