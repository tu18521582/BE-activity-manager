package com.kmstechnology.activitycrud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "activity_info")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id"
    )
    private Long id;

    @Column(
            name = "title"
    )
    private String title;

    @Column(
            name = "description"
    )
    private String description;

    @Column(
            name = "category"
    )
    private String category;

    @Column(
            name = "date_org"
    )
    private LocalDate date;

    @Column(
            name = "time_org"
    )
    private LocalTime time;

    @Column(
            name = "venue"
    )
    private String venue;

    @Column(
            name = "city"
    )
    private String city;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="id_host_user")
    private User user;

    @ManyToMany(mappedBy = "activityAttend")
    @JsonIgnore
    private Set<User> userAttend = new HashSet<>();

    public Activity() {
    }

    public Activity(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.category = builder.category;
        this.date = builder.date;
        this.time = builder.time;
        this.venue = builder.venue;
        this.city = builder.city;
        this.user = builder.user;
        this.userAttend = builder.userAttend;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<User> getUserAttend() {
        if(userAttend == null){
            return Collections.emptySet();
        }
        return userAttend;
    }

    public void setUserAttend(Set<User> userAttend) {
        this.userAttend = userAttend;
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

    public static Activity.Builder builder() {
        return new Activity.Builder();
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
        private User user;
        private Set<User> userAttend;

        private Builder() {

        }

        public Activity.Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Activity.Builder title(String title) {
            this.title = title;
            return this;
        }

        public Activity.Builder description(String description) {
            this.description = description;
            return this;
        }

        public Activity.Builder category(String category) {
            this.category = category;
            return this;
        }

        public Activity.Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Activity.Builder time(LocalTime time) {
            this.time = time;
            return this;
        }

        public Activity.Builder venue(String venue) {
            this.venue = venue;
            return this;
        }

        public Activity.Builder city(String city) {
            this.city = city;
            return this;
        }

        public Activity.Builder user(User user){
            this.user = user;
            return this;
        }

        public Activity.Builder userAttend(Set<User> userAttend) {
            this.userAttend = userAttend;
            return this;
        }

        public Activity build() {
            return new Activity(this);
        }
    }
}
