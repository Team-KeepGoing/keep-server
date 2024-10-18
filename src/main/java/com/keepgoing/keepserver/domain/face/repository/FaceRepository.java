package com.keepgoing.keepserver.domain.face.repository;

import com.keepgoing.keepserver.domain.face.entity.Face;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FaceRepository extends JpaRepository<Face, Long> {
    Optional<Face> findByEmail(String email);

    Optional<Face> findByS3ImageUrl(String s3ImageUrl);
}
