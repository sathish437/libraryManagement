package hello.example.welcome.repo;

import hello.example.welcome.entity.IssueTable;
import hello.example.welcome.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<IssueTable,Long> {
    IssueTable findByStudentTableEmail(String Email);
    IssueTable findByIssueId(Long id);
    boolean existsByStudentTableStudentIdAndStatus(Long id, Status status);
}
