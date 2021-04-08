package br.com.sgm.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sgm.auth.exceptions.CustomException;
import br.com.sgm.auth.model.User;
import br.com.sgm.auth.model.dto.UserDTO;
import br.com.sgm.auth.model.dto.UserTokenDTO;
import br.com.sgm.auth.repository.UserRepository;
import br.com.sgm.auth.security.JwtTokenProvider;

@Service
public class UserService {

    private PasswordEncoder encoder;
    private UserRepository repository;
    private JwtTokenProvider jwtProvider;
    private AuthenticationManager authManager;

    @Autowired
    public UserService(PasswordEncoder encoder, UserRepository repository, JwtTokenProvider jwtProvider, AuthenticationManager authManager) {
        this.encoder = encoder;
        this.repository = repository;
        this.jwtProvider = jwtProvider;
        this.authManager = authManager;
    }

    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public UserTokenDTO signin(String username, String password) throws CustomException {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            UserTokenDTO dto = new UserTokenDTO();
            User user = repository.findByUsername(username);
            dto.setToken(jwtProvider.createToken(username, user.getRoles()));
            dto.setUser(new UserDTO(user));
            return dto;
        } catch (CustomException e) {
            throw new CustomException("Invalid Username/Password", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signup(User user) throws CustomException {
        if (repository.existsByUsername(user.getUsername())){
            throw new CustomException("Username is alredy in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        else {
            save(user);
            return jwtProvider.createToken(user.getUsername(), user.getRoles());
        }
    }

    public void delete(String username) {
        repository.deleteByUsername(username);
    }

    public UserDTO search(String username) throws CustomException {
        User user = repository.findByUsername(username);
        if(user != null)
            return new UserDTO(user);
        else
            throw new CustomException("Username is alredy in use", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public String refresh(String username) {
        return jwtProvider.createToken(username, repository.findByUsername(username).getRoles());
    }

}
