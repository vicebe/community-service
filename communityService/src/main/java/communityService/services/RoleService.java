package communityService.services;

import communityService.dtos.RoleDTO;
import communityService.models.Role;
import communityService.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<RoleDTO> getAllRoles() {

        List<Role> roles = roleRepository.findAll();
        List<RoleDTO> roleDTOList = new ArrayList<>();

        for (Role role: roles) {
            RoleDTO roleDTO = new RoleDTO(role.getRole_id(), role.getRole_name(), role.getUsers());
            roleDTOList.add(roleDTO);
        }

        return roleDTOList;
    }

    public RoleDTO getRole(Long role_id) {
        Role role = roleRepository.findById(role_id).orElse(null);

        RoleDTO roleDTO = null;

        if (!(role == null)) {
            roleDTO = new RoleDTO(role.getRole_id(), role.getRole_name(), role.getUsers());
        }

        return roleDTO;
    }

}
