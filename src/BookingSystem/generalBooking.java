package BookingSystem;

import backend.*;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.*;

public class generalBooking {
    private TimeTable aTimeTable = new TimeTable();
    private Price aPrice = new Price();
    private Ticket aTicket = new Ticket();
    private UniversityDiscount aUniversityDiscount = new UniversityDiscount();

    private int generalTicket;
    private int universityTicket;
    private String code;
    private String uid;
    private boolean roundTrip;
    private int _toOrFrom = 0;
    private String startStationID;
    private String endStationID;
    private String strDate;
    private Date date;
    private String ticketType;
    private List<String[]> listOfValidTrainNo = new ArrayList<String[]>();
    private int validIndex;
    private int price;
    private int totalPrice;
    private String startTime = "";
    private String endTime = "";
    private String paymentDeadline;
    private boolean returnValid = false;
    private int preference = 0;

    // Getter of all ticket info from UI
    public int getGeneralTicket() {
        return generalTicket;
    }

    public int getUniversityTicket() {
        return universityTicket;
    }

    public boolean isRoundTrip() {
        return roundTrip;
    }

    public String getCode() {
        return aTicket.getCode();
    }

    public String getUid() {return aTicket.getUid();}

    public String getStartStationID() {
        return aTicket.getTicketInfo(_toOrFrom, "start");
    }

    public String getEndStationID() {
        return aTicket.getTicketInfo(_toOrFrom, "end");
    }

    public String getDate() { return aTicket.getTicketInfo(_toOrFrom, "date"); }

    public String getTicketType() {return aTicket.getTicketInfo(_toOrFrom, "ticketsType");}

    public String getPrice() {return aTicket.getPayment();}

    public Ticket getaTicket() {return aTicket;}

    public int get_toOrFrom() {return _toOrFrom;}

    public void setGeneralTicket(String generalTicket) throws wrongInputException {
        if (parseInt(generalTicket) < 0) {
            throw new wrongInputException("Ticket number cannot be negative");
        }
        if (roundTrip) {
            if (parseInt(generalTicket) > 5) {
                throw new wrongInputException("You can only order up to 5 round-trip tickets in one booking");
            }
        }
        else {
            if (parseInt(generalTicket) > 10) {
                throw new wrongInputException("You can only order up to 10 tickets in one booking");
            }
        }
        this.generalTicket = parseInt(generalTicket);
        aTicket.setTicketInfo(0, "ticketsCountA", this.generalTicket);
        aTicket.setTicketInfo(1, "ticketsCountA", this.generalTicket);
    }

    public void setUniversityTicket(String universityTicket) throws wrongInputException {
        if (parseInt(universityTicket) < 0) {
            throw new wrongInputException("Ticket number cannot be negative");
        }
        if (roundTrip) {
            if ((5 - this.generalTicket) < parseInt(universityTicket)) {
                throw new wrongInputException("You can only order up to 5 round-trip tickets in one booking");
            }
        }
        else {
            if ((10 - this.generalTicket) < parseInt(universityTicket)) {
                throw new wrongInputException("You can only order up to 10 tickets in one booking");
            }
        }
        this.universityTicket = parseInt(universityTicket);
        aTicket.setTicketInfo(0, "ticketsCountB", this.universityTicket);
        aTicket.setTicketInfo(1, "ticketsCountB", this.universityTicket);
    }

    public void setRoundTrip(String roundTrip) throws wrongInputException {
        if (roundTrip.equals("0")) this.roundTrip = false;
        else if (roundTrip.equals("1")) this.roundTrip = true;
        else {
            throw new wrongInputException("Invalid round trip input, please enter 0 or 1!");
        }
    }

    public void set_toOrFrom(int _toOrFrom) {
        this._toOrFrom = _toOrFrom;
    }

    public void setCode(int index) {
        int id = 167 * 307 * index;
        String idStr = Integer.toString(id);

        if (idStr.length() > 9) {
            aTicket.setCode(Integer.toString(id).substring(idStr.length() - 9, idStr.length()));
            code = Integer.toString(id).substring(idStr.length() - 9, idStr.length());
        }
        else {
            aTicket.setCode(String.format("%09d", id));
            code = String.format("%09d", id);
        }
    }

    public void setUid(String uid) throws wrongInputException {
        if (Character.isLetter(uid.charAt(0)) && Character.isDigit(uid.charAt(1)) && Character.isDigit(uid.charAt(2)) && Character.isDigit(uid.charAt(3)) && Character.isDigit(uid.charAt(4)) && Character.isDigit(uid.charAt(5))
                && Character.isDigit(uid.charAt(6)) && Character.isDigit(uid.charAt(7)) && Character.isDigit(uid.charAt(8)) && Character.isDigit(uid.charAt(9))) {
            aTicket.setUid(uid);
            this.uid = uid;
        }
        else {
            throw new wrongInputException("User ID should be composed of 1 letter and 9 numbers");
        }
    }

    public void setStartStationID(String stationID) {
        startStationID = stationID;
        aTicket.setTicketInfo(_toOrFrom, "start", stationID);
        if (!roundTrip) aTicket.setTicketInfo(1, "start", stationID);
    }

    public void setEndStationID(String stationID) {
        endStationID = stationID;
        aTicket.setTicketInfo(_toOrFrom, "end", stationID);
        if (!roundTrip) aTicket.setTicketInfo(1, "end", stationID);
    }

    public void setDate(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");// 定義日期格式
        date = null;
        try {
            date = format.parse(strDate);// 將字串轉換為日期
        } catch (ParseException e) {
            System.out.println("Input format of date is wrong");
        }
        aTicket.setTicketInfo(_toOrFrom, "date", strDate);
        if (!roundTrip) aTicket.setTicketInfo(1, "date", strDate);
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
        aTicket.setTicketInfo(0, "ticketsType", ticketType);
        aTicket.setTicketInfo(1, "ticketsType", ticketType);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTotalPrice(int price) {
        aTicket.setPayment(price);
    }

    public void setListOfValidTrainNo(String trainNo) {
        listOfValidTrainNo.add(new String[3]);
        System.out.println(listOfValidTrainNo.size());
        String[] s = seatRemain(aTimeTable.getRowIndex(trainNo), ticketType);
        listOfValidTrainNo.set(listOfValidTrainNo.size(), seatRemain(aTimeTable.getRowIndex(trainNo), ticketType));
    }

    public void setListOfValidTrainNo2(String trainNo2) {
        listOfValidTrainNo.add(new String[3]);
        listOfValidTrainNo.set(listOfValidTrainNo.size() - 1, seatRemain(aTimeTable.getRowIndex(trainNo2), ticketType));
    }

    public void setValidIndex(int validIndex) {
        this.validIndex = (validIndex - 1);
    }

    public void setPaymentDeadline(String paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
        aTicket.setPayDeadLine(this.paymentDeadline);
    }

    public void setReturnValid(boolean returnValid) {
        this.returnValid = returnValid;
    }

    public void setPreference(int preference) {
        this.preference = preference;
    }

    public void clearListOfValidTrainNo() {
        listOfValidTrainNo = new ArrayList<>();
    }

    public static String getWeekOfDate(Date dt) {
        String[] weekDays = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) w = 0;
        return weekDays[w];
    }

    public void findValidTrainNo(int time) throws SoldBuySeatException {
        int row;

        for (row = 0; row < 182; row++) {
//            System.out.println("row " + row);
            // check if the train is available on that day (based on Sun. Mon. Tue. Wed. Thu. Fri. Sat.)
            if (aTimeTable.get(row, "GeneralTimetable", "ServiceDay", getWeekOfDate(date)).equals("1")) {
//                System.out.println("1");

                // check if the direction is right
                if (((parseInt(startStationID) < parseInt(endStationID)) && (parseInt(aTimeTable.get(row, "GeneralTimetable", "GeneralTrainInfo", "Direction")) == 0)) ||
                    ((parseInt(startStationID) > parseInt(endStationID)) && (parseInt(aTimeTable.get(row, "GeneralTimetable", "GeneralTrainInfo", "Direction")) == 1))) {
//                    System.out.println("2");

                    // check if the start and end station ID are in the range of this row's beginning and terminal station
                    if ((parseInt(startStationID) < parseInt(endStationID) && parseInt(startStationID) >= parseInt(aTimeTable.get(row, "GeneralTimetable", "GeneralTrainInfo", "StartingStationID")) && parseInt(endStationID) <= parseInt(aTimeTable.get(row, "GeneralTimetable", "GeneralTrainInfo", "EndingStationID"))) ||
                        (parseInt(startStationID) > parseInt(endStationID) && parseInt(startStationID) <= parseInt(aTimeTable.get(row, "GeneralTimetable", "GeneralTrainInfo", "StartingStationID")) && parseInt(endStationID) >= parseInt(aTimeTable.get(row, "GeneralTimetable", "GeneralTrainInfo", "EndingStationID")))) {
//                        System.out.println("3");

                        // check if the start and end station ID are both in the Stoptimes-stationID of this row
                        // and the departure time of start station is within input time ±5
                        int i;
                        for (i = 0; i < aTimeTable.getNumOfStopTimes(row); i++){
                            int j;
                            if (startStationID.equals(aTimeTable.get(row, i, "GeneralTimetable", "StopTimes", "StationID")) &&
                                (((time + 3) > parseInt(aTimeTable.get(row, i, "GeneralTimetable", "StopTimes", "DepartureTime").substring(0, 2))) &&
                                 (parseInt(aTimeTable.get(row, i, "GeneralTimetable", "StopTimes", "DepartureTime").substring(0, 2))>= time))) {
//                                System.out.println("4");

                                for (j = i + 1 ; j < aTimeTable.getNumOfStopTimes(row); j++) {
                                    if (endStationID.equals(aTimeTable.get(row, j, "GeneralTimetable", "StopTimes", "StationID"))) {
//                                        System.out.println("5");
                                        // check if the train has enough seats
                                        int k;
                                        String[] seatRemain = seatRemain(row, ticketType);
                                        if (seatRemain[0] != null){
                                            listOfValidTrainNo.add(new String[3]);
                                            listOfValidTrainNo.set(listOfValidTrainNo.size() - 1, seatRemain);
                                            returnValid = true;
                                        }
                                        else {
                                            System.out.println("There's no seats remain in train number: " + aTimeTable.get(row, "GeneralTimetable", "GeneralTrainInfo", "TrainNo"));
                                            returnValid = false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public String[] seatRemain(int rowInTimeTable, String ticketType){
        Seat nullSeat = new Seat();
        String[] seatIndex = new String[]{"A", "B", "C", "D", "E"};
        String[] windowSeatIndex = new String[]{"A", "E"};
        String[] aisleSeatIndex = new String[]{"C", "D"};

        boolean available = true;
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        // from car 1 to car 12
        for (i = 0; i < 12; i++) {
            if (ticketType.equals("business")) i = 5;
            if ((ticketType.equals("standard") && i != 5) || (ticketType.equals("business") && i == 5)){
                // from seats row 1 to row_ (different car has different row of seats), with specific car index
                for (j = 1; j <= nullSeat.getNumOfRow(i); j++){
                    if (preference == 1) {
                        for (k = 0; k < windowSeatIndex.length; k++) {

                            for (l = 0; l < aTimeTable.getNumOfStopTimes(rowInTimeTable); l++){
                                Seat aSeat = new Seat(aTimeTable.get(rowInTimeTable, "GeneralTimetable", "GeneralTrainInfo", "TrainNo"), aTimeTable.get(rowInTimeTable, l, "GeneralTimetable", "StopTimes", "StationID"));
//                               System.out.println("New available = " + aSeat.checkSeat(i, Integer.toString(j), seatIndex[k]));
                                available = aSeat.checkSeat(i, Integer.toString(j), windowSeatIndex[k]);
                                if (!available) break;
                            }
                            if (available) break;
                        }
                    }
                    else if (preference == 2) {
                        for (k = 0; k < aisleSeatIndex.length; k++) {

                            for (l = 0; l < aTimeTable.getNumOfStopTimes(rowInTimeTable); l++){
                                Seat aSeat = new Seat(aTimeTable.get(rowInTimeTable, "GeneralTimetable", "GeneralTrainInfo", "TrainNo"), aTimeTable.get(rowInTimeTable, l, "GeneralTimetable", "StopTimes", "StationID"));
//                               System.out.println("New available = " + aSeat.checkSeat(i, Integer.toString(j), seatIndex[k]));
                                available = aSeat.checkSeat(i, Integer.toString(j), aisleSeatIndex[k]);
                                if (!available) break;
                            }
                            if (available) break;
                        }
                    }
                    else {
                        for (k = 0; k < seatIndex.length; k++) {

                            for (l = 0; l < aTimeTable.getNumOfStopTimes(rowInTimeTable); l++) {
                                Seat aSeat = new Seat(aTimeTable.get(rowInTimeTable, "GeneralTimetable", "GeneralTrainInfo", "TrainNo"), aTimeTable.get(rowInTimeTable, l, "GeneralTimetable", "StopTimes", "StationID"));
//                               System.out.println("New available = " + aSeat.checkSeat(i, Integer.toString(j), seatIndex[k]));
                                available = aSeat.checkSeat(i, Integer.toString(j), seatIndex[k]);
                                if (!available) break;
                            }
                            if (available) break;
                        }
                    }
                    if (available) break;
                }
            }
            if (available) break;
        }
        Seat aSeat = new Seat(aTimeTable.get(rowInTimeTable, "GeneralTimetable", "GeneralTrainInfo", "TrainNo"), startStationID);

        if (available) {
            if (preference == 1) return new String[]{Integer.toString(i), Integer.toString(j), windowSeatIndex[k], aTimeTable.get(rowInTimeTable, "GeneralTimetable", "GeneralTrainInfo", "TrainNo")};
            else if (preference == 2) return new String[]{Integer.toString(i), Integer.toString(j), aisleSeatIndex[k], aTimeTable.get(rowInTimeTable, "GeneralTimetable", "GeneralTrainInfo", "TrainNo")};
            else return new String[]{Integer.toString(i), Integer.toString(j), seatIndex[k], aTimeTable.get(rowInTimeTable, "GeneralTimetable", "GeneralTrainInfo", "TrainNo")};
        }
        else return new String[4];
    }

    public static String getTravelTime(String startTime, String endTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date start = format.parse(startTime);
        Date end = format.parse(endTime);
        long dif = end.getTime() - start.getTime();
        long hour = dif / (1000 * 60 * 60);
        long min = dif % (1000 * 60 * 60) / (1000 * 60);
        return hour + ":" + min;
    }

    public String showValidTrainInfo() throws ParseException {
        // only print out available train
        // with train number, departure time, arrival time, travel time
        int i;

        System.out.println("From station ID " + startStationID + " to station ID " + endStationID);
        if (listOfValidTrainNo.size() == 0) System.out.println("There's no valid train");
        String s = "";
        for (i = 0; i < listOfValidTrainNo.size(); i++) {
            s += "(" + (i + 1) + ")";
            s += "\nTrain number: " + listOfValidTrainNo.get(i)[3];
            int j;
            for (j = 0; j < aTimeTable.getNumOfStopTimes(aTimeTable.getRowIndex(listOfValidTrainNo.get(i)[3])); j++) {
                if (aTimeTable.get(aTimeTable.getRowIndex(listOfValidTrainNo.get(i)[3]), j, "GeneralTimetable", "StopTimes", "StationID").equals(startStationID)) {
                    startTime = aTimeTable.get(aTimeTable.getRowIndex(listOfValidTrainNo.get(i)[3]), j, "GeneralTimetable", "StopTimes", "DepartureTime");
                    s += "\nDeparture time: " + startTime;
                }
                if (aTimeTable.get(aTimeTable.getRowIndex(listOfValidTrainNo.get(i)[3]), j, "GeneralTimetable", "StopTimes", "StationID").equals(endStationID)) {
                    endTime = aTimeTable.get(aTimeTable.getRowIndex(listOfValidTrainNo.get(i)[3]), j, "GeneralTimetable", "StopTimes", "DepartureTime");
                    s += "\nArrival time: " + endTime;
                }
            }
            s += "\nTravel time: " + getTravelTime(startTime, endTime);
            s += "\n-----------------------------------------------\n";
        }

        return s;
    }

    public void sellTicket() throws SoldBuySeatException {
        if (validIndex > listOfValidTrainNo.size()) {
            System.out.println("Invalid index");
        }
        else {
            // for every stop stations, create a "Seat" object
            // put the train number to return array list
            int i;
            int j;
            int carNo = parseInt((listOfValidTrainNo.get(validIndex)[0]));
            String rowIndex = listOfValidTrainNo.get(validIndex)[1];
            String colIndex = listOfValidTrainNo.get(validIndex)[2];
            String trainNo = listOfValidTrainNo.get(validIndex)[3];
            int realCarNo = carNo + 1;

            if (Integer.toString(realCarNo).length() == 1) {
                if (rowIndex.length() == 1) {
                    aTicket.setTicketInfo(_toOrFrom, "seats", "0" + Integer.toString(realCarNo) + "0" + rowIndex + colIndex);
                    if (!roundTrip) aTicket.setTicketInfo(1, "seats", "0" + Integer.toString(realCarNo) + "0" + rowIndex + colIndex);
                }
                else {
                    aTicket.setTicketInfo(_toOrFrom, "seats", "0" + Integer.toString(realCarNo) + rowIndex + colIndex);
                    if (!roundTrip) aTicket.setTicketInfo(1, "seats", "0" + Integer.toString(realCarNo) + rowIndex + colIndex);
                }
            }
            else {
                if (rowIndex.length() == 1) {
                    aTicket.setTicketInfo(_toOrFrom, "seats", Integer.toString(realCarNo) + "0" + rowIndex + colIndex);
                    if (!roundTrip) aTicket.setTicketInfo(1, "seats", Integer.toString(realCarNo) + "0" + rowIndex + colIndex);
                }
                else {
                    aTicket.setTicketInfo(_toOrFrom, "seats", Integer.toString(realCarNo) + rowIndex + colIndex);
                    if (!roundTrip) aTicket.setTicketInfo(1, "seats", Integer.toString(realCarNo) + rowIndex + colIndex);
                }
            }

            for (i = 0; i < aTimeTable.getNumOfStopTimes(aTimeTable.getRowIndex(trainNo)); i++){
                if (aTimeTable.get(aTimeTable.getRowIndex(trainNo), i, "GeneralTimetable", "StopTimes", "StationID").equals(startStationID)) {
                    for (j = i; j < aTimeTable.getNumOfStopTimes(aTimeTable.getRowIndex(trainNo)); j++) {
                        if (!aTimeTable.get(aTimeTable.getRowIndex(trainNo), j, "GeneralTimetable", "StopTimes", "StationID").equals(endStationID)) {
                            Seat aSeat = new Seat(trainNo, aTimeTable.get(aTimeTable.getRowIndex(trainNo), j, "GeneralTimetable", "StopTimes", "StationID"));
                            aSeat.soldSeat(carNo, rowIndex, colIndex);
                            aSeat.save();
                        }
                        if (aTimeTable.get(aTimeTable.getRowIndex(trainNo), j, "GeneralTimetable", "StopTimes", "StationID").equals(endStationID)) {
                            Seat aSeat = new Seat(trainNo, aTimeTable.get(aTimeTable.getRowIndex(trainNo), j, "GeneralTimetable", "StopTimes", "StationID"));
                            aSeat.soldSeat(carNo, rowIndex, colIndex);
                            aSeat.save();
                            break;
                        }
                    }
                }
            }
        }
    }

    public void generalPriceCalculator() {
        int i;
        for (i = 0; i < 12; i++){
            if (aPrice.get(i, "OriginStationID").equals(startStationID)) {
                int j;
                for (j = 0; j < aPrice.getNumOfDestinationStations(i); j++) {
                    if (aPrice.get(i, j, "DesrinationStations", "ID").equals(endStationID)) {
                        int k;
                        for (k = 0; k < 8; k++) {
                            if (aPrice.get(i, j, k, "DesrinationStations", "Fares", "TicketType").equals(ticketType)) {
                                setPrice(parseInt(aPrice.get(i, j, k, "DesrinationStations", "Fares", "Price")));
                            }
                        }
                    }
                }
            }
        }
    }

    public void universityPriceCalculator() {
        int indexInUni = aUniversityDiscount.getRowIndex(listOfValidTrainNo.get(validIndex)[3]);
        int i;

        if (indexInUni >= 0) {
            setTicketType(aUniversityDiscount.get(indexInUni, "DiscountTrains", "ServiceDayDiscount", getWeekOfDate(date)));
        }

        for (i = 0; i < 12; i++){
            if (aPrice.get(i, "OriginStationID").equals(startStationID)) {
                int j;
                for (j = 0; j < aPrice.getNumOfDestinationStations(i); j++) {
                    if (aPrice.get(i, j, "DesrinationStations", "ID").equals(endStationID)) {
                        int k;
                        for (k = 0; k < 8; k++) {
                            if (aPrice.get(i, j, k, "DesrinationStations", "Fares", "TicketType").equals(ticketType)) {
                                setPrice(parseInt(aPrice.get(i, j, k, "DesrinationStations", "Fares", "Price")));
                            }
                        }
                    }
                }
            }
        }
    }

    public void paymentDeadlineCalculator() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
        Date datenow = new Date();
        String dateNowStr = format.format(datenow);
        Calendar cal = Calendar.getInstance();
        int days = (int)((date.getTime() - datenow.getTime()) / (1000 * 3600 * 24));

        // 當日車票
        if (datenow.equals(date)) {

            String departure = strDate + " " + startTime;
            cal.setTime(datenow);
            int min = parseInt(departure.substring(14, 16));
            int hour = parseInt(departure.substring(11, 13));

            dformat.format((cal.getTime()));

            if (min < 30 && hour == 0) {
                cal.add(Calendar.DAY_OF_YEAR, -1);
                setPaymentDeadline(dformat.format(cal.getTime()) + " " + 23 + ":" + (min + 30));
            }
            else {
                if (min < 30) {
                    setPaymentDeadline(dformat.format(cal.getTime()) + " " + (hour - 1) + ":" + (min + 30));
                }
                else {
                    setPaymentDeadline(dformat.format(cal.getTime()) + " " + hour + ":" + (min - 30));
                }
            }

        }
        else if (days > 2) {
            cal.setTime(datenow);
            cal.add(Calendar.DAY_OF_YEAR, 2);

            setPaymentDeadline(format.format(cal.getTime()));
        }
        else if (days <= 2) {
            cal.setTime(datenow);
            cal.add(Calendar.DAY_OF_YEAR, 1);

            setPaymentDeadline(format.format(cal.getTime()));
        }

    }

    public void totalPriceCalculator() {
        int p;

        generalPriceCalculator();
        p = generalTicket * price;
        universityPriceCalculator();
        p += universityTicket * price;

        if (returnValid) setTotalPrice(p * 2);
        else setTotalPrice(p);

    }
}
