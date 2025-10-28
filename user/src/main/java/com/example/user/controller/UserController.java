package com.example.user.controller;

import com.example.user.dto.UserRequestDTO;
import com.example.user.dto.UserResponseDTO;
import com.example.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{username}")
    public ResponseEntity<UserResponseDTO> getByUserName(@PathVariable String username) {
        return new ResponseEntity<>(userService.getByUserName(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> add(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userService.add(userRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("{username}")
    public ResponseEntity<Void> delete(@PathVariable String username) {
        userService.delete(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{username}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable String username, @RequestBody UserRequestDTO userRequest) {
        return new ResponseEntity<>(userService.update(username, userRequest), HttpStatus.OK);
    }
}
