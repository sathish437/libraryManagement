package hello.example.welcome.ExceptionHandling;

public class DeletionNotAllowedException extends RuntimeException{
    public DeletionNotAllowedException(String msg){
        super(msg);
    }
}
