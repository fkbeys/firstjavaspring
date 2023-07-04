package com.kayaspring.kayaspring.business.services;

import com.kayaspring.kayaspring.api.middlewares.logging.ILogger;
import com.kayaspring.kayaspring.common.GenericResultClass;
import com.kayaspring.kayaspring.data.repositories.ILanguageRepository;
import com.kayaspring.kayaspring.entities.models.Language;
import org.springframework.stereotype.Service;


@Service
public class LanguageService implements IService<Language> {

    private final ILanguageRepository languageRepository;
    private final ILogger logger;

    public LanguageService(ILanguageRepository languageRepository, ILogger logger) {
        this.languageRepository = languageRepository;
        this.logger = logger;
    }

    @Override
    public GenericResultClass getAll() {
        try {
            var data = languageRepository.findAll();
            return GenericResultClass.Success(data, data.stream().count());
        } catch (Exception ex) {
            return GenericResultClass.Exception(ex, logger);
        }
    }


    @Override
    public GenericResultClass upsert(Language model) {
        try {
            languageRepository.save(model);
            return GenericResultClass.Success(true, 1);
        } catch (Exception ex) {
            return GenericResultClass.Exception(ex, logger);
        }
    }


    @Override
    public GenericResultClass delete(long id) {
        try {
            languageRepository.deleteById(id);
            return GenericResultClass.Success(true, 1);
        } catch (Exception ex) {
            return GenericResultClass.Exception(ex, logger);
        }
    }
}
