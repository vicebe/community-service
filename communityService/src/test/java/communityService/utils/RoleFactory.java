package communityService.utils;

import communityService.dtos.CommunityDTO;
import communityService.dtos.RoleDTO;
import communityService.models.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleFactory {

    public static List<RoleDTO> createRoleDTOList() {
        List<RoleDTO> roleDTOList = new ArrayList<>();
        roleDTOList.add(createRoleDTO());
        return roleDTOList;
    }

    public static RoleDTO createRoleDTO() {
        return new RoleDTO(1L, "role", new ArrayList<>());
    }

    public static List<Role> createRoleList() {
        List<Role> roles = new ArrayList<>();
        roles.add(createRole());
        return roles;
    }

    public static Role createRole() {
        return new Role(1L, "role", new ArrayList<>());
    }

}
