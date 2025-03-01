package vn.spring.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.spring.bookstore.dto.request.UserCreationRequest;
import vn.spring.bookstore.dto.request.UserUpdateRequest;
import vn.spring.bookstore.entities.User;
import vn.spring.bookstore.exception.AppException;
import vn.spring.bookstore.exception.ErrorCode;
import vn.spring.bookstore.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request){
        User user = new User();

        //Check exist gmail
        if (userRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.USER_EXISTED);

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(Long userId, UserUpdateRequest request) {
        User user = getUser(userId);

        user.setFullName(request.getFullName());
        user.setPassword(request.getPassword());
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(userId);
    }
}
