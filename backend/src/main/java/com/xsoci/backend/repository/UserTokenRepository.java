package com.xsoci.backend.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.xsoci.backend.entity.UserToken;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    Optional<UserToken> findByUser(long id);

    Optional<UserToken> findByToken(String token);

    Optional<UserToken> findByType(String type);

    Optional<UserToken> findByTokenAndType(String token, String type);

    Optional<UserToken> deleteByTokenAndType(String token, String type);

    boolean existsByToken(String token);

    boolean existsByType(String type);
}