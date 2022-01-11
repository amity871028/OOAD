package backend;

public class SoldBuySeatException extends Exception{


    SoldBuySeatException(int carsIndex, String row, String col){
        super("Bad operation with car: " + carsIndex + "row: " + row + "col: " + col);

    }
    public String getMessage() {
        return super.getMessage();

    }

    SoldBuySeatException(){

    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}