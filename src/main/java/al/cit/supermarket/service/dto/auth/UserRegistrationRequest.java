package al.cit.supermarket.service.dto.auth;

import lombok.Data;

@Data
public class UserRegistrationRequest {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
