package hello.example.welcome.repo;

import hello.example.welcome.entity.Department;
import hello.example.welcome.entity.StudentTable;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentTable,Long> {
    List<StudentTable> findByNameContainingIgnoreCase(String name);
    Optional<StudentTable> findByEmail(String email);
}
