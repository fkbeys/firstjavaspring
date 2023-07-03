package com.kayaspring.kayaspring.Data.Repositories;

import com.kayaspring.kayaspring.Entities.Enums.UserRoleEnums;
import com.kayaspring.kayaspring.Entities.Models.User.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByName(UserRoleEnums name);
}