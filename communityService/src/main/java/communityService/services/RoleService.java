package communityService.services;

import communityService.dtos.RoleDTO;
import communityService.dtos.UserDTO;
import communityService.models.Role;
import communityService.models.User;
import communityService.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

            List<UserDTO> userDTOList = new ArrayList<>();
            role.getUsers().forEach(u -> userDTOList.add(new UserDTO(u.getUser_id())));

            RoleDTO roleDTO = new RoleDTO(role.getRole_id(), role.getRole_name(), userDTOList);
            roleDTOList.add(roleDTO);
        }

        return roleDTOList;
    }

    public RoleDTO getRole(Long role_id) {
        Role role = roleRepository.findById(role_id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));


        List<UserDTO> userDTOList = new ArrayList<>();
        role.getUsers().forEach(u -> userDTOList.add(new UserDTO(u.getUser_id())));
        return new RoleDTO(role.getRole_id(), role.getRole_name(), userDTOList);
    }

}
