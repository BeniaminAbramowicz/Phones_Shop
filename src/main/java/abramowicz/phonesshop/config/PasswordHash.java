package abramowicz.phonesshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHash {

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    PasswordHash(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        hash();
    }
    
    private static void hash(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String admin = passwordEncoder.encode("admin");
        System.out.println(admin);
    }
}
