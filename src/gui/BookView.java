package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.*;

//無訂購回程票的介面
public class BookView extends JFrame {
	private JPanel contentPane;
	private JTextField uidIn;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_1_1;
	private JLabel label_1_2;
	private JComboBox comboBox;
	private JComboBox comboBox1;
	private JLabel label_2;
	private JRadioButton radioButton;
	private JRadioButton radioButton1;
	private JLabel label_3;
	private JRadioButton radioButton2;
	private JRadioButton radioButton3;
	private JRadioButton radioButton4;
	private JLabel label_4;
	private JRadioButton radioButton5;
	private JRadioButton radioButton6;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JCheckBox checkBox;
	private JTextField timeIn;
	private JTextField timeIn1;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JLabel label_11;
	private JComboBox comboBox2;
	private JComboBox comboBox3;
	private ButtonGroup buttonGroup;
	private ButtonGroup buttonGroup1;
	private ButtonGroup buttonGroup2;
	private JButton searchingButton;
	private JButton homeButton;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JLabel label_12;
	private JTextField toTime;
	private JLabel label_13;
	private JLabel label_14;
	private JTextField fromTime;
	private JLabel label_15;
	private JLabel label_16;
	private JTextField orderIndex;
	private JButton enterButton;
	private JLabel label_17;
	private JTextField trainNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		BookView frame = new BookView();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public BookView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		label = new JLabel("UID");
		label.setBounds(390, 94, 68, 19);
		contentPane.add(label);

		uidIn = new JTextField("");
		uidIn.setEnabled(true);
		uidIn.setBounds(460, 94, 150, 19);
		contentPane.add(uidIn);
		uidIn.setColumns(10);

		label_1 = new JLabel("起訖站");
		label_1.setBounds(390, 137, 68, 19);
		contentPane.add(label_1);

		label_1_1 = new JLabel("起程站");
		label_1_1.setBounds(460, 137, 68, 19);
		contentPane.add(label_1_1);

		String[] station = { "南港", "台北", "板橋", "桃園", "新竹", "苗栗", "台中", "彰化", "雲林", "嘉義", "台南", "左營" };
		comboBox = new JComboBox(station);
		comboBox.setBounds(500, 137, 68, 19);
		contentPane.add(comboBox);

		label_1_2 = new JLabel("到達站");
		label_1_2.setBounds(580, 137, 68, 19);
		contentPane.add(label_1_2);

		comboBox1 = new JComboBox(station);
		comboBox1.setBounds(620, 137, 68, 19);
		contentPane.add(comboBox1);

		label_2 = new JLabel("車廂種類");
		label_2.setBounds(390, 180, 68, 19);
		contentPane.add(label_2);

		radioButton = new JRadioButton("標準車廂");
		radioButton.setBounds(460, 180, 80, 19);
		contentPane.add(radioButton);

		radioButton1 = new JRadioButton("商務車廂");
		radioButton1.setBounds(540, 180, 80, 19);
		contentPane.add(radioButton1);

		buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButton);
		buttonGroup.add(radioButton1);

		label_3 = new JLabel("座位喜好");
		label_3.setBounds(390, 223, 68, 19);
		contentPane.add(label_3);

		radioButton2 = new JRadioButton("無");
		radioButton2.setBounds(460, 223, 80, 19);
		contentPane.add(radioButton2);

		radioButton3 = new JRadioButton("靠窗優先");
		radioButton3.setBounds(540, 223, 80, 19);
		contentPane.add(radioButton3);

		radioButton4 = new JRadioButton("走道優先");
		radioButton4.setBounds(620, 223, 80, 19);
		contentPane.add(radioButton4);

		buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(radioButton2);
		buttonGroup1.add(radioButton3);
		buttonGroup1.add(radioButton4);

		label_4 = new JLabel("訂位方式");
		label_4.setBounds(390, 266, 68, 19);
		contentPane.add(label_4);

		radioButton5 = new JRadioButton("依時間搜尋");
		radioButton5.setBounds(460, 266, 100, 19);
		contentPane.add(radioButton5);

		radioButton6 = new JRadioButton("輸入車次號碼");
		radioButton6.setBounds(560, 266, 100, 19);
		contentPane.add(radioButton6);

		buttonGroup2 = new ButtonGroup();
		buttonGroup2.add(radioButton5);
		buttonGroup2.add(radioButton6);

		label_5 = new JLabel("時間");
		label_5.setBounds(390, 309, 68, 19);
		contentPane.add(label_5);

		label_6 = new JLabel("去程");
		label_6.setBounds(460, 309, 68, 19);
		contentPane.add(label_6);

//		label_7 = new JLabel("回程");
//		label_7.setBounds(460, 329, 68, 19);
//		contentPane.add(label_7);

//		checkBox = new JCheckBox("訂購回程");
//		checkBox.setBounds(700, 329, 150, 19);
//		contentPane.add(checkBox);
//		checkBox.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent e) {
//				if (e.getStateChange() == 1) {
//					timeIn1.setEnabled(true);
//					fromTime.setEnabled(true);
//				}
//				else {
//					timeIn1.setEnabled(false);
//					fromTime.setEnabled(false);
//				}
//					
//			}
//		});

		timeIn = new JTextField();
		timeIn.setEnabled(true);
		timeIn.setBounds(500, 309, 68, 19);
		contentPane.add(timeIn);
		timeIn.setColumns(10);

//		timeIn1 = new JTextField();
//		timeIn1.setEnabled(false);
//		timeIn1.setBounds(500, 329, 68, 19);
//		contentPane.add(timeIn1);
//		timeIn1.setColumns(10);

		label_8 = new JLabel("日期輸入格式 xxxx-xx-xx");
		label_8.setBounds(700, 309, 150, 19);
		contentPane.add(label_8);
		
		label_12 = new JLabel("約");
		label_12.setBounds(580, 309, 20, 19);
		contentPane.add(label_12);
		
		toTime = new JTextField();
		toTime.setEnabled(true);
		toTime.setBounds(600, 309, 20, 19);
		contentPane.add(toTime);
		
		label_13 = new JLabel("點出發");
		label_13.setBounds(625, 309, 40, 19);
		contentPane.add(label_13);
		
		label_17 = new JLabel("車次號碼");
		label_17.setBounds(580, 330, 60, 19);
		contentPane.add(label_17);
		
		trainNumber = new JTextField();
		trainNumber.setEnabled(true);
		trainNumber.setBounds(640, 330, 50, 19);
		contentPane.add(trainNumber);
		
//		label_14 = new JLabel("約");
//		label_14.setBounds(580, 329, 20, 19);
//		contentPane.add(label_14);
//		
//		fromTime = new JTextField();
//		fromTime.setEnabled(false);
//		fromTime.setBounds(600, 329, 20, 19);
//		contentPane.add(fromTime);
//		
//		label_15 = new JLabel("點出發");
//		label_15.setBounds(625, 329, 40, 19);
//		contentPane.add(label_15);

		label_9 = new JLabel("票數");
		label_9.setBounds(390, 352, 68, 19);
		contentPane.add(label_9);

		label_10 = new JLabel("全票");
		label_10.setBounds(460, 352, 68, 19);
		contentPane.add(label_10);

		label_11 = new JLabel("大學生優惠票");
		label_11.setBounds(460, 372, 80, 19);
		contentPane.add(label_11);

		Integer[] ticket = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		comboBox2 = new JComboBox(ticket);
		comboBox2.setBounds(560, 352, 68, 19);
		contentPane.add(comboBox2);

		comboBox3 = new JComboBox(ticket);
		comboBox3.setBounds(560, 372, 68, 19);
		contentPane.add(comboBox3);

		searchingButton = new JButton("搜尋");
		searchingButton.setBounds(800, 380, 68, 19);
		contentPane.add(searchingButton);

		homeButton = new JButton("回主畫面");
		homeButton.setBounds(800, 50, 90, 19);
		contentPane.add(homeButton);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(390, 430, 300, 300);
		contentPane.add(scrollPane);
		
		label_16 = new JLabel("請輸入代號");
		label_16.setBounds(750, 500, 100, 19);
		contentPane.add(label_16);
		
		orderIndex = new JTextField();
		orderIndex.setEnabled(true);
		orderIndex.setBounds(820, 500, 20, 19);
		contentPane.add(orderIndex);
		orderIndex.setColumns(10);
		
		enterButton = new JButton("確認");
		enterButton.setBounds(800, 650, 68, 19);
		contentPane.add(enterButton);
	}

	public String getuidIn() {
		return (uidIn.getText());
	}
	
	public String getStationFromID() {
		//comboBox.addActionListener(comboBox);
		String ID = "";
		if (comboBox.getSelectedIndex() == 0)
			ID = "0990";
		if (comboBox.getSelectedIndex() == 1)
			ID = "1000";
		if (comboBox.getSelectedIndex() == 2)
			ID = "1010";
		if (comboBox.getSelectedIndex() == 3)
			ID = "1020";
		if (comboBox.getSelectedIndex() == 4)
			ID = "1030";
		if (comboBox.getSelectedIndex() == 5)
			ID = "1035";
		if (comboBox.getSelectedIndex() == 6)
			ID = "1040";
		if (comboBox.getSelectedIndex() == 7)
			ID = "1043";
		if (comboBox.getSelectedIndex() == 8)
			ID = "1047";
		if (comboBox.getSelectedIndex() == 9)
			ID = "1050";
		if (comboBox.getSelectedIndex() == 10)
			ID = "1060";
		if (comboBox.getSelectedIndex() == 11)
			ID = "1070";
		return ID;
	}
	
	public String getStationToID() {
		String ID = "";
		if (comboBox1.getSelectedIndex() == 0)
			ID = "0990";
		if (comboBox1.getSelectedIndex() == 1)
			ID = "1000";
		if (comboBox1.getSelectedIndex() == 2)
			ID = "1010";
		if (comboBox1.getSelectedIndex() == 3)
			ID = "1020";
		if (comboBox1.getSelectedIndex() == 4)
			ID = "1030";
		if (comboBox1.getSelectedIndex() == 5)
			ID = "1035";
		if (comboBox1.getSelectedIndex() == 6)
			ID = "1040";
		if (comboBox1.getSelectedIndex() == 7)
			ID = "1043";
		if (comboBox1.getSelectedIndex() == 8)
			ID = "1047";
		if (comboBox1.getSelectedIndex() == 9)
			ID = "1050";
		if (comboBox1.getSelectedIndex() == 10)
			ID = "1060";
		if (comboBox1.getSelectedIndex() == 11)
			ID = "1070";
		return ID;
	}

	public String getStandardOrBusiness() {
		if (radioButton.isSelected())
			return "standard";
		else if (radioButton1.isSelected())
			return "business";
		else
			return null;
	}

	public int getSeatPrefer() {
		if (radioButton2.isSelected())
			return 0;
		if (radioButton3.isSelected())
			return 1;
		if (radioButton4.isSelected())
			return 2;
		else
			return 3;
	}

	public String getOrderType() {
		if (radioButton5.isSelected())
			return "0";
		else if (radioButton6.isSelected())
			return "1";
		else
			return null;
	}

	public String getTimeIn() {
		return (timeIn.getText());
	}

	public String getTime1In() {
		return (timeIn1.getText());
	}

	public int getNumberOfTicket() {
		return (comboBox2.getSelectedIndex());
	}

	public int getNumberOfDiscount() {
		return (comboBox3.getSelectedIndex());
	}
	
	public int getToTime() {
		return (Integer.parseInt(toTime.getText()));
	}
	
	public int getFromTime() {
		return (Integer.parseInt(fromTime.getText()));
	}
	
	public int getOrderIndex() {
		return (Integer.parseInt(orderIndex.getText()));
	}
	
	public String getTrainNumber() {
		return (trainNumber.getText());
	}
	
	//button for search result
	public void addSearchingButtonListener(ActionListener listener) {
		searchingButton.addActionListener(listener);
	}
	
	//button for booking
	public void addBookingButtonListener(ActionListener listener) {
		enterButton.addActionListener(listener);
	}

	//button for HomeView
	public void addHomeButtonListener(ActionListener listener) {
		homeButton.addActionListener(listener);
	}
	
	//output search result
	public void output(String a) {
		textArea.append(a);
	}
	
	public void clear() {
		uidIn.setText("");
		timeIn.setText("");
		//timeIn1.setText("");
		toTime.setText("");
		//fromTime.setText("");
		trainNumber.setText("");
		buttonGroup.clearSelection();
		buttonGroup1.clearSelection();
		buttonGroup2.clearSelection();
		textArea.setText("");
		comboBox.setSelectedIndex(0);
		comboBox1.setSelectedIndex(0);
		comboBox2.setSelectedIndex(0);
		comboBox3.setSelectedIndex(0);
		orderIndex.setText("");
	}
}