package com.marotino.translate.model;

import jakarta.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TranslatedEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String polishWord;
    private String englishWord;

    public TranslatedEntry() {}

    public TranslatedEntry(String polishWord, String englishWord) {
        this.polishWord = polishWord;
        this.englishWord = englishWord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPolishWord() {
        return polishWord;
    }

    public void setPolishWord(String polishWord) {
        this.polishWord = polishWord;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    @Override
    public String toString() {
        return "DictionaryEntry{" +
                "id=" + id +
                ", polishWord='" + polishWord + '\'' +
                ", englishWord='" + englishWord + '\'' +
                '}';
    }
}
