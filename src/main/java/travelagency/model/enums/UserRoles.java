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
            "/home",
            "/order",
            "/order/delete"
    )),
    MANAGER(Arrays.asList(
            "/",
            "/logout",
            "/profile",
            "/manage",
            "/home",
            "/tour/hot",
            "/tour/de-hot",
            "/order/delete"
    )),
    ADMIN(Arrays.asList(
            "/",
            "/logout",
            "/profile",
            "/manage",
            "/home",
            "/tour/create",
            "/tour/delete",
            "/tour/hot",
            "/tour/de-hot",
            "/order/delete"
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
