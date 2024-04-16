package br.com.tzuchaedahy.restwithspringboot.person.service;

import br.com.tzuchaedahy.restwithspringboot.person.controller.dto.request.AccountCredentialsDTO;
import br.com.tzuchaedahy.restwithspringboot.person.controller.dto.response.TokenDTO;
import br.com.tzuchaedahy.restwithspringboot.person.model.User;
import br.com.tzuchaedahy.restwithspringboot.person.repository.UserRepository;
import br.com.tzuchaedahy.restwithspringboot.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> authenticate(AccountCredentialsDTO accountCredentialsDTO) {
        try {
            String userName = accountCredentialsDTO.getUserName();
            String password = accountCredentialsDTO.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

            User user = userRepository.findByUsername(userName);

            TokenDTO token;

            if (user != null) {
                token = jwtTokenProvider.createTokenDTO(user.getUserName(), user.getRoles());
            } else {
                throw new UsernameNotFoundException("username " + userName + " not found");
            }

            return ResponseEntity.ok(token);
        } catch (Exception exception) {
            throw new BadCredentialsException("invalid username or password");
        }
    }

    public ResponseEntity refreshToken(String refreshToken, String userName) {
        User user = userRepository.findByUsername(userName);

        if (user == null) {
            throw new UsernameNotFoundException("username " + userName + " not found");
        }

        TokenDTO token = jwtTokenProvider.refreshToken(refreshToken);
        return ResponseEntity.ok(token);
    }
}
