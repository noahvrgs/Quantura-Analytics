package com.quantura.repository;

import com.quantura.model.User;
import com.quantura.model.UserSettings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserSettingsRepository extends JpaRepository<UserSettings, Long> {
}
