package myteam.project4.controller;

import lombok.RequiredArgsConstructor;
import myteam.project4.model.UserDTO;
import myteam.project4.service.KeyCloakService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("public-api/v1.0.0/users")
@RequiredArgsConstructor
public class UserController {

    private final KeyCloakService keycloakService;

    @PostMapping(path = "/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(keycloakService.createUser(userDTO));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(keycloakService.login(userDTO));
    }

    @GetMapping(value = "/logout")
    public ResponseEntity<?> logoutUser(HttpServletRequest request) {

        request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        AccessToken token = ((KeycloakPrincipal<?>) request.getUserPrincipal()).getKeycloakSecurityContext().getToken();

        String userId = token.getSubject();

        keycloakService.logoutUser(userId);

        return new ResponseEntity<>("Hi!, you have logged out successfully!", HttpStatus.OK);

    }

    @GetMapping(value = "/update/password")
    public ResponseEntity<?> updatePassword(HttpServletRequest request, String newPassword) {

        request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        AccessToken token = ((KeycloakPrincipal<?>) request.getUserPrincipal()).getKeycloakSecurityContext().getToken();

        String userId = token.getSubject();

        keycloakService.resetPassword(newPassword, userId);

        return new ResponseEntity<>("Hi!, your password has been successfully updated!", HttpStatus.OK);

    }

}
