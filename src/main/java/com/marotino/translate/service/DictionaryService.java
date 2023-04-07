package com.marotino.translate.service;

import com.marotino.translate.model.DictionaryEntry;
import com.marotino.translate.model.Page;
import com.marotino.translate.repository.DictionaryRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DictionaryService {
    private final DictionaryRepository dictionaryRepository;

    public DictionaryService(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    public DictionaryEntry addWord(DictionaryEntry dictionaryEntry) {
        return dictionaryRepository.save(dictionaryEntry);
    }

    public Optional<DictionaryEntry> findWordById(Long id) {
        return dictionaryRepository.findById(id);
    }

    public Page<DictionaryEntry> findAllWords(Pageable pageable) {
        return dictionaryRepository.findAll(pageable);
    }

    public Optional<String> translate(String word) {
        DictionaryEntry dictionaryEntry = dictionaryRepository.findByPolishWordIgnoreCase(word);

        if (dictionaryEntry != null) {
            return Optional.of(dictionaryEntry.getEnglishWord());
        }
        dictionaryEntry = dictionaryRepository.findByEnglishWordIgnoreCase(word);

        if (dictionaryEntry != null) {
            return Optional.of(dictionaryEntry.getPolishWord());
        }
        return Optional.empty();
    }

    public Page<DictionaryEntry> listEntries(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return dictionaryRepository.findAll(pageable);
    }

}

