package com.mofof.service;

import com.mofof.entity.system.Perm;
import com.mofof.entity.system.Role;
import com.mofof.repository.PermRepository;

import org.springframework.stereotype.Service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PermService {
  @Autowired
  PermRepository permRepository;

  public Set<Perm> getRolePerms(Role role) {
    return permRepository.findPermsByRoleId(role.getId());
  }

  public Role addPerms(Role role, Set<Perm> perms) {
    for (Perm perm : perms) {
      perm.setRole(role);
    }
    return role;
  }
}