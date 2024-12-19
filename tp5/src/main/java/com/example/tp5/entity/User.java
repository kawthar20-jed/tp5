package com.example.tp5.entity;

import jakarta.persistence.*;

import java.util.Optional;
import java.util.Set;

@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

	public void setPassword(String encode) {
		// TODO Auto-generated method stub
		
	}

	public void setUsername(String username2) {
		// TODO Auto-generated method stub
		
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<User> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}
}
