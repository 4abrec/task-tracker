package tracking.system.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import tracking.system.auth.dto.MessageResponseDto;
import tracking.system.auth.dto.UserRegistrationDto;
import tracking.system.auth.service.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/signup")
    public ResponseEntity<MessageResponseDto> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        return new ResponseEntity<>(userService.registration(registrationDto), HttpStatus.OK);

    }

    @GetMapping("/me")
    @Secured("ROLE_ADMIN")
    public void me(Principal principal) {
        System.out.println(principal.getName());
    }

}
