package com.example.petproject.service.impl;

import com.example.petproject.dto.request.LoginRequest;
import com.example.petproject.dto.request.UserRegistrationRequest;
import com.example.petproject.dto.respone.AuthResponse;
import com.example.petproject.dto.respone.UserResponse;
import com.example.petproject.entity.Cart;
import com.example.petproject.entity.Product;
import com.example.petproject.entity.User;
import com.example.petproject.exception.*;
import com.example.petproject.repository.CartRepository;
import com.example.petproject.repository.ProductRepository;
import com.example.petproject.repository.UserRepository;
import com.example.petproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public UserResponse registerUser(UserRegistrationRequest request) {
        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new APIException("Email đã được đăng ký", 409);
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullname(request.getFullname());
        user.setStatus("active");
        user.setRole("user");

        User savedUser = userRepository.save(user);

        // Create empty cart for new user
        Cart cart = new Cart();
        cart.setUser(savedUser);
        cartRepository.save(cart);

        return modelMapper.map(savedUser, UserResponse.class);
    }

    @Override
    public UserResponse loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new APIException("Email hoặc mật khẩu không đúng", 401));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new APIException("Email hoặc mật khẩu không đúng", 401);
        }

        // Check if user is active
        if (!"active".equals(user.getStatus())) {
            throw new APIException("Tài khoản của bạn đã bị khóa", 403);
        }

        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new APIException("Không tìm thấy người dùng với ID: " + id, 404));
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new APIException("Không tìm thấy người dùng với email: " + email, 404));
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse updateUser(Long id, UserRegistrationRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new APIException("Không tìm thấy người dùng với ID: " + id, 404));

        user.setFullname(request.getFullname());
        user.setEmail(request.getEmail());

        // Nếu password mới được cung cấp, thì cập nhật
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        // Nếu muốn cập nhật status hoặc role thì thêm vào đây
        // user.setStatus(request.getStatus());
        // user.setRole(request.getRole());

        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserResponse.class);
    }


    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new APIException("Không tìm thấy người dùng với ID: " + id, 404));

        // Xóa giỏ hàng nếu có
//        cartRepository.deleteByUser(user);
        userRepository.delete(user);
    }

}
