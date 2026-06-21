package org.norman.risks.user.model;

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
