package br.com.sgm.auth.model.dto;

import br.com.sgm.auth.model.Role;
import br.com.sgm.auth.model.User;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

public class UserDTO {

    @ApiModelProperty(position = 0)
    private String name;
    @ApiModelProperty(position = 1)
    private String username;
    @ApiModelProperty(position = 2)
    private String cpf;
    @ApiModelProperty(position = 3)
    private String phone;
    @ApiModelProperty(position = 4)
    private String email;
    @ApiModelProperty(position = 5)
    private String password;
    @ApiModelProperty(position = 6)
    List<Role> roles;

    public UserDTO() { }
    
    public UserDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.roles = user.getRoles();
        this.cpf = user.getCpf();
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

    public User toUser() {
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setCpf(cpf);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(roles);
        return user;
    }
}
