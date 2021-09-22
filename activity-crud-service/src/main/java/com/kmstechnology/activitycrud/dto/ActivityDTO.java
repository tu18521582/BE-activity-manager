package com.kmstechnology.activitycrud.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
public class ActivityDTO {
    private Long id;
    private String title;
    private String description;
    private String category;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime time;
    private String venue;
    private String city;
    private UserDTO host;
    private Set<UserDTO> userAttend;

    public ActivityDTO() {
    }

    public ActivityDTO(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.category = builder.category;
        this.date = builder.date;
        this.time = builder.time;
        this.venue = builder.venue;
        this.city = builder.city;
        this.host = builder.host;
        this.userAttend = builder.userAttend;
    }

    public UserDTO getHost() {
        return host;
    }

    public void setHost(UserDTO host) {
        this.host = host;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<UserDTO> getUserAttend() {
        return userAttend;
    }

    public void setUserAttend(Set<UserDTO> userAttend) {
        this.userAttend = userAttend;
    }

    public static ActivityDTO.Builder builder() {
        return new ActivityDTO.Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ActivityDTO that = (ActivityDTO) o;

        return new EqualsBuilder().append(id, that.id).append(title, that.title).append(description, that.description).append(category, that.category).append(date, that.date).append(time, that.time).append(venue, that.venue).append(city, that.city).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(title).append(description).append(category).append(date).append(time).append(venue).append(city).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("title", title)
                .append("description", description)
                .append("category", category)
                .append("date", date)
                .append("time", time)
                .append("venue", venue)
                .append("city", city)
                .toString();
    }

    public static final class Builder {
        private Long id;
        private String title;
        private String description;
        private String category;
        private LocalDate date;
        private LocalTime time;
        private String venue;
        private String city;
        private UserDTO host;
        private Set<UserDTO> userAttend;

        private Builder() {

        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder time(LocalTime time) {
            this.time = time;
            return this;
        }

        public Builder venue(String venue) {
            this.venue = venue;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder host(UserDTO host) {
            this.host = host;
            return this;
        }

        public Builder userAttend(Set<UserDTO> userAttend) {
            this.userAttend = userAttend;
            return this;
        }

        public ActivityDTO build() {
            return new ActivityDTO(this);
        }
    }
}
