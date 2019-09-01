package abramowicz.phonesshop.service;

import abramowicz.phonesshop.entities.User;
import abramowicz.phonesshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){this.userRepository = userRepository;}

    @Override
    public User displayUser() {
        return userRepository.getUser();
    }
}
