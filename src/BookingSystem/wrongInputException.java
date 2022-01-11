package BookingSystem;

public class wrongInputException extends Exception{
    public wrongInputException(String msg) {
        super("Wrong Input: " + msg);
    }
    public wrongInputException() {
    }
    public String getMessage() {
        return super.getMessage();
    }
}
