package hello.example.welcome.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<HashMap<String,Object>> handleBookNotFound(BookNotFoundException ex){
        HashMap<String,Object> map=new HashMap<>();

        map.put("message",ex.getMessage());
        map.put("statusCode", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<HashMap<String,Object>> handleEmailExistsException(EmailAlreadyExistsException ex){
        HashMap<String,Object> map= new HashMap<>();

        map.put("message",ex.getMessage());
        map.put("statusCode",HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<HashMap<String,Object>> handleStudentNotFound(StudentNotFoundException ex){
        HashMap<String,Object> map=new HashMap<>();

        map.put("message",ex.getMessage());
        map.put("statusCode", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<HashMap<String,Object>> handleInvalidEmailException(InvalidEmailException ex){
        HashMap<String,Object> map=new HashMap<>();

        map.put("message",ex.getMessage());
        map.put("StatusCode",HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IssueBookNotFoundException.class)
    public ResponseEntity<HashMap<String,Object>> handleIssueBookNotFoundException(IssueBookNotFoundException ex){
        HashMap<String,Object> map=new HashMap<>();

        map.put("message",ex.getMessage());
        map.put("StatusCode",HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DeletionNotAllowedException.class)
    public ResponseEntity<HashMap<String,Object>> handleBookNotDeleteFoundException(DeletionNotAllowedException ex){
        HashMap<String,Object> map=new HashMap<>();

        map.put("message",ex.getMessage());
        map.put("statusCode",HttpStatus.CONFLICT.value());

        return new ResponseEntity<>(map,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(StudentDeletionNotAllowedException.class)
    public ResponseEntity<HashMap<String,Object>> handleStudentDeletionNotAllowedException(StudentDeletionNotAllowedException ex){
        HashMap<String,Object> map=new HashMap<>();

        map.put("message",ex.getMessage());
        map.put("statusCode",HttpStatus.CONFLICT.value());

        return new ResponseEntity<>(map,HttpStatus.CONFLICT);
    }
}
