package com.app.restApiAndroid.models.usuario;

import com.app.restApiAndroid.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique=true)
    private String login;
    private String password;
    private UserRole role;



    public Usuario(String login, String password, UserRole role){
        this.login = login;
        this.password = password;
        this.role = role;

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMINS)
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMINS"),
                    new SimpleGrantedAuthority("ROLE_PAGES"),
                    new SimpleGrantedAuthority("ROLE_USERS"));
        else if(this.role == UserRole.PAGES)
            return List.of(
                    new SimpleGrantedAuthority("ROLE_PAGES"),
                    new SimpleGrantedAuthority("ROLE_USERS"));
        else
            return List.of(new SimpleGrantedAuthority("ROLE_USERS"));
    }

    @Override
    public String getUsername() {
        return this.login;
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
