package hello.example.welcome.ExceptionHandling;

public class StudentDeletionNotAllowedException extends RuntimeException{
    public StudentDeletionNotAllowedException(String msg){
        super(msg);
    }
}
