package br.com.sgm.auth.controller;

import br.com.sgm.auth.exceptions.CustomException;
import br.com.sgm.auth.model.dto.LoginRequestDTO;
import br.com.sgm.auth.model.dto.UserDTO;
import br.com.sgm.auth.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@Api(tags = "users")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/signin")
    @ApiOperation(value = "${user-controller.signin}")
    @ApiResponses(value = {
            @ApiResponse(code = 422, message = "Invalid username/passowrd")
    })
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO login) {
        try {
            String token = service.signin(login.getUsername(), login.getPassword());
            return ResponseEntity.ok().body(token);
        } catch (CustomException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PostMapping("/signup")
    @ApiOperation(value = "${user-controller.signup}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 422, message = "Username is already in user")
    })
    public ResponseEntity<?> signUp(@ApiParam("New User") @RequestBody UserDTO userDTO) {
        try {
            String token = service.signup(userDTO.toUser());
            return ResponseEntity.ok().body(token);
        } catch (CustomException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @DeleteMapping("/{username}")
    @Secured({"ROLE_ADMIN"})
    @ApiOperation(value = "${user-controller.delete}", authorizations = {@Authorization(value = "apikey")})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 422, message = "Username is already in user")
    })
    public ResponseEntity<?> delete(@ApiParam("username") @PathVariable String username) {
        try {
            service.delete(username);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{username}")
    @Secured({"ROLE_ADMIN"})
    @ApiOperation(value = "${user-controller.search}", authorizations = {@Authorization(value = "apikey")})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Expired or invalid token")
    })
    public ResponseEntity<?> search(@ApiParam("username") @PathVariable String username) {
        try {
            UserDTO user = service.search(username);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/refresh")
    @Secured({"ROLE_ADMIN","ROLE_USER", "ROLE_CITIZEN"})
    public ResponseEntity<?> refresh(HttpServletRequest req) {
        try {
            String token = service.refresh(req.getRemoteUser());
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
