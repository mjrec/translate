package com.marotino.translate.service;

import com.marotino.translate.model.Page;
import com.marotino.translate.model.TranslatedEntry;
import com.marotino.translate.model.UntranslatedEntry;
import com.marotino.translate.repository.TranslatedEntryRepository;
import com.marotino.translate.repository.UntranslatedDictionaryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TranslationServiceImplTest {
    @Mock
    private TranslatedEntryRepository translatedEntryRepository;
    @Mock
    private UntranslatedDictionaryRepository untranslatedDictionaryRepository;

    private TranslationServiceImpl translationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        translationService = new TranslationServiceImpl(translatedEntryRepository, untranslatedDictionaryRepository);
    }

    @Test
    void addTranslatedWord() {
        TranslatedEntry word = new TranslatedEntry("apple", "jabłko");
        when(translatedEntryRepository.save(word)).thenReturn(word);

        TranslatedEntry addedWord = translationService.addTranslatedWord(word);

        assertEquals(word, addedWord);
    }

    @Test
    void getTranslatedWords() {
        int page = 0;
        int size = 1;
        int totalElements = 1;
        Page<TranslatedEntry> expectedPage = new Page<>(List.of(new TranslatedEntry()), page, size, totalElements);
        when(translatedEntryRepository.findAll(PageRequest.of(page, size))).thenReturn(expectedPage);

        Page<TranslatedEntry> actualPage = translationService.getTranslatedWords(page, size);

        assertEquals(expectedPage, actualPage);
    }

    @Test
    void getUntranslatedWords() {
        int page = 0;
        int size = 1;
        Page<UntranslatedEntry> expectedPage = new Page<>(List.of(new UntranslatedEntry("")), page, size, 1);
        when(untranslatedDictionaryRepository.findAll(PageRequest.of(page, size))).thenReturn(expectedPage);

        Page<UntranslatedEntry> actualPage = translationService.getUntranslatedWords(page, size);

        assertEquals(expectedPage, actualPage);
    }

    @Test
    void translate() {
        String sentence = "To jest jabłko.";
        String expectedTranslation = "this is apple";
        TranslatedEntry apple = new TranslatedEntry("jabłko", "apple");
        TranslatedEntry is = new TranslatedEntry("jest", "is");
        TranslatedEntry thisWord = new TranslatedEntry("to", "this");

        when(translatedEntryRepository.findByPolishWordIgnoreCase("jabłko")).thenReturn(apple);
        when(translatedEntryRepository.findByPolishWordIgnoreCase("jest")).thenReturn(is);
        when(translatedEntryRepository.findByPolishWordIgnoreCase("to")).thenReturn(thisWord);

        String actualTranslation = translationService.translate(sentence);

        assertEquals(expectedTranslation, actualTranslation);
    }
}