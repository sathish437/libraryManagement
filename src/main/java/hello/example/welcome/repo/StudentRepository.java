package hello.example.welcome.repo;

import hello.example.welcome.entity.StudentTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentTable,Long> {
}
