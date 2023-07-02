package com.kayaspring.kayaspring.Business.Managers;

import com.kayaspring.kayaspring.Entities.Models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IUsersRepository extends JpaRepository<AppUser, Long> {

}
