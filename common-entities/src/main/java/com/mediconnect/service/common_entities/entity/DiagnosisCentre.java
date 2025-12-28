package com.mediconnect.service.common_entities.entity;

import com.mediconnect.service.common_entities.entity.Enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosisCentre extends UserEntity {

    @Column(nullable = false)
    private String city;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+Role.DIAGNOSIS_CENTRE.name()));
    }

//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }


//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        // return List.of(Roles.DIAGNOSIS_CENTRE.name()) -- this dos not works bcoz it is expecting list of granted authorities not string or enum
//        return List.of(new SimpleGrantedAuthority("ROLE_"+ Roles.DIAGNOSIS_CENTRE.name()));
//    }



}
