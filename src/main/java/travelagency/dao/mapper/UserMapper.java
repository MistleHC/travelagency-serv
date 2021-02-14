package travelagency.dao.mapper;

import travelagency.Encryption.AESEncryptor;
import travelagency.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    public User extractFromResultSet(ResultSet rs) throws SQLException {
        return User.newBuilder()
                .setId(rs.getLong("id"))
                .setEmail(rs.getString("email"))
                .setName(rs.getString("name"))
                .setPassword(AESEncryptor.decrypt(rs.getString("password"), "travel"))
                .setAboutMe(rs.getString("aboutme"))
                .setFullName(rs.getString("fullname"))
                .build();
    }
}
