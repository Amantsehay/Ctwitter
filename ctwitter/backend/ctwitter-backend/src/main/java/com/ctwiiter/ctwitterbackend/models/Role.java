package com.ctwiiter.ctwitterbackend.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "role_id")
    private Integer roleId;

    private String authority; // when the table Role is created with the name of roles
    // the authority column will be named as authority
// A user has one authority and
    @ManyToMany(mappedBy = "authorities")
    private Set<TwitterUser> users;
    public Role () {
    }

    public Role (Integer roleId, String authority) {
        this.roleId = roleId;
        this.authority = authority;
    }

    public Integer getRoleId () {
        return roleId;
    }

    public void setRoleId (Integer roleId) {
        this.roleId = roleId;
    }

    public String getAuthority () {
        return authority;
    }

    public void setAuthority (String authority) {
        this.authority = authority;
    }

    @Override
    public String toString () {
        return "Role{" +
                "roleId=" + roleId +
                ", authority='" + authority + '\'' +
                '}';
    }
}
