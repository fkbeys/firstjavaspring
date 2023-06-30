package com.kayaspring.kayaspring.Data;

import com.kayaspring.kayaspring.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ILanguagesRepository extends JpaRepository<Category, Long> {

}
