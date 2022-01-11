package backend;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import static java.lang.Integer.*;

public class Seat {
    private String filePath = new String(System.getProperty("user.dir")+"/src/backend/file/seat.json");

    private JSONObject seatJSONObj;


//	public String get(int carsIndex,  String...location) {
//		return null;
//	}
    /**
     * This is used to get value of car, type, or 1 2 3 4 5 6....
     * @param carsIndex int type to indicate index of cars array
     * @param location String type arguments. You MUST specify clearly each key you want to get
     * @return String type of value. If you are getting 1 2 3 4 5 6, return would be like ["A","B","C","D","E"] which is String type
     */
    public String get(int carsIndex, String...location) {

        switch(location.length) {
            case 2:
                // get car type
                return "" + seatJSONObj.getJSONArray(location[0]).getJSONObject(carsIndex).get(location[1]);
            case 3:
                // get 1 2 3 4 5 6 7
                return "" + seatJSONObj.getJSONArray(location[0]).getJSONObject(carsIndex).getJSONObject(location[1]).getJSONArray(location[2]);
            default:
                return "There may be something wrong with your location array: "+ Arrays.toString(location);
        }
    }

    /**
     *  This is used to get A B C D E.....
     * @param location String type arguments. You MUST specify clearly each key you want to get
     * @return String type of value
     */
    public String get(String... location) {
        // get A B C D E
        try {
            return seatJSONObj.getJSONObject(location[0]).getString(location[1]);
        }catch(Exception e) {
            return "There may be something wrong with your location array: "+ Arrays.toString(location);
        }

    }


    public String toString() {
        return seatJSONObj.toString();
    }




    public Seat(){
        String jsonString = new String();
        try {
            // cwd is at termProject
            //System.out.println(System.getProperty("user.dir"));


            // load file
            File file =  new File(filePath);
            Scanner scan = new Scanner(file);
            while(scan.hasNext()) {
                jsonString = jsonString + scan.nextLine();
            }
        }catch(Exception e) {
            System.out.println("There may be something wrong with file path:"+filePath);
        }
        seatJSONObj = new JSONObject(jsonString);
    }

    /** you can indicate which trainNo and StationID to find the corresponding seat.json
     * @param trainNo String type to indicate the trainNo
     * @param stationId String type to indicate the stationId
     */
    public Seat(String trainNo, String stationId){
        String jsonString = new String();
        String fileName = trainNo + "-" + stationId + "-seat" + ".json";
        filePath = System.getProperty("user.dir")+"/src/backend/file/seat/"+fileName;
        try {
            // cwd is at termProject
            //System.out.println(System.getProperty("user.dir"));

            // load file
            File file =  new File(filePath);
            Scanner scan = new Scanner(file);
            while(scan.hasNext()) {
                jsonString = jsonString + scan.nextLine();
            }
        }catch(Exception e) {
            System.out.println("There may be something wrong with file path:"+filePath);
        }
        seatJSONObj = new JSONObject(jsonString);
    }
    /**
     * You can add seat by this function under the condition that the seat is sold before and now it's available
     * It's good to known that the col must be capital.
     * @param carsIndex int type to indicate the which car you want to operate with
     * @param row String type to indicate which row you want to operate with such "1", "4", and "13"
     * @param col String type to indicate what kind of seat row you want to add with such "A", "E", and "B"
     */
    public void addSeat(int carsIndex, String row, String col) {
        // 這可以多寫萬一重複加seat的處理
        seatJSONObj.getJSONArray("cars").getJSONObject(carsIndex).getJSONObject("seats").getJSONArray(row).put(col);

    }

    /**
     * Get the number of row of seats of a specific car
     * @param carsIndex int type to indicate car index in a train
     * @return int type to indicate numbers of rows of seats
     */
    public int getNumOfRow(int carsIndex) {
        return seatJSONObj.getJSONArray("cars").getJSONObject(carsIndex).getJSONObject("seats").length();
    }

    public String getSeatCode(int carsIndex, String row, String col) {
        return seatJSONObj.getJSONArray("cars").getJSONObject(carsIndex).getJSONObject("seats").getJSONArray(row).getString(parseInt(col));
    }

    /**
     * You can sold when that seat is available.
     * @param carsIndex int type to indicate the which car you want to operate with
     * @param row String type to indicate which row you want to operate with such "1", "4", and "13"
     * @param col String type to indicate what kind of seat row you want to add with such "A", "E", and "B"
     * @throws SoldBuySeatException you can use getMessage() function to know what happen
     */

    public void soldSeat(int carsIndex, String row, String col) throws SoldBuySeatException {
        JSONArray certainRow = seatJSONObj.getJSONArray("cars").getJSONObject(carsIndex).getJSONObject("seats").getJSONArray(row);
        boolean available = false;

        for (int i=0; i<certainRow.length(); i++) {

            if (col.equals(certainRow.get(i).toString())) {
                // seat can be sold
                certainRow.remove(i);
                available = true;
            }
        }
        if (available == false) {
            throw new SoldBuySeatException();
        }

        // return available;

    }

    /**
     * Check if a seat is available or not
     * @param carsIndex int type to indicate car index in a train
     * @param row String type to indicate row index in a car
     * @param col String type to indicate col index in a row
     * @return true if the specific seat is available, otherwise, false
     */
    public boolean checkSeat(int carsIndex, String row, String col) {
        JSONArray certainRow = seatJSONObj.getJSONArray("cars").getJSONObject(carsIndex).getJSONObject("seats").getJSONArray(row);

        for (int i = 0; i < certainRow.length(); i++) {
            if (col.equals(certainRow.get(i).toString())) {
                // seat is available
                return true;
            }
        }
        return false;

    }


    /**
     * Every time you finish all operation with this object and want to save object as file in databse
     * Be aware of that it will overwrite the original file
     */
    public void save() {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(seatJSONObj.toString());
            writer.close();
        }catch(Exception e) {
            System.out.println("Saving file went wrong");
        }
        System.out.println("The file has been saved");
    }




    public static void main(String[] args) {
//		Seat s = new Seat("0672-1050-Seat");
//		System.out.println(s.toString());
//		System.out.println(s.get("seatsPosition", "A"));
//		System.out.println(s.get(0, "cars", "car"));
//		System.out.println(s.get(0, "cars", "type"));
//		System.out.println(s.get(0, "cars", "seats", "1"));

        // seat資料夾的seat.json
        Seat sTmp = new Seat("0108", "0990");
//        System.out.println(sTmp.toString());
//        System.out.println(sTmp.get("seatsPosition", "A"));
//        System.out.println(sTmp.get(0, "cars", "car"));
//        System.out.println(sTmp.get(0, "cars", "type"));
//
//        // sell seat and assume that is available to be sold
//        System.out.println(sTmp.get(0, "cars", "seats", "1"));
//        try {
//            sTmp.soldSeat(0, "1", "C");
//            System.out.println("success");
//        }catch(SoldBuySeatException e){
//            //Something you need to handle
//        }
//
//        System.out.println(sTmp.get(0, "cars", "seats", "1"));
//
//        //  sell seat and assume that is occupied and soldSeat() will throw Exception
//
//        try {
//            sTmp.soldSeat(0, "1", "D");
//        }catch (SoldBuySeatException e){
//            //Something you need to handle
//            System.out.println("fail");
//        }
//        sTmp.addSeat(0, "1", "E");
//        System.out.println(sTmp.get(0, "cars", "seats", "1"));
//
//        sTmp.save();

        System.out.println(sTmp.getSeatCode(0, "1", "0"));

    }
}