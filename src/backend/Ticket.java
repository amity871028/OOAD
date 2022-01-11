package backend;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 一張票就是一張Ticket object
 * 你可以由getter setter得到ticket的任何資訊
 * 要注意的是，仔細看原始json檔用的value是什麼type，若要用setter，應當要傳入正確的型態
 * 若賣出一張ticket，就要new出一個Ticket object，並用setter對所有欄位做設定，
 * 不然新new的Ticket object所有欄位會用預設值帶入
 * @author kobemary
 *
 */
public class Ticket {
    //int toOrFrom;
    JSONObject ticket = new JSONObject("{\n"
            + "        \"code\": \"123456789\",\n"
            + "        \"uid\": \"A123456789\",\n"
            + "        \"ticketInfo\":[\n"
            + "            {\n"
            + "                \"date\" : \"2021-06-01\",\n"
            + "                \"ticketsType\" : \"standard\",\n"
            + "                \"ticketsCountA\" : 1,\n"
            + "                \"ticketsCountB\" : 2,\n"
            + "                \"start\" : \"0990\",\n"
            + "                \"end\" : \"1070\",\n"
            + "                \"seats\": [\"0413A\"]\n"
            + "            },\n"
            + "            {\n"
            + "                \"date\" : \"2021-06-01\",\n"
            + "                \"ticketsType\" : \"standard\",\n"
            + "                \"ticketsCountA\" : 1,\n"
            + "                \"ticketsCountB\" : 2,\n"
            + "                \"start\" : \"0990\",\n"
            + "                \"end\" : \"1070\",\n"
            + "                \"seats\": [\"0413A\"]\n"
            + "            }\n"
            + "        ],\n"
            + "        \"payDeadline\" : \"2021-05-25\",\n"
            + "        \"payment\" : 550\n"
            + "    }");


    public int getToAndFrom() {
        return 1;

    }

    /**
     * 因為key="tiecktInfo"的欄位比較複雜，在此做解釋
     * 第一個參數_toOrFrom表示的是你要拿的是去程還是回程的資料, 0表去程, 1表回來，若兩者一樣，表示單程票
     * key值就是ticketInfo裡面對應的key
     * @param _toOrFrom 0 represent first ticket element; 1 represent first ticket element
     * @param key key of each element in ticketInfo array
     * @return A String type value. It would be like 2021-06-01, 1, or 0413A
     */

    public String getTicketInfo(int _toOrFrom,  String key) {
        switch (key) {
            case "seats":
                // get seats
                return ticket.getJSONArray("ticketInfo").getJSONObject(_toOrFrom).getJSONArray(key).getString(0);
            case "ticketsCountA":
                // get ticketsCountA
                return "" + ticket.getJSONArray("ticketInfo").getJSONObject(_toOrFrom).getInt(key);
            case "ticketsCountB":
                // get ticketsCountB
                return "" + ticket.getJSONArray("ticketInfo").getJSONObject(_toOrFrom).getInt(key);
            default:
                // get date, ticketsType, start, or end
                return ticket.getJSONArray("ticketInfo").getJSONObject(_toOrFrom).getString(key);
        }
    }
    public String getCode() {
        return ticket.getString("code");
    }
    public String getUid() {
        return ticket.getString("uid");
    }
    public String getPayDeadLine() {
        return ticket.getString("payDeadline");
    }
    public String getPayment() {
        return ""+ticket.getInt("payment");
    }


    /**
     * This is used to set date, ticketsType, ticketsCount, start, end, or seats
     * 因為key="tiecktInfo"的欄位比較複雜，在此做解釋
     * 第一個參數_toOrFrom表示的是你要拿的是去程還是回程的資料, 0表去程, 1表回來，若兩者一樣，表示單程票
     * key值就是ticketInfo裡面對應的key
     * input就是你數入的value
     * input type 可以是String int
     * 寫入seats的時候要注意，只需輸入""內的字串
     * eg. 想存成["hello"]，則第三個參數input則為hello 字串
     * @param _toOrFrom int type variable to indicate the index of ticketInfo array
     * @param key A string that indicate the key. It must be date, ticketsType, ticketsCount, start, end, or seats
     * @param input A string of value. Be aware of that if you are setting "seat", input variable must be a string that inside [].
     */



    public void setTicketInfo(int _toOrFrom,  String key, Object input) {
        switch (key) {
            case "seats":
                // set seats
                String b = new String((String) input);
                JSONArray tmp = new JSONArray("["+b+"]");
                ticket.getJSONArray("ticketInfo").getJSONObject(_toOrFrom).put(key, tmp);
                break;
            case "ticketsCountA":
                // set ticketsCountA
                ticket.getJSONArray("ticketInfo").getJSONObject(_toOrFrom).put( key, (int) input);
            case "ticketsCountB":
                // set ticketsCountB
                ticket.getJSONArray("ticketInfo").getJSONObject(_toOrFrom).put( key, (int) input);
                break;
            default:
                // set date, ticketsType, start, or end
                String intToString = String.valueOf(input);
                ticket.getJSONArray("ticketInfo").getJSONObject(_toOrFrom).put( key, (String) input);
                break;
        }
    }
    public void setCode(String input) {
        ticket.put("code", input);
    }
    public void setUid(String input) {
        ticket.put("uid", input);
    }
    public void setPayDeadLine(String input) {
        ticket.put("payDeadline", input);
    }
    public void setPayment(int input) {
        ticket.put("payment", input);
    }

    public void setTicket(JSONObject ticket) {
        this.ticket = ticket;
    }

    /**
     * DON'T use this constructor
     * This's only for Mary to use
     * Plan to be copy constructor
     * @param input JSONObject type
     */
    public Ticket(JSONObject input) {

        ticket = input;
    }
    public Ticket() {
    }
    public String toString() {
        return ticket.toString();
    }

    public static void main(String[] args) {
        Ticket t = new Ticket();
        //抓出各個欄位的資訊
        //System.out.println(t);
        //System.out.println(t.toString());
		System.out.println(t.getCode());
		System.out.println(t.getUid());
		System.out.println(t.getPayment());
		System.out.println(t.getPayDeadLine());
		System.out.println(t.getTicketInfo(0, "date"));
		System.out.println(t.getTicketInfo(0, "ticketsCountA"));
		System.out.println(t.getTicketInfo(0, "seats"));
		System.out.println(t.getTicketInfo(1, "ticketsCountA"));

        //設定個欄位
		System.out.println(t.getCode());
		t.setCode("987654");
		System.out.println(t.getCode());

		System.out.println(t.getTicketInfo(0, "ticketsCountA"));
		t.setTicketInfo(0, "ticketsCountA", 232);
		System.out.println(t.getTicketInfo( 0, "ticketsCountA"));

         //新增一個張ticket就直接new一個Ticket物件，但記得要處存起來一定要用TicketManager add()，然後save()
		Ticket nt = new Ticket();

		nt.setCode("987654");
		System.out.println(nt.getCode());
		nt.setUid("A987654321");
		System.out.println(nt.getUid());

    }

}