package br.com.challenge.forumhub.controller;

import br.com.challenge.forumhub.dto.LoginDTO;
import br.com.challenge.forumhub.dto.TokenDTO;
import br.com.challenge.forumhub.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginDTO loginDTO) {
        var authToken = new UsernamePasswordAuthenticationToken(
                loginDTO.username(), loginDTO.password()
        );

        var authentication = authenticationManager.authenticate(authToken);

        String token = tokenService.gerarToken(loginDTO.username());

        return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
    }
}