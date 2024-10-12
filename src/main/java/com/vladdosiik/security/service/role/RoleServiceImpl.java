package com.vladdosiik.security.service.role;

import com.vladdosiik.security.model.Role;
import com.vladdosiik.security.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    @PostConstruct
    @Transactional
    public void addRolesToDatabase() {
        if (roleRepository.findAll().isEmpty()) {
            roleRepository.save(Role.builder()
                    .role("ROLE_USER")
                    .build());
            roleRepository.save(Role.builder()
                    .role("ROLE_ADMIN")
                    .build());
            log.info("Successfully added roles to the database");
        } else {
            log.info("Roles already in database");
        }
    }
}
