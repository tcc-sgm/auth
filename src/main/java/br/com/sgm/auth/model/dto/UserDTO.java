package br.com.sgm.auth.model.dto;

import br.com.sgm.auth.model.Role;
import br.com.sgm.auth.model.User;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

public class UserDTO {

    @ApiModelProperty(position = 0)
    private String username;
    @ApiModelProperty(position = 1)
    private String email;
    @ApiModelProperty(position = 2)
    private String password;
    @ApiModelProperty(position = 3)
    List<Role> roles;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.roles = user.getRoles();
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

    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(roles);
        return user;
    }
}
