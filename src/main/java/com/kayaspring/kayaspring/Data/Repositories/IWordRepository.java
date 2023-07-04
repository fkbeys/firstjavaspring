package com.kayaspring.kayaspring.Data.Repositories;

import com.kayaspring.kayaspring.Entities.Models.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IWordRepository extends JpaRepository<Word, Long>, JpaSpecificationExecutor<Word> {


}

