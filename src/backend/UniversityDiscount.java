package backend;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import org.json.JSONObject;

public class UniversityDiscount {
    private static final String filePath = new String(System.getProperty("user.dir") + "/src/backend/file/universityDiscount.json");
    private JSONObject universityDiscountJSONObj;

    public int getRowIndex(String trainNo) {
        int i;

        for (i = 0; i < 69; i++) {
            if (get(i, "DiscountTrains", "TrainNo").equals(trainNo)) return i;
        }
        return -1;
    }


    public String get(int DiscountTrainsIndex, String... location) {
        switch(location.length) {
            case 2:
                return "" + this.universityDiscountJSONObj.getJSONArray(location[0]).getJSONObject(DiscountTrainsIndex).get(location[1]);
            case 3:
                return "" + this.universityDiscountJSONObj.getJSONArray(location[0]).getJSONObject(DiscountTrainsIndex).getJSONObject(location[1]).getDouble(location[2]);
            default:
                return "There may be something wrong with your location array: " + Arrays.toString(location);
        }
    }

    public String get(String... location) {
        return "" + this.universityDiscountJSONObj.get(location[0]);
    }

    public String toString() {
        return this.universityDiscountJSONObj.toString();
    }

    public UniversityDiscount() {
        String jsonString = new String();

        try {
            File file = new File(filePath);

            for(Scanner scan = new Scanner(file); scan.hasNext(); jsonString = jsonString + scan.nextLine()) {
            }
        } catch (Exception var4) {
            System.out.println("There may be something wrong with file path:" + filePath);
        }

        this.universityDiscountJSONObj = new JSONObject(jsonString);
    }

    public static void main(String[] args) {
        UniversityDiscount u = new UniversityDiscount();
        System.out.println(u.toString());
        System.out.println(u.get("EffectiveDate"));
        System.out.println(u.get("DiscountTrains"));
        System.out.println(u.get(0, "DiscountTrains", "TrainNo"));
        System.out.println(u.get(0, "DiscountTrains", "ServiceDayDiscount"));
        System.out.println(u.get(0, "DiscountTrains", "ServiceDayDiscount", "Monday"));
    }
}

