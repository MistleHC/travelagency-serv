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

    //=================================================================================================================
    //TourDao Queries
    //=================================================================================================================

    public static final String GET_TOURS_BY_COUNTRY =
                    "SELECT t.id, t.name, t.description, t.country, t.peoples, ht.name AS hotel_type, tt.name AS tour_type, t.price, t.is_hot " +
                    "FROM tours AS t " +
                    "INNER JOIN hotel_types AS ht ON t.hotel_type_id = ht.id " +
                    "INNER JOIN tour_types AS tt ON t.tour_type_id = tt.id " +
                    "WHERE t.country LIKE '%s';";

    public static final String GET_TOURS_BY_HOTEL_NAME =
                    "SELECT t.id, t.name, t.description, t.country, t.peoples, ht.name AS hotel_type, tt.name AS tour_type, t.price, t.is_hot " +
                    "FROM tours AS t " +
                    "INNER JOIN hotel_types AS ht ON t.hotel_type_id = ht.id " +
                    "INNER JOIN tour_types AS tt ON t.tour_type_id = tt.id " +
                    "WHERE ht.name LIKE '%s';";

    public static final String GET_TOURS_BY_COUNTRY_AND_HOTEL =
                    "SELECT t.id, t.name, t.description, t.country, t.peoples, ht.name AS hotel_type, tt.name AS tour_type, t.price, t.is_hot " +
                    "FROM tours AS t " +
                    "INNER JOIN hotel_types AS ht ON t.hotel_type_id = ht.id " +
                    "INNER JOIN tour_types AS tt ON t.tour_type_id = tt.id " +
                    "WHERE t.country LIKE '%1$s' AND ht.name LIKE '%2$s';";

    public static final String GET_ALL_TOURS =
                    "SELECT t.id, t.name, t.description, t.country, t.peoples, ht.name AS hotel_type, tt.name AS tour_type, t.price, t.is_hot " +
                    "FROM tours AS t " +
                    "INNER JOIN hotel_types AS ht ON t.hotel_type_id = ht.id " +
                    "INNER JOIN tour_types AS tt ON t.tour_type_id = tt.id;";
}
