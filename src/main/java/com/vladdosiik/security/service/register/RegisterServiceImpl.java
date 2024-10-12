package com.vladdosiik.security.service.register;

import com.vladdosiik.security.model.Role;
import com.vladdosiik.security.model.User;
import com.vladdosiik.security.repository.RoleRepository;
import com.vladdosiik.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(findRoleByName("ROLE_USER")));
        User savedUser = userRepository.save(user);
        log.info("Successfully Registered user: {}", savedUser);
    }

    private Role findRoleByName(String roleName) {
        return roleRepository.findByRole(roleName)
                .orElseThrow(() -> new UsernameNotFoundException("Role not found"));
    }
}
