package com.marotino.translate.controller;

import com.marotino.translate.model.DictionaryEntry;
import com.marotino.translate.model.Page;
import com.marotino.translate.service.DictionaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class DictionaryControllerTest {

    @Mock
    private DictionaryService dictionaryService;

    private DictionaryController dictionaryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dictionaryController = new DictionaryController(dictionaryService);
    }

    @Test
    void testTranslateWord() {
        String word = "hello";
        String translation = "hola";
        when(dictionaryService.translate(word)).thenReturn(Optional.of(translation));

        ResponseEntity<String> response = dictionaryController.translateWord(word);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(translation, response.getBody());
    }

    @Test
    void testTranslateWordNotFound() {
        String word = "goodbye";
        when(dictionaryService.translate(word)).thenReturn(Optional.empty());

        ResponseEntity<String> response = dictionaryController.translateWord(word);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testAddWord() {
        DictionaryEntry entry = new DictionaryEntry("hello", "hola");
        when(dictionaryService.addWord(entry)).thenReturn(entry);

        ResponseEntity<DictionaryEntry> response = dictionaryController.addWord(entry);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(entry, response.getBody());
    }

    @Test
    void testListWords() {

        Page<DictionaryEntry> entries = new Page<>(Collections.singletonList(new DictionaryEntry("hello", "hola")), 1, 10, 20);
        when(dictionaryService.listEntries(0, 10)).thenReturn(entries);

        ResponseEntity<Page<DictionaryEntry>> response = dictionaryController.listWords(0, 10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(entries, response.getBody());
    }
}
