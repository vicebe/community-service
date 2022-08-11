package communityService.unitTests;

import communityService.dtos.CommunityDTO;
import communityService.repositories.CommunityRepository;
import communityService.repositories.RoleRepository;
import communityService.repositories.UserRepository;
import communityService.services.CommunityService;
import communityService.utils.CommunityFactory;
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
public class CommunityServiceTest {

    @Mock
    CommunityRepository communityRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    CommunityService communityService;

    @Test
    void testGetAllCommunities() {

        List<CommunityDTO> expectedList = CommunityFactory.createCommunityDTOList();

        when(communityRepository.findAll()).thenReturn(CommunityFactory.createCommunityList());

        Assertions.assertEquals(expectedList, communityService.getAllCommunities());
    }

    @Test
    void testGetACommunity() {
        CommunityDTO expected = CommunityFactory.createCommunityDTO();

        when(communityRepository.findById(1L)).thenReturn(Optional.of(CommunityFactory.createCommunity()));

        Assertions.assertEquals(expected, communityService.getCommunity(1L));
    }

}
