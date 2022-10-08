package tn.sharing.asserterproject.services.securityServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.sharing.asserterproject.entities.AppUser;
import tn.sharing.asserterproject.entities.Role;
import tn.sharing.asserterproject.repositories.UserRepo;


import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) {

        AppUser user = userRepo.findByEmail(email);

        log.info(user.toString() + " this user is from userRepo service implementation");

        List<Role> roles = user.getRoles();

        List<GrantedAuthority> authorities =  new ArrayList<>();
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName().toString()));
        });

        log.info(authorities.toString());


        return new User(user.getEmail(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(),authorities);

    }
}
