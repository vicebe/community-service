package communityService.unitTests;

import communityService.dtos.CommunityDTO;
import communityService.dtos.RoleDTO;
import communityService.dtos.UserDTO;
import communityService.models.User;
import communityService.repositories.CommunityRepository;
import communityService.repositories.RoleRepository;
import communityService.repositories.UserRepository;
import communityService.services.CommunityService;
import communityService.services.RoleService;
import communityService.services.UserService;
import communityService.utils.CommunityFactory;
import communityService.utils.RoleFactory;
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
public class RoleServiceTest {

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    RoleService roleService;

    @Test
    public void testGetAllRoles() {
        List<RoleDTO> expectedList = RoleFactory.createRoleDTOList();

        when(roleRepository.findAll()).thenReturn(RoleFactory.createRoleList());

        Assertions.assertEquals(expectedList, roleService.getAllRoles());
    }

    @Test
    public void testGetARole() {
        RoleDTO expected = RoleFactory.createRoleDTO();

        when(roleRepository.findById(1L)).thenReturn(Optional.of(RoleFactory.createRole()));

        Assertions.assertEquals(expected, roleService.getRole(1L));
    }
}
