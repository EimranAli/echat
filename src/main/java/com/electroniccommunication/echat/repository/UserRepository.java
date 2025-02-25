package com.electroniccommunication.echat.repository;

import com.electroniccommunication.echat.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
