package com.crackerStudents.projectApp.DTO;

import com.crackerStudents.projectApp.domain.Pack;
import com.crackerStudents.projectApp.domain.Role;
import com.crackerStudents.projectApp.domain.Session;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @Author Krylov Sergey
 */
public class UserDTO {
    private UUID id;
    private String username;
    private String password;
    private String email;
    //  activationCode - не нужен
    private String firstName;
    private String lastName;
    private String avatar;
    private boolean active;
    private Set<Role> roles;
    private List<Pack> packs;
    private List<Session> sessions;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Pack> getPacks() {
        return packs;
    }

    public void setPacks(List<Pack> packs) {
        this.packs = packs;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}
