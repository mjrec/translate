package com.marotino.translate.model;

public class DictionaryEntry {

    private Long id;
    private String polishWord;
    private String englishWord;

    public DictionaryEntry() {}

    public DictionaryEntry(String polishWord, String englishWord) {
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
