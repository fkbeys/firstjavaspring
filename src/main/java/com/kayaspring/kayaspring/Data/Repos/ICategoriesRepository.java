package com.kayaspring.kayaspring.Data.Repos;

import com.kayaspring.kayaspring.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICategoriesRepository extends JpaRepository<Category, Long> {

}
