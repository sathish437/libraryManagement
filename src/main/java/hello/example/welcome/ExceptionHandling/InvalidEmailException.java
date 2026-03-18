package hello.example.welcome.ExceptionHandling;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException(String msg){
        super(msg);
    }
}
