package tracking.system.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tracking.system.auth.domain.Role;
import tracking.system.auth.domain.User;
import tracking.system.auth.dto.MessageResponseDto;
import tracking.system.auth.dto.UserRegistrationDto;
import tracking.system.auth.exception.TaskTrackerException;
import tracking.system.auth.repo.RoleRepository;
import tracking.system.auth.repo.UserRepository;
import tracking.system.auth.service.UserService;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public MessageResponseDto registration(UserRegistrationDto userRegistrationDto) {
        if (userRepository.existsByUsername(userRegistrationDto.getUsername()))
            throw new TaskTrackerException("Ошибка: Данный пользователь уже зарегистрирован!", HttpStatus.BAD_REQUEST);

        User user = new User(passwordEncoder.encode(userRegistrationDto.getPassword()),
                userRegistrationDto.getUsername(), userRegistrationDto.getPhoneNumber(),
                userRegistrationDto.getFirstName(), userRegistrationDto.getLastName());

        Set<String> requestRoles = userRegistrationDto.getRoles();
        Set<Role> roles = new HashSet<>();

        for (String requestRole: requestRoles) {
            roles.add(roleRepository.findByName(requestRole)
                    .orElseThrow(() -> new TaskTrackerException("Role " + requestRole + " not found", HttpStatus.BAD_REQUEST)));
            }
        user.setRoles(roles);
        userRepository.save(user);
        return new MessageResponseDto("Пользователь " + user.getUsername() + " успешно зарегистрирован!");
    }
}
