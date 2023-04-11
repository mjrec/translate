package com.marotino.translate.service;

import com.marotino.translate.model.Page;
import com.marotino.translate.model.TranslatedEntry;
import com.marotino.translate.model.UntranslatedEntry;

public interface TranslationService {

    TranslatedEntry addTranslatedWord(TranslatedEntry word);

    Page<TranslatedEntry> getTranslatedWords(int page, int size);

    Page<UntranslatedEntry> getUntranslatedWords(int page, int size);

    String translate(String translate);

}
