package com.kayaspring.kayaspring.Business.Managers;

import com.kayaspring.kayaspring.Entities.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ILanguagesRepository extends JpaRepository<Category, Long> {

}
