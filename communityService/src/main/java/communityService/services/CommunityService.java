package communityService.services;

import communityService.dtos.CommunityDTO;
import communityService.dtos.CreateCommunityDTO;
import communityService.dtos.RoleDTO;
import communityService.dtos.UserDTO;
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

            Role role2 = new Role();
            role2.setRole_name("users");
            role2.setUsers(new ArrayList<>());

            community.setRoles(new ArrayList<>());
            community.getRoles().add(role);
            community.getRoles().add(role2);

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

            List<UserDTO> userDTOList = new ArrayList<>();
            community.getUsers().forEach(u -> userDTOList.add(new UserDTO(u.getUser_id())));
            communityDTO.setUsers(userDTOList);

            List<RoleDTO> roleDTOList = new ArrayList<>();

            for (Role role : community.getRoles()) {
                List<UserDTO> userDTOList2 = new ArrayList<>();

                role.getUsers().forEach(u -> userDTOList2.add(new UserDTO(u.getUser_id())));

                RoleDTO roleDTO = new RoleDTO(role.getRole_id(), role.getRole_name(), userDTOList2);
                roleDTOList.add(roleDTO);
            }
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

            List<UserDTO> userDTOList = new ArrayList<>();
            community.getUsers().forEach(u -> userDTOList.add(new UserDTO(u.getUser_id())));
            communityDTO.setUsers(userDTOList);

            List<RoleDTO> roleDTOList = new ArrayList<>();
            for (Role role : community.getRoles()) {
                List<UserDTO> userDTOList2 = new ArrayList<>();

                role.getUsers().forEach(u -> userDTOList2.add(new UserDTO(u.getUser_id())));

                RoleDTO roleDTO = new RoleDTO(role.getRole_id(), role.getRole_name(), userDTOList2);
                roleDTOList.add(roleDTO);
            }
            communityDTO.setRoles(roleDTOList);

            return communityDTO;
        }
        return null;
    }

    public boolean joinCommunity(Long community_id, Long user_id) {
        Community community = communityRepository.findById(community_id).orElse(null);
        User user = userRepository.findById(user_id).orElse(null);
        boolean res = false;

        if (community != null && user != null) {
            if (community.getUsers().stream().noneMatch(u -> u.getUser_id().equals(user_id))) {
                community.getUsers().add(user);
                for (Role role: community.getRoles()) {
                    if (role.getRole_name().equals("users")) {
                        role.getUsers().add(user);
                        communityRepository.save(community);
                        res = true;
                    }
                }
            }
        }
        return res;
    }

    public boolean exitCommunity(Long community_id, Long user_id) {
        Community community = communityRepository.findById(community_id).orElse(null);
        User user = userRepository.findById(user_id).orElse(null);
        boolean res = false;

        if (community != null && user != null) {
            if (community.getUsers().stream().anyMatch(u -> u.getUser_id().equals(user_id))) {
                if (community.getRoles().get(0).getUsers().stream().anyMatch(u -> u.getUser_id().equals(user_id))) {
                    return false;
                }
                community.getUsers().remove(user);
                for (Role role: community.getRoles()) {
                    role.getUsers().remove(user);
                }
                res = true;
                communityRepository.save(community);
            }

        }
        return res;
    }

    public boolean changeCommunityName(Long community_id, Long user_id, String community_newName) {
        Community community = communityRepository.findById(community_id).orElse(null);
        User user = userRepository.findById(user_id).orElse(null);
        boolean res = false;

        if (community != null && user != null) {
            for (Role role : community.getRoles()) {
                if (role.getRole_name().equals("administrator")) {
                    if (role.getUsers().stream().anyMatch(u -> u.getUser_id().equals(user.getUser_id()))) {
                        community.setCommunity_name(community_newName);
                        communityRepository.save(community);
                        res = true;
                        break;
                    }
                }
            }
        }
        return res;
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
                    }
                }
            }
        }
        return res;
    }
}
