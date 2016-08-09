package fezzik.service;

import fezzik.domain.User;

import java.util.List;

/**
 * Represents the business logic for interacting with the user repository.
 */
public interface UserService {

    List<User> get();

}
