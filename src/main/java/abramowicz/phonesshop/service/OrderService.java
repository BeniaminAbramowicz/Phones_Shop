package abramowicz.phonesshop.service;

import abramowicz.phonesshop.entities.User;

public interface OrderService {

    void createOrder(int userId);
    void test();
    User displayUser();
}
