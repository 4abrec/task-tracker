package tracking.system.auth.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserRegistrationDto {

    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String username;
    private Set<String> roles;

}
