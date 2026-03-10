package hello.example.welcome.ExceptionHandling;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String msg){
        super(msg);
    }
}
