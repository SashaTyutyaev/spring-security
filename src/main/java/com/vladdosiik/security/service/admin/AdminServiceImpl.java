package com.vladdosiik.security.service.admin;

import com.vladdosiik.security.model.Role;
import com.vladdosiik.security.model.User;
import com.vladdosiik.security.model.dto.NewUserForAdmin;
import com.vladdosiik.security.model.dto.mapper.UserMapper;
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
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void createUser(NewUserForAdmin newUserForAdmin) {
        User user = userMapper.toUser(newUserForAdmin);
        user.setPassword(passwordEncoder.encode(newUserForAdmin.getPassword()));
        user.setRoles(Set.of(findRoleByName("ROLE_USER")));
        userRepository.save(user);
        log.info("Successfully create new newUserForAdmin by admin");
    }

    @Override
    @Transactional
    public void setRoleToUser(String userName, String roleName) {
        User user = findUserByName(userName);
        Role role = findRoleByName(roleName);
        user.getRoles().add(role);

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(String name) {
        User user = findUserByName(name);
        userRepository.delete(user);
        log.info("Successfully delete user with name {}", user.getName());
    }

    private User findUserByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private Role findRoleByName(String roleName) {
        return roleRepository.findByRole(roleName)
                .orElseThrow(() -> new UsernameNotFoundException("Role not found"));
    }
}
