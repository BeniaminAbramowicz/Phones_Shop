package abramowicz.phonesshop.service;

import abramowicz.phonesshop.repositories.AdminProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminProductRepository adminRepository;

    @Override
    public void deleteProduct(int id) {
        adminRepository.deleteProduct(id);
    }
}
