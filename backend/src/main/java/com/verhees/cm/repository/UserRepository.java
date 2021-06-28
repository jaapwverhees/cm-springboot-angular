package com.verhees.cm.repository;

import com.verhees.cm.model.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserCredentialsUsername(String username);
}
