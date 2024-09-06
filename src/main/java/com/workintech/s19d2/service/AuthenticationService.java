package com.workintech.s19d2.service;

import com.workintech.s19d2.repository.MemberRepository;
import com.workintech.s19d2.repository.RoleRepository;

import com.workintech.s19d2.entity.Member;
import com.workintech.s19d2.entity.Role;
import lombok.AllArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class AuthenticationService {

    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_USER = "USER";

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public Member register(String email, String password) {
        Optional<Member> memberOptional = memberRepository.findByEmail(email);

        if (memberOptional.isPresent()) {
            throw new RuntimeException("User with given email already exists! Email: " + email);
        }

        String encodedPassword = passwordEncoder.encode(password);

        List<Role> roleList = new ArrayList<>();
        addRoleAdmin(roleList);

        Member newMember = new Member();
        newMember.setEmail(email);
        newMember.setPassword(encodedPassword);
        newMember.setRoles(roleList);

        return memberRepository.save(newMember);
    }

    private void addRoleUser(List<Role> roleList) {
        Optional<Role> roleUser = roleRepository.findByAuthority(ROLE_USER);

        if (!roleUser.isPresent()) {
            Role roleUserEntity = new Role();
            roleUserEntity.setAuthority(ROLE_USER);
            roleList.add(roleRepository.save(roleUserEntity));
        } else {
            roleList.add(roleUser.get());
        }
    }

    private void addRoleAdmin(List<Role> roleList) {
        Optional<Role> roleAdmin = roleRepository.findByAuthority(ROLE_ADMIN);

        if (roleAdmin.isEmpty()) {
            Role roleAdminEntity = new Role();
            roleAdminEntity.setAuthority(ROLE_ADMIN);
            roleList.add(roleRepository.save(roleAdminEntity));
        } else {
            roleList.add(roleAdmin.get());
        }
    }
}
