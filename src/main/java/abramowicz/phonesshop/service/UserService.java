package abramowicz.phonesshop.service;

import abramowicz.phonesshop.entities.User;

public interface UserService {

    User getUserByEmail(String email);
}
