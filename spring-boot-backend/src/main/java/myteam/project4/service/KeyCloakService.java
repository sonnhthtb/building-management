package myteam.project4.service;

import myteam.project4.model.UserDTO;
import org.keycloak.representations.AccessTokenResponse;

public interface KeyCloakService {

    UserDTO createUser(UserDTO userDTO);

    AccessTokenResponse login(UserDTO userDTO);

    void logoutUser(String userId);

    void resetPassword(String newPassword, String userId);
}
