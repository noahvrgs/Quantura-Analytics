package com.quantura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_settings")
public class UserSettings {

    // ===== ID Generation =====
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // ===== Relationships ======
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    // ===== UI Preferences =====
    private String theme = "dark";
    
    // ===== Notifications =====
    private boolean emailNotifications = false;
    private boolean smsNotifications = false;
    private boolean pushNotifications = false;
    
    // ===== User Privacy/Activity =====
    private boolean showActivityStatus = false;
    private boolean allowFriendRequests = false;

    // ===== Account Behavior =====
    private String timezone = "America/Los Angeles";
    private String dateFormat = "MM/DD/YYYY";

    // ===== Constructors =====
    public UserSettings() {}

    public UserSettings(User user) { 
        this.user = user; 
    }

    // ===== Getters =====
    public Long getId() { return id; }
    public User getUser() { return user; }
    public String getTheme() { return theme; }
    public boolean isEmailNotifications() { return emailNotifications;}
    public boolean isSmsNotifications() { return smsNotifications; }
    public boolean isPushNotifications() { return pushNotifications; }
    public boolean isShowActivityStatus() { return showActivityStatus; }
    public boolean isAllowFriendRequests() { return allowFriendRequests; }
    public String getTimeZone() { return timezone; }
    public String getDateFormat() { return dateFormat; }
    
    // ===== Setters =====
    public void setUser(User user) { this.user = user; }
    public void setTheme(String theme) { this.theme = theme; }
    public void setEmailNotifications(boolean emailNotifications) { this.emailNotifications = emailNotifications; }
    public void setSmsNotifications(boolean smsNotifications) { this.smsNotifications = smsNotifications; }
    public void setPushNotifications(boolean pushNotifications) { this.pushNotifications = pushNotifications; }
    public void setShowActivityStatus(boolean showActivityStatus) { this.showActivityStatus = showActivityStatus; }
    public void setAllowFriendRequests(boolean allowFriendRequests) { this.allowFriendRequests = allowFriendRequests; }
    public void setTimeZone(String timezone) { this.timezone = timezone; }
    public void setDateFormat(String dateFormat) { this.dateFormat = dateFormat; }

    
}
