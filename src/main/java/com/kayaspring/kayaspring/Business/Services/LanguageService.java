package com.kayaspring.kayaspring.Business.Services;

import com.kayaspring.kayaspring.Api.Middlewares.Logging.ILogger;
import com.kayaspring.kayaspring.Common.GenericResultClass;
import com.kayaspring.kayaspring.Data.Repositories.ILanguageRepository;
import com.kayaspring.kayaspring.Entities.Models.Language;
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
