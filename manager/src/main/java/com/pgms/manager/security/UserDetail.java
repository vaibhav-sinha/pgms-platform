package com.pgms.manager.security;

import com.pgms.shared.model.AccountStatus;
import com.pgms.shared.model.Department;
import com.pgms.shared.model.Designation;
import com.pgms.shared.model.EntryStatus;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by user-1 on 28/6/15.
 */
public class UserDetail extends AbstractAuthenticationToken {

    private Long id;
    private EntryStatus entryStatus;
    private String name;
    private Designation designation;
    private Department department;
    private String username;
    private String encryptedPassword;
    private String salt;
    private String address;
    private String mobile;
    private String photoUrl;
    private Date lastSignedIn;
    private AccountStatus accountStatus;
    private String role;

    public UserDetail(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return encryptedPassword;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }
}
