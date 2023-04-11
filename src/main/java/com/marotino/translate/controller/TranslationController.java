package com.marotino.translate.controller;

import com.marotino.translate.model.Page;
import com.marotino.translate.model.TranslatedEntry;
import com.marotino.translate.model.UntranslatedEntry;
import com.marotino.translate.service.TranslationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**

 The TranslationController class provides RESTful endpoints for translating words, adding translated words,
 retrieving translated and untranslated entries in paginated form.
 */
@RestController
@RequestMapping("/translation")
public class TranslationController {

    private final TranslationServiceImpl translationServiceImpl;

    public TranslationController(TranslationServiceImpl translationServiceImpl) {
        this.translationServiceImpl = translationServiceImpl;
    }

    /**
     * Retrieves the translation of the given word.
     *
     * @param word the word to be translated.
     * @return the translation of the given word.
     */
    @GetMapping("/translated")
    public ResponseEntity<String> getTranslation(@RequestParam("word") String word) {
        String translation = translationServiceImpl.translate(word);
        return ResponseEntity.ok(translation);
    }

    /**
     * Adds a new translated entry to the database.
     *
     * @param entry the translated entry to be added.
     * @return the newly added translated entry.
     */
    @PostMapping("/translated")
    public ResponseEntity<TranslatedEntry> addTranslatedEntry(@RequestBody TranslatedEntry entry) {
        TranslatedEntry newEntry = translationServiceImpl.addTranslatedWord(entry);
        return ResponseEntity.ok(newEntry);
    }

    /**
     * Retrieves a paginated list of translated entries from the database.
     *
     * @param page the page number to be retrieved.
     * @param size the size of each page.
     * @return a paginated list of translated entries.
     */
    @GetMapping("/translated-list")
    public ResponseEntity<Page<TranslatedEntry>> getTranslatedEntries(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size) {
        Page<TranslatedEntry> entries = translationServiceImpl.getTranslatedWords(page, size);
        return ResponseEntity.ok(entries);
    }

    /**
     * Retrieves a paginated list of untranslated entries from the database.
     *
     * @param page the page number to be retrieved.
     * @param size the size of each page.
     * @return a paginated list of untranslated entries.
     */
    @GetMapping("/untranslated-list")
    public ResponseEntity<Page<UntranslatedEntry>> getUntranslatedEntries(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<UntranslatedEntry> entries = translationServiceImpl.getUntranslatedWords(page, size);
        return ResponseEntity.ok(entries);
    }
}
