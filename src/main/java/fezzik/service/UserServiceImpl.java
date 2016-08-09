package fezzik.service;

import fezzik.domain.User;
import fezzik.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The implementation of the service that interacts with the user repository.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
  
    public List<User> get(){
        return userRepository.findAll();
    }
    
    
    

}
