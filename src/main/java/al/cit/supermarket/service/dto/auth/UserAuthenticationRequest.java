package al.cit.supermarket.service.dto.auth;

import lombok.Data;

@Data
public class UserAuthenticationRequest {

    private String username;
    private String password;
}
