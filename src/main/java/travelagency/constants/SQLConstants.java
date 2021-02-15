package travelagency.constants;

public class SQLConstants {
    //=================================================================================================================
    //UserDao Queries
    //=================================================================================================================

    public static final String GET_USER_BY_EMAIL =
                    "SELECT id, email, name, password, aboutme, fullname " +
                    "FROM users " +
                    "WHERE email LIKE '%s'";

    public static final String GET_USER_ROLE_BY_USER_ID =
                    "SELECT name " +
                    "FROM user_roles AS u INNER JOIN roles AS r ON u.role_id = r.id " +
                    "WHERE u.user_id = %s";

    public static final String INSERT_USER_WITH_NAME_EMAIL_PW =
                    "INSERT INTO users (email, name, password) " +
                    "VALUES ('%1$s', '%2$s', '%3$s')";

    public static final String INSERT_ROLE_FOR_USER =
                    "INSERT INTO user_roles (user_id, role_id) " +
                    "VALUES (%1$s, %2$s)";

    public static final String UPDATE_USER_ADDITIONAL_INFO =
                    "UPDATE users " +
                    "SET aboutme = '%1$s', fullname = '%2$s' " +
                    "WHERE id = %3$s;";

    //=================================================================================================================
    //OrderDao Queries
    //=================================================================================================================

    public static final String GET_ORDERS_BY_CUSTOMER_ID =
                    "SELECT t.id, t.name, t.description, t.country, t.peoples, ht.name AS hotel_type, tt.name AS tour_type, t.price, t.is_hot, " +
                        "ord.id AS order_id, ord.customer_id AS order_customer_id, st.id AS status_id, st.title AS status_title, " +
                        "us.id AS user_id, us.email AS user_email, us.name AS user_name " +
                    "FROM tours AS t " +
                    "INNER JOIN hotel_types AS ht ON t.hotel_type_id = ht.id " +
                    "INNER JOIN tour_types AS tt ON t.tour_type_id = tt.id " +
                    "INNER JOIN orders AS ord ON t.id = ord.tour_id " +
                    "INNER JOIN statuses AS st ON ord.status_id = st.id " +
                    "INNER JOIN users AS us ON ord.customer_id = us.id " +
                    "WHERE ord.customer_id = %s;";

    public static final String GET_ORDERS_BY_STATUS_TITLE =
                    "SELECT t.id, t.name, t.description, t.country, t.peoples, ht.name AS hotel_type, tt.name AS tour_type, t.price, t.is_hot, " +
                        "ord.id AS order_id, ord.customer_id AS order_customer_id, st.id AS status_id, st.title AS status_title, " +
                        "us.id AS user_id, us.email AS user_email, us.name AS user_name " +
                    "FROM tours AS t " +
                    "INNER JOIN hotel_types AS ht ON t.hotel_type_id = ht.id " +
                    "INNER JOIN tour_types AS tt ON t.tour_type_id = tt.id " +
                    "INNER JOIN orders AS ord ON t.id = ord.tour_id " +
                    "INNER JOIN statuses AS st ON ord.status_id = st.id " +
                    "INNER JOIN users AS us ON ord.customer_id = us.id " +
                    "WHERE st.title LIKE '%s';";

    public static final String INSERT_NEW_ORDER =
                    "INSERT INTO orders (customer_id, tour_id, status_id) " +
                    "VALUES (%1$s, %2$s, %3$s);";

    public static final String DELETE_ORDER_BY_ID =
                    "DELETE FROM orders " +
                    "WHERE id = %s;";

    public static final String UPDATE_ORDER_STATUS =
                    "UPDATE orders " +
                    "SET status_id = %1$s " +
                    "WHERE id = %2$s;";

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

    public static final String INSERT_NEW_TOUR =
                    "INSERT INTO tours (name, description, country, peoples, hotel_type_id, tour_type_id, price, is_hot) " +
                    "VALUES ('%1$s', '%2$s', '%3$s', %4$s, %5$s, %6$s, %7$s, %8$s);";

    public static final String GET_TOUR_BY_ID =
                    "SELECT t.id, t.name, t.description, t.country, t.peoples, ht.name AS hotel_type, tt.name AS tour_type, t.price, t.is_hot " +
                    "FROM tours AS t " +
                    "INNER JOIN hotel_types AS ht ON t.hotel_type_id = ht.id " +
                    "INNER JOIN tour_types AS tt ON t.tour_type_id = tt.id " +
                    "WHERE t.id = %s;";

    public static final String UPDATE_TOUR_HOTNESS_VALUE =
                    "UPDATE tours " +
                    "SET is_hot = %1$s " +
                    "WHERE id = %2$s;";

    public static final String GET_ALL_TOUR_TYPES =
                    "SELECT id, name FROM tour_types;";

    public static final String GET_TOUR_TYPE_BY_NAME =
                    "SELECT id, name FROM tour_types " +
                    "WHERE name LIKE '%s';";

    public static final String DELETE_TOUR_BY_ID =
                    "DELETE FROM tours " +
                    "WHERE id = %s;";

    //=================================================================================================================
    //CountryDao Queries
    //=================================================================================================================

    public static final String GET_ALL_COUNTRIES =
                    "SELECT id, name FROM countries;";

    //=================================================================================================================
    //HotelDao Queries
    //=================================================================================================================

    public static final String GET_ALL_HOTELS =
                    "SELECT id, name FROM hotel_types;";

    public static final String GET_HOTEL_BY_NAME =
                    "SELECT id, name FROM hotel_types " +
                    "WHERE name LIKE '%s';";
}
