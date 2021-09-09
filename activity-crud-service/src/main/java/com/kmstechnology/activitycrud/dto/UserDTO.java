package com.kmstechnology.activitycrud.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserDTO {
    private String displayName;
    private String username;
    private String email;
    private String password;

    public UserDTO() {

    }

    public UserDTO(Builder builder) {
        this.displayName = builder.displayName;
        this.username = builder.username;
        this.email = builder.email;
        this.password = builder.password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        return new EqualsBuilder().append(displayName, userDTO.displayName).append(username, userDTO.username).append(email, userDTO.email).append(password, userDTO.password).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(displayName).append(username).append(email).append(password).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("displayName", displayName)
                .append("username", username)
                .append("email", email)
                .append("password", password)
                .toString();
    }

    public static final class Builder {
        private String displayName;
        private String username;
        private String email;
        private String password;

        private Builder() {

        }

        public Builder displayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(this);
        }
    }
}
