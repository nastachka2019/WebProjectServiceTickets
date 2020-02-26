package by.epam.project.entity;

import java.time.LocalDate;

/**
 * Класс-сущность для  User
 *
 * @author Shpakova A.
 */

public class User extends Entity {

    private int userId;
    private UserRole userRole;
    private String name;
    private String surname;
    private String email;
    private String login;
    private String password;
    private String phone;
    private LocalDate dateOfBirth;

    public User() {
    }

    public User(int userId, UserRole userRole, String name, String surname, String email, String login, String password, String phone, LocalDate dateOfBirth) {
        this.userId = userId;
        this.userRole = userRole;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        User user = (User) o;
        return userId == user.userId && (phone == user.phone && phone != null && phone.equals(user.getPhone())) &&
                (userRole == user.userRole && userRole != null && userRole.equals(user.getUserRole())) &&
                (name == user.name && name != null && name.equals(user.getName())) &&
                (surname == user.surname && surname != null && surname.equals(user.getSurname())) &&
                (email == user.email && email != null && email.equals(user.getEmail())) &&
                (login == user.login && login != null && login.equals(user.getLogin())) &&
                (dateOfBirth == user.dateOfBirth && dateOfBirth != null && dateOfBirth.equals(user.getDateOfBirth())) &&
                (password == user.password && password != null && password.equals(user.getPassword()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + userId;
        result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("User{" + "userId=" + userId + ", userRole=" + userRole + ", name='" + name + ", surname='" + surname +
                ", email='" + email + ", login='" + login + ", password='" + password + ", phone=" + phone + ", dateOfBirth=" + dateOfBirth +
                '}');
        return str.toString();
    }

    public static class Builder {      //для постр. колонок
        private User user;

        public Builder() {
            user = new User();
        }

        public Builder setUserId(int userId) {
            user.userId = userId;
            return this;
        }

        public Builder setUserRole(UserRole userRole) {
            user.userRole = userRole;
            return this;
        }

        public Builder setName(String name) {
            user.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            user.surname = surname;
            return this;
        }

        public Builder setEmail(String email) {
            user.email = email;
            return this;
        }

        public Builder setLogin(String login) {
            user.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            user.password = password;
            return this;
        }

        public Builder setPhone(String phone) {
            user.phone = phone;
            return this;
        }

        public Builder setDateOfBirth(LocalDate dateOfBirth) {
            user.dateOfBirth = dateOfBirth;
            return this;
        }

        public User build() {
            return user;
        }

    }
}
