package communityService.unitTests;

import communityService.dtos.CommunityDTO;
import communityService.dtos.UserDTO;
import communityService.models.User;
import communityService.repositories.CommunityRepository;
import communityService.repositories.RoleRepository;
import communityService.repositories.UserRepository;
import communityService.services.CommunityService;
import communityService.services.UserService;
import communityService.utils.CommunityFactory;
import communityService.utils.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    CommunityRepository communityRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    UserService userService;

    @Test
    public void testGetAllUsers() {

        List<UserDTO> expectedList = UserFactory.createUserDTOList();

        when(userRepository.findAll()).thenReturn(UserFactory.createUserList());

        Assertions.assertEquals(expectedList, userService.getAllUsers());
    }

    @Test
    public void testGetAUser() {

        UserDTO expected = UserFactory.createUserDTO();

        when(userRepository.findById(1L)).thenReturn(Optional.of(UserFactory.createUser()));

        Assertions.assertEquals(expected, userService.getUser(1L));
    }


}
