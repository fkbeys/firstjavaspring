package com.kayaspring.kayaspring.Managers;

import com.kayaspring.kayaspring.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryManager  extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {


}

