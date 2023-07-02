package com.kayaspring.kayaspring.Business.Managers;

import com.kayaspring.kayaspring.Entities.Models.Category;
import com.kayaspring.kayaspring.Entities.Models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ILanguageManager extends JpaRepository<Language, Long>, JpaSpecificationExecutor<Language> {


}

