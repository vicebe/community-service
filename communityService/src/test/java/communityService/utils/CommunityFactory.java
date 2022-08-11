package communityService.utils;

import communityService.dtos.CommunityDTO;
import communityService.dtos.RoleDTO;
import communityService.dtos.UserDTO;
import communityService.models.Community;
import communityService.models.Role;
import communityService.models.User;

import java.util.ArrayList;
import java.util.List;

public class CommunityFactory {

    public static List<CommunityDTO> createCommunityDTOList() {

        List<CommunityDTO> communityDTOList = new ArrayList<>();
        communityDTOList.add(createCommunityDTO());
        return communityDTOList;
    }

    public static CommunityDTO createCommunityDTO() {
        CommunityDTO communityDTO = new CommunityDTO();

        communityDTO.setCommunity_id(1L);
        communityDTO.setCommunity_name("community");

        List<UserDTO> userDTOList = new ArrayList<>();
        communityDTO.setUsers(userDTOList);

        List<RoleDTO> roleDTOList = new ArrayList<>();
        communityDTO.setRoles(roleDTOList);

        return communityDTO;
    }

    public static List<Community> createCommunityList() {
        List<Community> communities = new ArrayList<>();
        communities.add(createCommunity());
        return communities;
    }

    public static Community createCommunity() {
        Community community = new Community();

        community.setCommunity_id(1L);
        community.setCommunity_name("community");

        List<User> userList = new ArrayList<>();
        community.setUsers(userList);

        List<Role> roleList = new ArrayList<>();
        community.setRoles(roleList);

        return community;
    }

}
