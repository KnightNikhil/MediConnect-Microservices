package com.mediconnect.service.common_entities.entity;

import com.mediconnect.service.common_entities.entity.Enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.userdetails.UserDetails;


@Getter
@Setter
@MappedSuperclass
// @MappedSuperclass is a JPA annotation used to designate a class whose mapping information is applied to the entities that inherit from it. Because it is a @MappedSuperclass, no table named user_entity is created in the database.
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class UserEntity implements UserDetails {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public String getUsername() {
        return String.valueOf(id);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
