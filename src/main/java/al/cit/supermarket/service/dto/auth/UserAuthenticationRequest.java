package al.cit.supermarket.service.dto.auth;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserAuthenticationRequest {

    private String name;
    private String surname;
    @Email
    private String email;
    private String username;
    private String password;
}
