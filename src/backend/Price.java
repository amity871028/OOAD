package backend;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class Price {
    private static final String filePath = new String(System.getProperty("user.dir") + "/src/backend/file/price.json");
    private JSONArray priceArr;
    private ArrayList<JSONObject> priceArrL = new ArrayList();

    public String get(int priceIndex, int DesrinationStationsIndex, int FaresIndex, String... location) {
        switch(location.length) {
            case 3:
                return "" + ((JSONObject)this.priceArrL.get(priceIndex)).getJSONArray(location[0]).getJSONObject(DesrinationStationsIndex).getJSONArray(location[1]).getJSONObject(FaresIndex).get(location[2]);
            default:
                return "There may be something wrong with your location array: " + Arrays.toString(location);
        }
    }

    public int getNumOfDestinationStations(int row) {
        return priceArrL.get(row).getJSONArray("DesrinationStations").length();
    }

    public String get(int priceIndex, int DesrinationStationsIndex, String... location) {
        switch(location.length) {
            case 2:
                if (location[1].equals("Direction")) {
                    return "" + ((JSONObject)this.priceArrL.get(priceIndex)).getJSONArray(location[0]).getJSONObject(DesrinationStationsIndex).getInt(location[1]);
                }

                return ((JSONObject)this.priceArrL.get(priceIndex)).getJSONArray(location[0]).getJSONObject(DesrinationStationsIndex).getString(location[1]);
            default:
                return "There may be something wrong with your location array: " + Arrays.toString(location);
        }
    }

    public String get(int priceIndex, String... location) {
        switch(location.length) {
            case 1:
                return ((JSONObject)this.priceArrL.get(priceIndex)).getString(location[0]);
            case 2:
            default:
                return "There may be something wrong with your location array: " + Arrays.toString(location);
        }
    }

    public String toString() {
        return this.priceArrL.toString();
    }

    public Price() {
        String jsonString = new String();

        try {
            File file = new File(filePath);

            for(Scanner scan = new Scanner(file); scan.hasNext(); jsonString = jsonString + scan.nextLine()) {
            }
        } catch (Exception var4) {
            System.out.println("There may be something wrong with file path:" + filePath);
        }

        this.priceArr = new JSONArray(jsonString);

        for(int i = 0; i < this.priceArr.length(); ++i) {
            JSONObject tmp = new JSONObject(this.priceArr.getJSONObject(i).toString());
            this.priceArrL.add(tmp);
        }

    }

    Price(String path) {
        String jsonString = new String();

        try {
            new String("/Users/kobemary/OOP/termProject/src");
            String relativePath = new String("/backend/file/price.json");
            String fullPath = new String(path + relativePath);
            File file = new File(fullPath);

            for(Scanner scan = new Scanner(file); scan.hasNext(); jsonString = jsonString + scan.nextLine()) {
            }
        } catch (Exception var8) {
            System.out.println("load station.json file error");
        }

        this.priceArr = new JSONArray(jsonString);

        for(int i = 0; i < this.priceArr.length(); ++i) {
            JSONObject tmp = new JSONObject(this.priceArr.getJSONObject(i).toString());
            this.priceArrL.add(tmp);
        }

    }

    public static void main(String[] args) {
        Price p = new Price();
        System.out.println(p.get(0, "OriginStationID"));
        System.out.println(p.get(0, 0, "DesrinationStations", "ID"));
        System.out.println(p.get(0, 0, "DesrinationStations", "Direction"));
        System.out.println(p.get(0, 0, 0, "DesrinationStations", "Fares", "TicketType"));
        System.out.println(p.get(0, 0, 1, "DesrinationStations", "Fares", "Price"));
    }
}
