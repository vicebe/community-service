package communityService.services;

import communityService.dtos.UserDTO;
import communityService.models.Community;
import communityService.models.Role;
import communityService.models.User;
import communityService.repositories.CommunityRepository;
import communityService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        return new UserDTO(userRepository.findById(user_id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")).getUser_id());
    }

    public void deleteUser(Long user_id) {
        User user = userRepository.findById(user_id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        List<Community> communities = user.getCommunities();

        for (Community community:communities) {
            community.getUsers().remove(user);
            for (Role role: community.getRoles()) {
                if (role.getRole_name().equals("administrator")) {
                    if (role.getUsers().stream().anyMatch(u -> u.getUser_id().equals(user_id))) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user is admin of a community " +
                                "and can't be deleted");
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
    }

}
