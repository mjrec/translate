package com.marotino.translate.service;

import com.marotino.translate.model.Page;
import com.marotino.translate.model.TranslatedEntry;
import com.marotino.translate.model.UntranslatedEntry;
import com.marotino.translate.repository.TranslatedEntryRepository;
import com.marotino.translate.repository.UntranslatedDictionaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class TranslationServiceImpl implements TranslationService {
    private final TranslatedEntryRepository translatedEntryRepository;
    private final UntranslatedDictionaryRepository untranslatedDictionaryRepository;
    
    @Override
    public TranslatedEntry addTranslatedWord(TranslatedEntry word) {
        untranslatedDictionaryRepository.removeByWord(word.getEnglishWord());
        untranslatedDictionaryRepository.removeByWord(word.getPolishWord());
        return translatedEntryRepository.save(word);
    }

    @Override
    public Page<TranslatedEntry> getTranslatedWords(int page, int size) {
        return translatedEntryRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<UntranslatedEntry> getUntranslatedWords(int page, int size) {
        return untranslatedDictionaryRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public String translate(String sentence) {
        String cleanSentence = sentence.replaceAll("\\p{Punct}", "");
        String[] sentenceWords = cleanSentence.split(" ");
        List<String> translatedSentenceList = Arrays.stream(sentenceWords)
                .map(this::translateWord).toList();
        return String.join(" ", translatedSentenceList);
    }

    private String translateWord(String word) {
        TranslatedEntry translatedEntry = translatedEntryRepository.findByPolishWordIgnoreCase(word.toLowerCase());

        if (translatedEntry != null) {
            return translatedEntry.getEnglishWord();
        }
        translatedEntry = translatedEntryRepository.findByEnglishWordIgnoreCase(word);

        if (translatedEntry != null) {
            return translatedEntry.getPolishWord();
        }

        return word;
    }

}
