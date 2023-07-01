package com.kayaspring.kayaspring.Data;

import com.kayaspring.kayaspring.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoriesRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

}

