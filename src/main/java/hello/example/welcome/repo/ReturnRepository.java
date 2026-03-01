package hello.example.welcome.repo;

import hello.example.welcome.entity.ReturnTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnRepository extends JpaRepository<ReturnTable,Long> {
}
