package hello.example.welcome.repo;

import hello.example.welcome.entity.IssueTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<IssueTable,Long> {
}
