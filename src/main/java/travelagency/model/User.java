package travelagency.model;

import travelagency.model.enums.UserRoles;

import java.util.Objects;
import java.util.Set;

public class User {

    private Long id;
    private String email;
    private String name;
    private String password;
    private String aboutMe;
    private String fullName;
    private String role;
    private Set<Order> orders;

    private User() { }

    public static Builder newBuilder() {
        return new User().new Builder();
    }

    public class Builder {
        private  Builder() {

        }

        public Builder setId(Long userId) {
            User.this.id = userId;

            return this;
        }

        public Builder setEmail(String email) {
            User.this.email = email;

            return this;
        }

        public Builder setName(String name) {
            User.this.name = name;

            return this;
        }

        public Builder setPassword(String password) {
            User.this.password = password;

            return this;
        }

        public Builder setAboutMe(String aboutMe) {
            User.this.aboutMe = aboutMe;

            return this;
        }

        public Builder setFullName(String fullName) {
            User.this.fullName = fullName;

            return this;
        }

        public Builder setRoles(UserRoles roles) {
            User.this.role = roles.name();

            return this;
        }

        public Builder setOrders(Set<Order> orders) {
            User.this.orders = orders;

            return this;
        }

        public User build() {
            return User.this;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRole() {
        return role;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setRole(String role) { this.role = role; }

    public void setAboutMe(String aboutMe) { this.aboutMe = aboutMe; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", aboutMe='" + aboutMe + '\'' +
                ", fullName='" + fullName + '\'' +
                ", role=" + role +
                ", orders=" + orders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                email.equals(user.email) &&
                name.equals(user.name) &&
                password.equals(user.password) &&
                Objects.equals(aboutMe, user.aboutMe) &&
                Objects.equals(fullName, user.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, password, aboutMe, fullName);
    }
}
