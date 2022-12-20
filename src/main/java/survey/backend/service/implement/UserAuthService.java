package survey.backend.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import survey.backend.dto.RequestDto;
import survey.backend.entities.User;
import survey.backend.entities.UserRole;
import survey.backend.repository.UserRepository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName).get();      // Ici c'est le User de l'application
        // Il faudrait pour bien faire comme condition que l'utilisateur existe
        List<UserRole> userRoles = user.getUserRoles().stream().collect(Collectors.toList());
        List<GrantedAuthority> grantedAuthorities = userRoles.stream()
                .map(role -> {
                    return new SimpleGrantedAuthority(role.getRole());
                }).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails
                .User(userName, user.getUserPass(), grantedAuthorities);    // Ici c'est le User du springframework
    }

    public void saveUser(RequestDto request) {
        if (userRepository.findByUserName(request.getUserName()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        User user = new User();     // Ici c'est le User de l'application
        user.setUserName(request.getUserName());
        user.setUserPass(passwordEncoder.encode(request.getUserPass()));
        user.setUserRoles(request.getRoles().stream()
                .map(role -> {
                    UserRole urole = new UserRole();
                    urole.setRole(role);
                    return urole;
                }).collect(Collectors.toSet()));
        userRepository.save(user);
    }
}
