package communityService.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "communities")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long community_id;

    @Column
    private String community_name;

    @ManyToMany()
    private List<User> users;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Role> roles;
}
