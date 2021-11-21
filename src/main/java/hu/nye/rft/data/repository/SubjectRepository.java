package hu.nye.rft.data.repository;

import hu.nye.rft.data.domain.SubjectEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntryEntity, Long> {
}
