package com.kmstechnology.activitycrud.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Set;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Long id;
    private String displayName;
    private String username;
    private String email;
    private String password;
    private Set<ActivityDTO> activities;
    private Set<ActivityDTO> activityAttend;

    public UserDTO() {
    }

    public UserDTO(Builder builder) {
        this.id = builder.id;
        this.displayName = builder.displayName;
        this.username = builder.username;
        this.email = builder.email;
        this.password = builder.password;
        this.activities = builder.activities;
        this.activityAttend = builder.activityAttend;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public Set<ActivityDTO> getActivities() {
        return activities;
    }

    public Set<ActivityDTO> getActivityAttend() {
        return activityAttend;
    }

    public void setActivityAttend(Set<ActivityDTO> activityAttend) {
        this.activityAttend = activityAttend;
    }

    public void setActivities(Set<ActivityDTO> activities) {
        this.activities = activities;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        return new EqualsBuilder().append(id, userDTO.id).append(displayName, userDTO.displayName).append(username, userDTO.username).append(email, userDTO.email).append(password, userDTO.password).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(displayName).append(username).append(email).append(password).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("displayName", displayName)
                .append("username", username)
                .append("email", email)
                .append("password", password)
                .toString();
    }

    public static final class Builder {
        private Long id;
        private String displayName;
        private String username;
        private String email;
        private String password;
        private Set<ActivityDTO> activities;
        private Set<ActivityDTO> activityAttend;

        private Builder() {

        }

        public Builder id(Long id) {
            this.id = id;
            return this;
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

        public  Builder activities(Set<ActivityDTO> activities) {
            this.activities = activities;
            return this;
        }

        public  Builder activityAttend(Set<ActivityDTO> activityAttend) {
            this.activityAttend  = activityAttend;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(this);
        }
    }
}
