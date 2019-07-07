package abramowicz.phonesshop.entities;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "name")
    @NotNull
    @Size(max = 45)
    private String name;

    @Column(name = "surname")
    @NotNull
    @Size(max = 45)
    private String surname;

    @Column(name = "email")
    @NotNull
    @Size(max = 100)
    private String email;

    @Column(name = "phone_number")
    @NotNull
    @Size(max = 9)
    private String phoneNumber;

    @Column(name = "password")
    @NotNull
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User(){
        //for JPA
    }
}
