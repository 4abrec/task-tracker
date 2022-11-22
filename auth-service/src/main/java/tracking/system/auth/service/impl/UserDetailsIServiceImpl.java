package tracking.system.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import tracking.system.auth.domain.User;
import tracking.system.auth.exception.TaskTrackerException;
import tracking.system.auth.repo.UserRepository;
import tracking.system.auth.service.impl.UserDetailsImpl;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsIServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new TaskTrackerException("Пользователь с логином: " + username + " не найден", HttpStatus.BAD_REQUEST));
        System.out.println(user);
        return UserDetailsImpl.build(user);
    }
}
