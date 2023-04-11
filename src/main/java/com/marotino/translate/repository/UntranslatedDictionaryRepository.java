package com.marotino.translate.repository;

import com.marotino.translate.model.Page;
import com.marotino.translate.model.UntranslatedEntry;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface UntranslatedDictionaryRepository extends CrudRepository<UntranslatedEntry, Long> {

    void removeByWord(String value);
    Page<UntranslatedEntry> findAll(Pageable pageable);
}
