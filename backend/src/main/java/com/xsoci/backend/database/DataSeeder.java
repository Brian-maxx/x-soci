package com.xsoci.backend.database;

import com.xsoci.backend.entity.Role;
import com.xsoci.backend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        seedRole("USER");
        seedRole("ADMIN");
    }

    private void seedRole(String name) {
        if (!roleRepository.existsByRoleName(name)) {
            Role role = Role.builder()
                    .roleName(name)
                    .isActive(true)
                    .build();
            roleRepository.save(role);
            System.out.println("Seed role: " + name);
        }
    }
}