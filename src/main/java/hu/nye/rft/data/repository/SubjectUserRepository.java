package hu.nye.rft.data.repository;

import hu.nye.rft.data.domain.SubjectUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectUserRepository extends JpaRepository<SubjectUserEntity, Long> {
}
