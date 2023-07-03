package com.kayaspring.kayaspring.Data.Repositories;

import com.kayaspring.kayaspring.Entities.Models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ILanguageRepository extends JpaRepository<Language, Long>, JpaSpecificationExecutor<Language> {


}

