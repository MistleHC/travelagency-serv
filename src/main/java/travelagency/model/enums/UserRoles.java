package travelagency.model.enums;

import java.util.Arrays;
import java.util.List;

public enum UserRoles {
    GUEST(Arrays.asList(
            "/",
            "/login",
            "/registration"
    )),
    CUSTOMER(Arrays.asList(
            "/",
            "/logout",
            "/profile"
    )),
    MANAGER(Arrays.asList(
            "/",
            "/logout",
            "/profile",
            "/manage"
    )),
    ADMIN(Arrays.asList(
            "/",
            "/logout",
            "/profile",
            "/manage"
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
