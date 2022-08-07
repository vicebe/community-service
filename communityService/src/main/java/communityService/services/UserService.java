package communityService.services;

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

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommunityRepository communityRepository;

    public void createUser() {
        User user = new User();
        userRepository.save(user);
    }

    public List<UserDTO> getAllUsers() {

        List<User> users = userRepository.findAll();

        List<UserDTO> userDTOList = new ArrayList<>();

        users.forEach(u -> userDTOList.add(new UserDTO(u.getUser_id())));

        return userDTOList;
    }

    public UserDTO getUser(Long user_id) {
        try {
            return new UserDTO(userRepository.findById(user_id).orElse(null).getUser_id());
        }
        catch (NullPointerException e) {
            return null;
        }
    }

    public boolean deleteUser(Long user_id) {
        boolean res = false;
        User user = userRepository.findById(user_id).orElse(null);
        if (user != null) {

            List<Community> communities = user.getCommunities();

            for (Community community:communities) {
                community.getUsers().remove(user);
                for (Role role: community.getRoles()) {
                    if (role.getRole_name().equals("administrator")) {
                        if (role.getUsers().stream().anyMatch(u -> user.getUser_id().equals(user.getUser_id()))) {
                            return false;
                        }
                    }
                }
            }

            for (Community community:communities) {
                community.getUsers().remove(user);
                for (Role role: community.getRoles()) {
                    role.getUsers().remove(user);
                }
            }

            userRepository.delete(user);
            res = true;
        }
        return res;
    }

}
