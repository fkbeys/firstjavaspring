package com.kayaspring.kayaspring.Business.Services;

import com.kayaspring.kayaspring.api.Middlewares.Logging.ILogger;
import com.kayaspring.kayaspring.Common.GenericResultClass;
import com.kayaspring.kayaspring.Data.Repositories.IWordRepository;
import com.kayaspring.kayaspring.Entities.Models.Word;
import org.springframework.stereotype.Service;


@Service
public class WordService implements IService<Word> {

    private final IWordRepository WordRepository;
    private final ILogger logger;

    public WordService(IWordRepository WordRepository, ILogger logger) {
        this.WordRepository = WordRepository;
        this.logger = logger;
    }

    @Override
    public GenericResultClass getAll() {
        try {
            var data = WordRepository.findAll();
            return GenericResultClass.Success(data, data.stream().count());
        } catch (Exception ex) {
            return GenericResultClass.Exception(ex, logger);
        }
    }




    @Override
    public GenericResultClass upsert(Word model) {
        try {
            WordRepository.save(model);
            return GenericResultClass.Success(true, 1);
        } catch (Exception ex) {
            return GenericResultClass.Exception(ex, logger);
        }
    }


    @Override
    public GenericResultClass delete(long id) {
        try {
            WordRepository.deleteById(id);
            return GenericResultClass.Success(true, 1);
        } catch (Exception ex) {
            return GenericResultClass.Exception(ex, logger);
        }
    }
}
