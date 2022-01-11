package backend;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;

import org.json.*;

/**
 * This class you will use to get the ArrayList of all tickets
 * 你可以想想這是一個array，裡面放的是一張張Ticket
 * 通常拿到Ticket後就是對Ticket class的操作了
 * 別忘了若有更改ArrayList of all ticket，要call save()，才會把更新內容存入資料庫
 *
 * @author kobemary
 *
 */
public class TicketManager {
    private static final String filePath = new String(System.getProperty("user.dir")+"/src/backend/file/booking.json");
    private JSONArray ticketArr;
    private ArrayList<Ticket> ticketArrL = new ArrayList<Ticket>();
    private String bookingPath;

    /**
     * 退票時使用，要先算出對應的index，當作參數傳入
     * @param index int type variable that indicate the index of the ArrayList you want to remove
     */
    public void removeTicket(int index) {
        ticketArrL.remove(index);
    }
    /**
     * 通常賣出一張票的時候會用到，要先自己準備好要加入的Ticket object，然後當作參數傳入
     * @param input Ticket type object that you want to add to the ArrayList
     */
    public void addTicket(Ticket input) {
        ticketArrL.add(input);
    }

    /**
     *把ArrayList of Ticket以字串形式回傳
     * @return A string form of ArrayList of Ticket
     */
    public String toString() {
        return ticketArrL.toString();
    }
    public int getSize() {
        // the number of sold tickets
        return ticketArrL.size();
    }
    /**
     * 從ArrayList of Ticket拿到指定的Ticket object
     * @param bookingIndex int type, 指定要拿的Ticket index
     * @return Ticket object
     */
    public Ticket getTicketObj(int bookingIndex) {
        return ticketArrL.get(bookingIndex);
    }
    /**
     * 若有更新內容，結束時應該要call 此method，才會把更新的內容存入資料庫，且會覆蓋讀入的檔案
     */
    public void save() {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(ticketArrL.toString());
            writer.close();
        }catch(Exception e) {
            System.out.println("Saving file went wrong");
        }
        System.out.println("The file has been saved");
    }

    /**
     * This is used to save new ooking.json when you want to create a new file instead of overwrite the old one
     * The new file will be created in the same directory of old one
     * @param newFileName A String type to indicate your new saved file name
     */
    public void save(String newFileName) {

        try {
            FileWriter writer = new FileWriter(System.getProperty("user.dir")+"/src/backend/file/"+newFileName);
            writer.write(ticketArrL.toString());
            writer.close();
        }catch(Exception e) {
            System.out.println("Saving file went wrong");
        }
        System.out.println("The file has been saved");
    }

    public TicketManager(){
        String jsonString = new String();
        try {
            File file =  new File(filePath);
            Scanner scan = new Scanner(file);
            while(scan.hasNext()) {
                jsonString = jsonString + scan.nextLine();
            }

        }catch(Exception e) {
            System.out.println("There may be something wrong with file path:"+filePath);
        }
        this.ticketArr = new JSONArray(jsonString);
        for (int i=0; i<ticketArr.length(); i++) {
            Ticket ticket = new Ticket(ticketArr.getJSONObject(i));
            ticketArrL.add(ticket);
        }
    }


    public static void main(String[] args) {
        TicketManager tm = new TicketManager();
        System.out.println(tm.toString());
        System.out.println("(1)    " + tm.getSize());
        System.out.println(tm.getTicketObj(0));
        System.out.println(tm.getTicketObj(0).getUid());
        System.out.println(tm.getTicketObj(0).getCode());
        System.out.println(tm.getTicketObj(0).getPayDeadLine());
        System.out.println(tm.getTicketObj(0).getPayment());
        System.out.println(tm.getTicketObj(0).getTicketInfo(0, "date"));
        System.out.println(tm.getTicketObj(0).getTicketInfo(0, "ticketsCountA"));
        System.out.println(tm.getTicketObj(0).getTicketInfo(0, "seats"));
        System.out.println(tm.getTicketObj(0).getTicketInfo(0, "ticketsType"));
        System.out.println();
        //add a new Ticket

        Ticket t = new Ticket();
        System.out.println(t);
        t.setUid("r09525126");
        t.setTicketInfo(0, "date", "TODAY");
        t.setTicketInfo(0, "ticket", "1");

        System.out.println(tm.toString());
        System.out.println("(2)    " + tm.getSize());

        tm.addTicket(t);

        System.out.println(tm.toString());
        System.out.println("(3)    " + tm.getSize());
        System.out.println();

        // remove a new Ticket
        System.out.println(tm.toString());
        System.out.println(tm.getSize());

        tm.removeTicket(0);

        System.out.println(tm.toString());
        System.out.println(tm.getSize());

    }

}
