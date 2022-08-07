package communityService.services;

import communityService.dtos.CommunityDTO;
import communityService.dtos.CreateCommunityDTO;
import communityService.dtos.RoleDTO;
import communityService.models.Community;
import communityService.models.Role;
import communityService.models.User;
import communityService.repositories.CommunityRepository;
import communityService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CommunityService {

    @Autowired
    CommunityRepository communityRepository;

    @Autowired
    UserRepository userRepository;

    public boolean createCommunity(CreateCommunityDTO communityDTO) {

        User user = userRepository.findById(communityDTO.getAdmin_id()).orElse(null);

        if (user != null) {
            Community community = new Community();

            community.setCommunity_name(communityDTO.getCommunity_name());
            community.setUsers(new ArrayList<>());
            community.getUsers().add(user);

            Role role = new Role();
            role.setRole_name("administrator");
            role.setUsers(new ArrayList<>());
            role.getUsers().add(user);

            community.setRoles(new ArrayList<>());
            community.getRoles().add(role);

            communityRepository.save(community);

            return true;
        }
        return false;
    }

    public List<CommunityDTO> getAllCommunities() {

        List<Community> communities = communityRepository.findAll();

        List<CommunityDTO> communityDTOS = new ArrayList<>();

        for(Community community: communities) {
            CommunityDTO communityDTO = new CommunityDTO();
            communityDTO.setCommunity_id(community.getCommunity_id());
            communityDTO.setCommunity_name(community.getCommunity_name());
            communityDTO.setUsers(community.getUsers());

            List<RoleDTO> roleDTOList = new ArrayList<>();
            community.getRoles().forEach(r -> roleDTOList.add(new RoleDTO(r.getRole_id(), r.getRole_name(),
                    r.getUsers())));
            communityDTO.setRoles(roleDTOList);

            communityDTOS.add(communityDTO);
        }

        return communityDTOS;
    }

    public CommunityDTO getCommunity(Long community_id) {

        Community community = communityRepository.findById(community_id).orElse(null);

        if (community != null) {
            CommunityDTO communityDTO = new CommunityDTO();
            communityDTO.setCommunity_id(community.getCommunity_id());
            communityDTO.setCommunity_name(community.getCommunity_name());
            communityDTO.setUsers(community.getUsers());

            List<RoleDTO> roleDTOList = new ArrayList<>();
            community.getRoles().forEach(r -> roleDTOList.add(new RoleDTO(r.getRole_id(), r.getRole_name(),
                    r.getUsers())));
            communityDTO.setRoles(roleDTOList);

            return communityDTO;
        }
        return null;
    }

    public boolean deleteCommunity(Long community_id, Long admin_id) {

        Community community = communityRepository.findById(community_id).orElse(null);

        boolean res = false;

        if (community != null) {
            for(Role role: community.getRoles()) {
                if (role.getRole_name().equals("administrator")) {
                    if (role.getUsers().stream().anyMatch(user -> user.getUser_id().equals(admin_id))) {
                        communityRepository.delete(community);
                        res = true;
                        break;
                    }
                }
            }
        }
        return res;
    }

}
