package com.example.library.security;

import com.example.library.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

/**
 * The AuthUserDetails class represents a custom UserDetails implementation for authentication purposes
 * in the library application. It encapsulates user information and implements the UserDetails interface.
 */
public record AuthUserDetails(User user) implements UserDetails {

    /**
     * Returns an empty set of granted authorities since the library application does not use role-based access control.
     *
     * @return An empty set of GrantedAuthority objects.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }

    /**
     * Retrieves the user's password.
     *
     * @return The user's password.
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Retrieves the user's email, which serves as the username for authentication.
     *
     * @return The user's email.
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /**
     * Indicates that the user's account never expires.
     *
     * @return True, indicating that the user's account is non-expirable.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates that the user's account is always non-locked.
     *
     * @return True, indicating that the user's account is non-locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates that the user's credentials (password) never expire.
     *
     * @return True, indicating that the user's credentials are non-expirable.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates that the user's account is always enabled.
     *
     * @return True, indicating that the user's account is enabled.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
