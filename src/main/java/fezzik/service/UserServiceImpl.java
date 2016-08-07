package fezzik.service;

import fezzik.domain.User;
import fezzik.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    public List<User> get(){
        return repository.findAll();
    }

}
