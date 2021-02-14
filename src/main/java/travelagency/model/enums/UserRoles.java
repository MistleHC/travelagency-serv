package travelagency.model.enums;

import java.util.Arrays;
import java.util.List;

public enum UserRoles {
    GUEST(Arrays.asList(
            "/",
            "/login",
            "/registration",
            "/home"
    )),
    CUSTOMER(Arrays.asList(
            "/",
            "/logout",
            "/profile",
            "/home"
    )),
    MANAGER(Arrays.asList(
            "/",
            "/logout",
            "/profile",
            "/manage",
            "/home"
    )),
    ADMIN(Arrays.asList(
            "/",
            "/logout",
            "/profile",
            "/manage",
            "/home"
    ));

    private final List<String> authorities;

    UserRoles(List<String> authorities) {
        this.authorities = authorities;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    @Override
    public String toString() {
        return authorities.toString();
    }
}
