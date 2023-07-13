package antifraud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/user")
    public ResponseEntity<RegistrationResponse> register(@RequestBody RegistrationRequest request) {
        authenticationService.register(request);
        return ResponseEntity<RegistrationResponse>.
                .name(request.getName())
                .username(request.getName())
                .password(request.getPassword())
                .build();
    }

}
