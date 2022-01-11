package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

//查詢訂票介面
public class SearchBookView extends JFrame {
	private JPanel contentPane;
	private JLabel uidLabel;
	private JLabel ticketIdLabel;
	private JTextField uidIn;
	private JTextField referNumber;
	private JButton searchingButton;
	private JButton homeButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SearchBookView frame = new SearchBookView();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public SearchBookView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		uidLabel = new JLabel("UID");
		uidLabel.setBounds(390, 94, 68, 19);
		contentPane.add(uidLabel);

		ticketIdLabel = new JLabel("訂位代號");
		ticketIdLabel.setBounds(390, 137, 68, 19);
		contentPane.add(ticketIdLabel);

		uidIn = new JTextField();
		uidIn.setEnabled(true);
		uidIn.setBounds(460, 94, 150, 19);
		contentPane.add(uidIn);
		uidIn.setColumns(10);

		referNumber = new JTextField();
		referNumber.setEnabled(true);
		referNumber.setBounds(460, 137, 150, 19);
		contentPane.add(referNumber);
		referNumber.setColumns(10);

		searchingButton = new JButton("搜尋");
		searchingButton.setBounds(800, 450, 68, 19);
		contentPane.add(searchingButton);

		homeButton = new JButton("回主畫面");
		homeButton.setBounds(800, 50, 90, 19);
		contentPane.add(homeButton);
	}
	
	public String getuidIn() {
		return (uidIn.getText());
	}
	
	public String getReferNumber() {
		return (referNumber.getText());
	}
	
	//button for search result
	public void addSearchingButtonListener(ActionListener listener) {
		searchingButton.addActionListener(listener);
	}
	
	//button for HomeView
	public void addHomeButtonListener(ActionListener listener) {
		homeButton.addActionListener(listener);
	}
	
	public void clear() {
		uidIn.setText("");
		referNumber.setText("");
	}
}