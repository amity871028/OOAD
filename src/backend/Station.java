package backend;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class Station {
    private static final String filePath = new String(System.getProperty("user.dir") + "/src/backend/file/station.json");
    private JSONArray stationArr;
    private ArrayList<JSONObject> stationArrL = new ArrayList();

    public String get(int stationIndex, String... location) {
        switch(location.length) {
            case 1:
                return ((JSONObject)this.stationArrL.get(stationIndex)).getString(location[0]);
            case 2:
                return ((JSONObject)this.stationArrL.get(stationIndex)).getJSONObject(location[0]).getString(location[1]);
            default:
                return "There may be something wrong with your location array: " + Arrays.toString(location);
        }
    }

    public String toString() {
        return this.stationArrL.toString();
    }

    Station() {
        String jsonString = new String();

        try {
            File file = new File(filePath);

            for(Scanner scan = new Scanner(file); scan.hasNext(); jsonString = jsonString + scan.nextLine()) {
            }
        } catch (Exception var4) {
            System.out.println("There may be something wrong with file path:" + filePath);
        }

        this.stationArr = new JSONArray(jsonString);

        for(int i = 0; i < this.stationArr.length(); ++i) {
            JSONObject tmp = new JSONObject(this.stationArr.getJSONObject(i).toString());
            this.stationArrL.add(tmp);
        }

    }

    public static void main(String[] args) {
        Station s = new Station();
        System.out.println(s.toString());
        System.out.println(s.get(0, "StationID"));
        System.out.println(s.get(0, "StationName", "En"));
    }
}
