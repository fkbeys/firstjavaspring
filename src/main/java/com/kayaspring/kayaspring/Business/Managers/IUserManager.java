package com.kayaspring.kayaspring.Business.Managers;

import com.kayaspring.kayaspring.Entities.Models.AppUser;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserManager extends JpaRepository<AppUser, Long>, JpaSpecificationExecutor<AppUser> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM AppUser u WHERE u.username = ?1 AND u.password = ?2")
    boolean existsByUsernameAndPassword(String username, String password);


}

