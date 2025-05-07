package com.example.petproject.service.impl;

import com.example.petproject.dto.request.LoginRequest;
import com.example.petproject.dto.request.RegisterRequest;
import com.example.petproject.dto.respone.AuthResponse;
import com.example.petproject.entity.Product;
import com.example.petproject.entity.User;
import com.example.petproject.repository.ProductRepository;
import com.example.petproject.repository.UserRepository;
import com.example.petproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    // Constructor injection (best practice)
    @Autowired
    public UserServiceImpl(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public User register(RegisterRequest request) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã tồn tại!");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // TODO: mã hóa sau này
        user.setName(request.getName());
        user.setRole("USER");
        user.setActive(true);

        return userRepository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        if (loginRequest.getEmail() == null || loginRequest.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Email hoặc mật khẩu không đúng"));

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("Email hoặc mật khẩu không đúng");
        }

        // Tạo response
        AuthResponse authResponse = new AuthResponse();
        authResponse.setEmail(user.getEmail());
        authResponse.setName(user.getName());
        authResponse.setFullname(user.getName()); // Có thể chỉnh sửa nếu bạn có trường fullname riêng

        return authResponse;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void blockUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(false);
        userRepository.save(user);
    }


}
