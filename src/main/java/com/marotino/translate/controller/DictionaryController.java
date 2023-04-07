package com.marotino.translate.controller;

import com.marotino.translate.model.DictionaryEntry;
import com.marotino.translate.model.Page;
import com.marotino.translate.service.DictionaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("/translate")
    public ResponseEntity<String> translateWord(@RequestParam("word") String word) {
        Optional<String> translation = dictionaryService.translate(word);
        return translation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/words")
    public ResponseEntity<DictionaryEntry> addWord(@RequestBody DictionaryEntry entry) {
        DictionaryEntry newEntry = dictionaryService.addWord(entry);
        return ResponseEntity.ok(newEntry);
    }

    @GetMapping("/words")
    public ResponseEntity<Page<DictionaryEntry>> listWords(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        Page<DictionaryEntry> entries = dictionaryService.listEntries(page, size);
        return ResponseEntity.ok(entries);
    }
}
