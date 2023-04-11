package com.example.tien.Final.config.security;

import com.example.tien.Final.service.userdetail.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;


public class SecurityUtils {
	
	public static final UserDetailsImpl getPrincipal() {
        return (UserDetailsImpl) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
    }
}
