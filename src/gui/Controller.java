package gui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import BookingSystem.*;
import backend.SoldBuySeatException;
import backend.TicketManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Controller {
	private int index;
	private int result;
	private TicketManager aTicketManager;
	private generalBooking order;
	private Unbooking unbooking;
	private LookInto lookinto;
	private HomeView homeView;
	private BookView bookView;
	private CancelView cancelView;
	private SearchBookView searchBookView;

	public Controller() {
		index = 1;
		aTicketManager = new TicketManager();
		order = new generalBooking();
		unbooking = new Unbooking();
		lookinto = new LookInto();

		// 主頁面
		homeView = new HomeView();
		homeView.addGoBookButtonListener(new GoBookButtonListener());
		homeView.addGoCancelButtonListener(new GoCancelButtonListener());
		homeView.addGoSearchButtonListener(new GoSearchBookButtonListener());
		
		// 訂票頁面
		bookView = new BookView();
		bookView.addSearchingButtonListener(new SearchingButtonListener());
		bookView.addBookingButtonListener(new BookingButtonListener());
		bookView.addHomeButtonListener(new GoHomeListener());

		// 退票頁面
		cancelView = new CancelView();
		cancelView.addCancelButtonListener(new GoCancelButtonListener());
		cancelView.addHomeButtonListener(new GoHomeListener());

		// 訂票查詢頁面
		searchBookView = new SearchBookView();
		searchBookView.addSearchingButtonListener(new GoSearchButtonListener());
		searchBookView.addHomeButtonListener(new GoHomeListener());

		homeView.setVisible(true);
	}
	

	// GoHomeView
	public class GoHomeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			homeView.setVisible(true);
			bookView.setVisible(false);
			bookView.clear();
			cancelView.setVisible(false);
			cancelView.clear();
			searchBookView.setVisible(false);
			searchBookView.clear();
		}
	}

	// HomeView Begins
	public class GoBookButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			bookView.setVisible(true);
			homeView.setVisible(false);
		}
	}
	
	public class GoCancelButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			cancelView.setVisible(true);
			homeView.setVisible(false);
		}
	}

	public class GoSearchBookButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			searchBookView.setVisible(true);
			homeView.setVisible(false);
		}
	}
	// HomeView Ends
	
	// SearchBookView Begins
	public class GoSearchButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			lookinto = new LookInto();
			String a = searchBookView.getReferNumber() + " " + searchBookView.getuidIn();
	        System.out.println(a);
			try {
				JOptionPane.showMessageDialog(searchBookView, "BookingHistory: " + lookinto.BookingHistory(searchBookView.getReferNumber(), searchBookView.getuidIn()));
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (wrongInputException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	// SearchBookView Ends

	// BookView Begins
	public class SearchingButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			order.setCode(index);
			aTicketManager = new TicketManager();
			try {
				order.setRoundTrip("0");
			} catch (wrongInputException e2) {
				e2.printStackTrace();
			}
			try {
				order.setUid(bookView.getuidIn());
			} catch (wrongInputException e2) {
				e2.printStackTrace();
			}
			order.setStartStationID(bookView.getStationFromID());
			order.setEndStationID(bookView.getStationToID());
			order.setTicketType(bookView.getStandardOrBusiness());
			order.setPreference(bookView.getSeatPrefer());
			try {
				order.setGeneralTicket(String.valueOf(bookView.getNumberOfTicket()));
			} catch (wrongInputException e2) {
				e2.printStackTrace();
			}
			try {
				order.setUniversityTicket(String.valueOf(bookView.getNumberOfDiscount()));
			} catch (wrongInputException e2) {
				e2.printStackTrace();
			}
			String searchChoice = bookView.getOrderType();
			if (searchChoice.equals("0")) {
				order.setDate(bookView.getTimeIn());
				try {
					order.findValidTrainNo(bookView.getToTime());
				} catch (SoldBuySeatException e2) {
					e2.printStackTrace();
				}
				try {
					bookView.output(order.showValidTrainInfo());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			} else if (searchChoice.equals("1")) {
				order.setDate(bookView.getTimeIn());
				order.setListOfValidTrainNo2(bookView.getTrainNumber());
				try {
					bookView.output(order.showValidTrainInfo());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			} else
				System.out.println("Invalid input!");
		}
	}

	public class BookingButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			order.setValidIndex(bookView.getOrderIndex());
			try {
				order.sellTicket();
			} catch (SoldBuySeatException e1) {
				e1.printStackTrace();
			}
			try {
				order.paymentDeadlineCalculator();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			order.totalPriceCalculator();
			aTicketManager.addTicket(order.getaTicket());
			aTicketManager.save("booking.json");
			String code = aTicketManager.getTicketObj(index + 1).getCode();
			String uid = aTicketManager.getTicketObj(index + 1).getUid();
			String date_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "date");
			String type_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "ticketsType");
			String generalTicket_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "ticketsCountA");
			String uniTicket_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "ticketsCountB");
			String start_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "start");
			String end_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "end");
			String seat_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "seats");
			String price = aTicketManager.getTicketObj(index + 1).getPayment();
			String deadline = aTicketManager.getTicketObj(index + 1).getPayDeadLine();
			String information = "Ticket code: " + code + "\nUser ID: " + uid + "\nDate: " + date_0 + "\nTicket type: "
					+ type_0 + "\nNumber of general tickets: " + generalTicket_0
					+ "\nNumber of university discount tickets: " + uniTicket_0 + "\nStart station: " + start_0
					+ "\nEnd station: " + end_0 + "\nSeat: " + seat_0 + "\nTotal price: " + price
					+ "\nThe payment deadline: " + deadline;
			JOptionPane.showMessageDialog(bookView, information);
			order = new generalBooking();
			index = index + 1;
		}
	}
	//BookView Ends

}
