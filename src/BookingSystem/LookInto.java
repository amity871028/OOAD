package BookingSystem;

import java.text.*;
import java.util.*;

import backend.*;

/**
 * 查詢訂票紀錄
 *
 * @author 
 */

public class LookInto {
    TicketManager tm = new TicketManager();

    /**
     * 輸入日期會得到該日的星期英文全名
     *
     * @param date 日期
     * @return 該日的星期英文全名
     * @throws ParseException
     */
    private String DateToDay(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date temp = dateFormat.parse(date);
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        return dayFormat.format(temp);
    }

    /**
     * 輸入車站編號會得到該車站中文名稱
     *
     * @param stationID 車站編號
     * @return 該車站中文名稱
     */
    private String StationIDToStationName(String stationID) {
        switch (stationID) {
            case "0990":
                return "南港";
            case "1000":
                return "台北";
            case "1010":
                return "板橋";
            case "1020":
                return "桃園";
            case "1030":
                return "新竹";
            case "1035":
                return "苗栗";
            case "1040":
                return "台中";
            case "1043":
                return "彰化";
            case "1047":
                return "雲林";
            case "1050":
                return "嘉義";
            case "1060":
                return "台南";
            case "1070":
                return "左營";
        }
        return "??";
    }

    /**
     * 查詢訂票紀錄
     *
     * @param code 訂票號碼
     * @param uid 使用者代號
     * @return 訂票紀錄
     * @throws wrongInputException
     */
    public String BookingHistory(String code, String uid) throws wrongInputException {
        ArrayList searchResult = new ArrayList();
        boolean flag = true;
        String result = "";
        for (int i = 0; i < tm.getSize(); i++) {
            if (tm.getTicketObj(i).getCode().equals(code) && tm.getTicketObj(i).getUid().equals(uid)) {
                result = "\nBooking Code: " + tm.getTicketObj(i).getCode();
                result = result + "\nUser ID: " + tm.getTicketObj(i).getUid();
                result = result + "\nDate: " + tm.getTicketObj(i).getTicketInfo(0, "date");
                result = result + "\nTickets Type: " + tm.getTicketObj(i).getTicketInfo(0, "ticketsType");
                
/* 註解的地方為bug，請修正！ */
                
//                result = result + "\nGeneral Tickets: " + tm.getTicketObj(i).getTicketInfo(0, "ticketsCountA");
//                result = result + "\nUniversity Tickets: " + tm.getTicketObj(i).getTicketInfo(0, "ticketsCountB");
//                result = result + "\nFrom: " + StationIDToStationName(tm.getTicketObj(i).getTicketInfo(0, "start"));
//                result = result + "\nTo: " + StationIDToStationName(tm.getTicketObj(i).getTicketInfo(0, "end"));
                result = result + "\nSeats: " + tm.getTicketObj(i).getTicketInfo(0, "seats");
                result = result + "\nPay Deadline: " + tm.getTicketObj(i).getPayDeadLine();
                result = result + "\nPayment: " + tm.getTicketObj(i).getPayment() + "\n";
                searchResult.add(result);
                flag = false;
                break;
            }
        }
        if (flag) throw new wrongInputException("NO BOOKING HISTORY FOUND");
        return searchResult.toString();
    }
}