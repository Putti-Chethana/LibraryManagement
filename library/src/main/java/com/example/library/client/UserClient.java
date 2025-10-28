package com.example.library.client;

import com.example.library.dto.UserRequestDTO;
import com.example.library.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="user", path="users")
public interface UserClient {

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll();

    @GetMapping("/{username}")
    public ResponseEntity<UserResponseDTO> getByUserName(@PathVariable String username);

    @PostMapping
    public ResponseEntity<UserResponseDTO> add(@RequestBody UserRequestDTO userRequestDTO);

    @DeleteMapping("{username}")
    public ResponseEntity<Void> delete(@PathVariable String username);

    @PutMapping("{username}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable String username, @RequestBody UserRequestDTO userRequest);
}
