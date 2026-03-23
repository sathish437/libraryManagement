package hello.example.welcome.ExceptionHandling;

public class BookDeletionNotAllowedException extends RuntimeException{
    public BookDeletionNotAllowedException(String msg){
        super(msg);
    }
}
