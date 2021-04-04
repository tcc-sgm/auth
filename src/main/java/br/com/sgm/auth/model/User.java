package br.com.sgm.auth.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    @Size(min = 3, max = 45, message = "Minimum name length: 3 Characters")
    private String name;

    @Column(unique = true, nullable = false)
    @Size(min = 4, max = 25, message = "Minimum username length: 4 Characters")
    private String username;

    @Column(unique = true, nullable = false)
    @Size(min = 11, max = 25, message = "Minimum cpf length: 11 Characters")
    private String cpf;

    @Size(min = 10)
    @Column(nullable = true)
    private String phone;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @Size(min = 8, message = "Minimum password length: 8 Characters")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    List<Role> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
