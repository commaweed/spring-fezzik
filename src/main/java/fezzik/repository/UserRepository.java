package fezzik.repository;

import fezzik.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface UserRepository extends MongoRepository<User, String> {

    public User findByFirstName(String firstName);

    public List<User> findByLastName(String lastName);

}