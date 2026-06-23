package org.norman.user.entity;

// This is not a JPA entity as users
// are not stored in the database.
// This is a result of a REST call
// to the Keycloak Admin API to retrieve
// a list of users registered.
public record User(
        String id,
        String username,
        String firstName,
        String lastName,
        String email,
        Boolean emailVerified,
        Boolean enabled,
        Long createdTimestamp
) {
}
