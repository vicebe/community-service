package communityService.dtos;

import communityService.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleDTO {

    private Long role_id;
    private String role_name;
    private List<User> users;

}
