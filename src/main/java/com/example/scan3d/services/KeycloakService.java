package com.example.scan3d.services;

import com.example.scan3d.models.response.errors.BadRequestException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeycloakService {
    private final Keycloak keycloak;
    @Value("${keycloak.realm}")
    private String realm;

    public UUID createUser(String username, String email, String firstName, String lastName, String password) {

        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEnabled(true);
        user.setEmailVerified(false);

        Response response = keycloak.realm(realm).users().create(user);

        if (response.getStatus() != 201) {

            throw new BadRequestException("Create user failed");
        }

        String userId = CreatedResponseUtil.getCreatedId(response);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);

        keycloak.realm(realm)
                .users()
                .get(userId)
                .resetPassword(credential);

        return UUID.fromString(userId);
    }

    public void deleteUser(UUID userKeycloakId) {
        try {
            keycloak.realm(realm)
                    .users()
                    .get(userKeycloakId.toString())
                    .remove();
            log.info("Rolled back Keycloak user: {}", userKeycloakId);
        } catch (Exception ex) {
            log.error("Failed to rollback Keycloak user: {}. Manual cleanup required.", userKeycloakId, ex);
        }
    }
}
