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
    @Column(name = "idUser")
    private int idUser;

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

    @Column(name = "phoneNumber")
    @NotNull
    @Size(max = 9)
    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idRoleFk", nullable = false)
    private Role role;

    public User(){

    }
}
