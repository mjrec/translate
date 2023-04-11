package com.marotino.translate.repository;

import com.marotino.translate.model.Page;
import com.marotino.translate.model.TranslatedEntry;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface TranslatedEntryRepository extends CrudRepository<TranslatedEntry, Long> {

    TranslatedEntry findByPolishWordIgnoreCase(String polishWord);

    TranslatedEntry findByEnglishWordIgnoreCase(String englishWord);

    Page<TranslatedEntry> findAll(Pageable pageable);
}
