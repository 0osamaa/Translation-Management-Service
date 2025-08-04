package com.osama.repository;

import com.osama.model.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Long> {
    List<Translation> findByLocale(String locale);

    @Query("SELECT t FROM Translation t WHERE t.key LIKE %:keyword% OR t.content LIKE %:keyword% OR t.tags LIKE %:keyword%")
    List<Translation> searchTranslations(String keyword);
}
