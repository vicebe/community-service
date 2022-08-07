package communityService.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommunityDTO {

    private Long community_id;
    private String community_name;
    List<UserDTO> users;
    List<RoleDTO> roles;

}
