package tracking.system.auth.service;

import tracking.system.auth.domain.User;
import tracking.system.auth.dto.MessageResponseDto;
import tracking.system.auth.dto.UserRegistrationDto;

public interface UserService {

    User save(User user);

    MessageResponseDto registration(UserRegistrationDto userRegistrationDto);
}
