package com.marotino.translate.controller;

import com.marotino.translate.model.Page;
import com.marotino.translate.model.TranslatedEntry;
import com.marotino.translate.model.UntranslatedEntry;
import com.marotino.translate.service.TranslationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/translation")
public class TranslationController {

    private final TranslationServiceImpl translationServiceImpl;

    public TranslationController(TranslationServiceImpl translationServiceImpl) {
        this.translationServiceImpl = translationServiceImpl;
    }

    @GetMapping("/translated")
    public ResponseEntity<String> getTranslation(@RequestParam("word") String word) {
        String translation = translationServiceImpl.translate(word);
        return ResponseEntity.ok(translation);
    }

    @PostMapping("/translated")
    public ResponseEntity<TranslatedEntry> addTranslatedEntry(@RequestBody TranslatedEntry entry) {
        TranslatedEntry newEntry = translationServiceImpl.addTranslatedWord(entry);
        return ResponseEntity.ok(newEntry);
    }

    @GetMapping("/translated")
    public ResponseEntity<Page<TranslatedEntry>> getTranslatedEntries(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size) {
        Page<TranslatedEntry> entries = translationServiceImpl.getTranslatedWords(page, size);
        return ResponseEntity.ok(entries);
    }

    @GetMapping("/untranslated")
    public ResponseEntity<Page<UntranslatedEntry>> getUntranslatedEntries(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<UntranslatedEntry> entries = translationServiceImpl.getUntranslatedWords(page, size);
        return ResponseEntity.ok(entries);
    }
}
