package al.cit.supermarket.service.dto.auth;

import lombok.Data;

@Data
public class UserAuthenticationRequest {

    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
}
