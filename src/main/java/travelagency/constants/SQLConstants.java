package travelagency.constants;

public class SQLConstants {
    //=================================================================================================================
    //UserDao Queries
    //=================================================================================================================

    public static final String GET_USER_BY_EMAIL = "SELECT id, email, name, password, aboutme, fullname " +
                                                   "FROM users " +
                                                   "WHERE email LIKE '%s'";

    public static final String GET_USER_ROLE_BY_USER_ID = "SELECT name " +
                                                          "FROM user_roles AS u INNER JOIN roles AS r ON u.role_id = r.id " +
                                                          "WHERE u.user_id = %s";

    public static final String INSERT_USER_WITH_NAME_EMAIL_PW = "INSERT INTO users (email, name, password) " +
                                                                "VALUES ('%1$s', '%2$s', '%3$s')";

    public static final String INSERT_ROLE_FOR_USER = "INSERT INTO user_roles (user_id, role_id) " +
                                                      "VALUES (%1$s, %2$s)";
}
