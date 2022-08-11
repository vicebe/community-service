package communityService.utils;

import communityService.dtos.UserDTO;
import communityService.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserFactory {

    public static List<UserDTO> createUserDTOList() {
        List<UserDTO> userDTOList = new ArrayList<>();
        userDTOList.add(createUserDTO());
        return userDTOList;
    }

    public static UserDTO createUserDTO() {
        return new UserDTO(1L);
    }

    public static List<User> createUserList() {
        List<User> userList = new ArrayList<>();
        userList.add(createUser());
        return userList;
    }

    public static User createUser() {
        return new User(1L, new ArrayList<>());
    }
}
