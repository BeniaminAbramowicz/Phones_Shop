package abramowicz.phonesshop.service;

import abramowicz.phonesshop.entities.User;

public interface UserService {

    User displayUser();
    User getUserByEmail(String email);
}
