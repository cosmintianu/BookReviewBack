package project.bookreview.requests;


import lombok.Data;
import project.bookreview.domain.Role;


import java.util.Set;

@Data
public class UserRequest {
    public String username;
    private String password;
    private String email;
    private int age;
    private Set<Role> roles;
}
