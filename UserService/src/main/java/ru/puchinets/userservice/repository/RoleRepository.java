package ru.puchinets.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.puchinets.userservice.model.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
