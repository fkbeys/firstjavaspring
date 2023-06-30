package com.kayaspring.kayaspring.Data;

import com.kayaspring.kayaspring.Models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IUsersRepository extends JpaRepository<AppUser, Long> {

}
