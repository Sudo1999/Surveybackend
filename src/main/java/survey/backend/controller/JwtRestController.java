package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import survey.backend.dto.RequestDto;
import survey.backend.dto.ResponseDto;
import survey.backend.exception.DisabledUserException;
import survey.backend.exception.InvalidUserCredentialsException;
import survey.backend.auth.JwtUtil;
import survey.backend.service.implement.UserAuthService;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(value = "http://localhost:4200")
public class JwtRestController {
    // JwtRestController appelle la classe jwtUtil et aussi la fonction saveUser() du service UserAuthService
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signin")
    public ResponseEntity<ResponseDto> generateJwtToken(@RequestBody RequestDto request) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getUserPass()));
        } catch (DisabledException e) {
            throw new DisabledUserException("User Inactive");
        } catch (BadCredentialsException e) {
            throw new InvalidUserCredentialsException("Invalid Credentials");
        }
        org.springframework.security.core.userdetails.User user = (User) authentication.getPrincipal();
        // Le User est celui de org.springframework.security.core.userdetails (comme celui de UserAuthService)
        Set<String> roles = user.getAuthorities().stream().map(role -> role.getAuthority()).collect(Collectors.toSet());
        String token = jwtUtil.generateToken(authentication);

        ResponseDto response = new ResponseDto();
        response.setToken(token);
        response.setRoles(roles.stream().collect(Collectors.toList()));
        return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RequestDto request) {
        userAuthService.saveUser(request);
        return new ResponseEntity<String>("User successfully registered", HttpStatus.OK);
    }
}
