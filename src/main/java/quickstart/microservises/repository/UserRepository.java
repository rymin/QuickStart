package quickstart.microservises.repository;

import quickstart.microservises.domain.Role;
import quickstart.microservises.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserRepository {

    private List<User> userList = Arrays.asList(
            User.builder()
                    .username("user")
                    .password(new BCryptPasswordEncoder().encode("password"))
                    .authorities(Collections.singletonList(Role.USER))
                    .enabled(true)
                    .accountNonExpired(true)
                    .credentialsNonExpired(true)
                    .accountNonLocked(true)
                    .build(),

            User.builder()
                    .username("user1")
                    .password(new BCryptPasswordEncoder().encode("password1"))
                    .authorities(Collections.singletonList(Role.USER))
                    .enabled(true)
                    .accountNonExpired(true)
                    .credentialsNonExpired(true)
                    .accountNonLocked(true)
                    .build()
    );


    public User findByUsername(String name) {
        User res = null;
        for (User user : userList) {
            if (name.equals(user.getUsername())) {
                res = user;
                break;
            }
        }
        return res;
    }

}
