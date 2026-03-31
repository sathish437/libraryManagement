package hello.example.welcome.repo;

import hello.example.welcome.entity.IssueTable;
import hello.example.welcome.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<IssueTable,Long> {
    IssueTable findByStudentTableEmail(String Email);
    IssueTable findByIssueId(Long id);
    List<IssueTable> findByStatus(Status status);
    List<IssueTable> findByStudentTableNameContainingIgnoreCase(String name);
    boolean existsByStudentTableStudentIdAndStatus(Long id, Status status);
}
