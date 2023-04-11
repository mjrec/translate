package com.marotino.translate.controller;

import com.marotino.translate.model.Page;
import com.marotino.translate.model.TranslatedEntry;
import com.marotino.translate.model.UntranslatedEntry;
import com.marotino.translate.service.TranslationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TranslationControllerTest {

    @Mock
    private TranslationServiceImpl translationService;

    @Test
    public void getTranslation_returnsOkResponse() {
        // Given
        String word = "hello";
        String translation = "cześć";
        when(translationService.translate(anyString())).thenReturn(translation);

        // When
        TranslationController controller = new TranslationController(translationService);
        ResponseEntity<String> response = controller.getTranslation(word);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(translation, response.getBody());
        verify(translationService, times(1)).translate(word);
    }

    @Test
    public void addTranslatedEntry_returnsOkResponse() {
        // Given
        TranslatedEntry entry = new TranslatedEntry("kot", "cat");
        when(translationService.addTranslatedWord(any(TranslatedEntry.class))).thenReturn(entry);

        // When
        TranslationController controller = new TranslationController(translationService);
        ResponseEntity<TranslatedEntry> response = controller.addTranslatedEntry(entry);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(entry, response.getBody());
        verify(translationService, times(1)).addTranslatedWord(entry);
    }

    @Test
    public void getTranslatedEntries_returnsOkResponse() {
        // Given
        Page<TranslatedEntry> entries = new Page<>(List.of(new TranslatedEntry("kot", "cat")), 0, 1, 1);
        when(translationService.getTranslatedWords(anyInt(), anyInt())).thenReturn(entries);

        // When
        TranslationController controller = new TranslationController(translationService);
        ResponseEntity<Page<TranslatedEntry>> response = controller.getTranslatedEntries(0, 1);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(entries, response.getBody());
        verify(translationService, times(1)).getTranslatedWords(0, 1);
    }

    @Test
    public void getUntranslatedEntries_returnsOkResponse() {
        // Given
        Page<UntranslatedEntry> entries = new Page<>(List.of(new UntranslatedEntry("pies")), 0, 1, 1);
        when(translationService.getUntranslatedWords(anyInt(), anyInt())).thenReturn(entries);

        // When
        TranslationController controller = new TranslationController(translationService);
        ResponseEntity<Page<UntranslatedEntry>> response = controller.getUntranslatedEntries(0, 1);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(entries, response.getBody());
        verify(translationService, times(1)).getUntranslatedWords(0, 1);
    }
}
