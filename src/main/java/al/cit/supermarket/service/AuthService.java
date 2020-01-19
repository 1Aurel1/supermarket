package al.cit.supermarket.service;

import al.cit.supermarket.model.User;
import al.cit.supermarket.model.authority.Authority;
import al.cit.supermarket.model.authority.AuthorityName;
import al.cit.supermarket.repository.AuthorityRepository;
import al.cit.supermarket.repository.UserRepository;
import al.cit.supermarket.service.dto.auth.UserRegistrationRequest;
import al.cit.supermarket.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    private UserMapper userMapper;

    @Autowired
    public AuthService(UserRepository userRepository, AuthorityRepository authorityRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.userMapper = userMapper;
    }

    public void createUser(UserRegistrationRequest request){

        User user = userMapper.userRegistrationRequestToUser(request);

        user.addAuthority(authorityRepository.findByName(AuthorityName.USER));

        userRepository.save(user);
    }
}
