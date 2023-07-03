package com.kayaspring.kayaspring.Data.Repositories;

import com.kayaspring.kayaspring.Entities.Models.User.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<AppUser, Long>, JpaSpecificationExecutor<AppUser> {

//    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM AppUser u WHERE u.username = ?1 AND u.password = ?2")
//    boolean existsByUsernameAndPassword(String username, String password);
//

    Optional<AppUser> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}

