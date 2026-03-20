package hello.example.welcome.ExceptionHandling;

public class IssueBookNotFoundException extends RuntimeException{
    public IssueBookNotFoundException(String msg){
        super(msg);
    }
}
