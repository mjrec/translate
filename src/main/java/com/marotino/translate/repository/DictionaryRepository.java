package com.marotino.translate.repository;

import com.marotino.translate.model.DictionaryEntry;
import com.marotino.translate.model.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface DictionaryRepository extends CrudRepository<DictionaryEntry, Long> {

    DictionaryEntry findByPolishWordIgnoreCase(String polishWord);

    DictionaryEntry findByEnglishWordIgnoreCase(String englishWord);

    Page<DictionaryEntry> findAll(Pageable pageable);
}
