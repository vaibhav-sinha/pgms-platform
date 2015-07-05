package com.pgms.manager.security;

import com.pgms.service.api.OfficerService;
import com.pgms.shared.model.Officer;
import com.pgms.shared.util.Mapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
@Component
public class UserLoginService implements AuthenticationProvider {

    @Autowired
    OfficerService officerService;

    @Autowired
    Mapper mapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        if (StringUtils.isAnyBlank(username, password)) {
            throw new BadCredentialsException("Invalid username or password");
        }
        Officer officer = officerService.login(username, password);
        if(officer == null) {
            throw new BadCredentialsException("Invalid username or password");
        }
        return getUserDetail(officer);
    }

    public void authorize(UserDetail userDetails) {
        SecurityContextHolder.getContext().setAuthentication(userDetails);
    }

    public void authorizeLogin(Officer officer) {
        UserDetail userDetails = getUserDetail(officer);
        authorize(userDetails);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

    private UserDetail getUserDetail(Officer officer) {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetail userDetail = new UserDetail(grantedAuthorityList);
        mapper.map(officer, userDetail);
        userDetail.setAuthenticated(true);
        return userDetail;
    }
}
