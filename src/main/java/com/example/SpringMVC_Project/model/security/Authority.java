package com.example.SpringMVC_Project.model.security;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Table(name = "authority")
@NoArgsConstructor
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "authority_id")
    private Integer authorityId;
    private String authority;

    public Authority(Integer authorityId, String authority) {
        this.authorityId = authorityId;
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
