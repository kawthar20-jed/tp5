package com.example.tp5.service;


import com.example.tp5.entity.Role;
import com.example.tp5.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Méthode pour créer un rôle
    public Role createRole(String roleName) {
        if (roleRepository.findByName(roleName) != null) {
            throw new IllegalArgumentException("Rôle déjà existant.");
        }

        Role role = new Role();
        role.setName(roleName);
        return roleRepository.save(role);
    }

    // Méthode pour récupérer tous les rôles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Méthode pour récupérer un rôle par son nom
    public Role getRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
