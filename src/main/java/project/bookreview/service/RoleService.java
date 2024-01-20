package project.bookreview.service;

import org.springframework.stereotype.Service;
import project.bookreview.repository.RoleRepository;


@Service
public class RoleService {
    private  final RoleRepository roleRepository;

    public RoleService( RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

//    public Role getRoleByName(RolesEnum roleName){
//        return roleRepository.findByRoleName(roleName);
//    }
}
