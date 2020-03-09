package com.codegym.service.ipml;

import com.codegym.dao.entity.Role;
import com.codegym.dao.repository.RoleRepository;
import com.codegym.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    public Role findByid(Long id) {
        return roleRepository.findById(id).orElse(null);
    }
}
