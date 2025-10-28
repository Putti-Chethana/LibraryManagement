package com.example.user.service;

import com.example.user.dto.UserRequestDTO;
import com.example.user.dto.UserResponseDTO;
import com.example.user.exception.UserNotFoundException;
import com.example.user.model.User;
import com.example.user.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {
    private final UserRepo userRepo;
    private final ModelMapper mapper;

    @Autowired
    public UserService(UserRepo userRepo, ModelMapper mapper) {
        this.userRepo = userRepo;
        this.mapper = mapper;
    }

    public List<UserResponseDTO> getAll() {
        return userRepo.findAll().stream().map(user -> mapper.map(user, UserResponseDTO.class)).toList();
    }

    public UserResponseDTO getByUserName(String name) {
        System.out.println("feign client communication is done");
        User user = userRepo.findById(name).orElseThrow(()-> new UserNotFoundException("user with userName is not found in userservice..."));
        System.out.println("chethana");
        System.out.println(user.getUsername());
        return mapper.map(user, UserResponseDTO.class);
    }

    public UserResponseDTO add(UserRequestDTO userRequestDTO) {
        User user = mapper.map(userRequestDTO, User.class);
        userRepo.save(user);
        return mapper.map(user, UserResponseDTO.class);
    }

    public void delete(String name) {
        User user = userRepo.findById(name).orElseThrow(()-> new UserNotFoundException("User not found"));
        userRepo.delete(user);
    }

    public UserResponseDTO update(String userName, UserRequestDTO userRequest) {
        User user = userRepo.findById(userName).orElseThrow(()-> new UserNotFoundException("User not found"));

//        if (userRequest.getUsername()!=null && !userRequest.getUsername().isBlank()) {
//            user.setUsername(userRequest.getUsername());
//        }

        if (userRequest.getName()!=null && !userRequest.getName().isBlank()) {
            user.setName(userRequest.getName());
        }

        if (userRequest.getEmail()!=null && !userRequest.getEmail().isBlank()) {
            user.setEmail(userRequest.getEmail());
        }

        userRepo.save(user);

        return mapper.map(user, UserResponseDTO.class);
    }
}
