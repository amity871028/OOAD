package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.io.IOException;
//import ordering.*;

//import backend.*;

//主畫面介面
public class HomeView extends JFrame {
	private JPanel contentPane;
	private JButton goBookButton;
	private JButton goChangeButton;
	private JButton goSearchButton;
	private JButton goMealButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		HomeView frame = new HomeView();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public HomeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		goBookButton = new JButton("訂票");
		goBookButton.setBounds(405, 200, 150, 70);
		contentPane.add(goBookButton);

		goChangeButton = new JButton("退票");
		goChangeButton.setBounds(405, 400, 150, 70);
		contentPane.add(goChangeButton);

		goSearchButton = new JButton("查詢訂票");
		goSearchButton.setBounds(405, 600, 150, 70);
		contentPane.add(goSearchButton);

	}

	//button for BookView
	public void addGoBookButtonListener(ActionListener listener) {
		goBookButton.addActionListener(listener);
	}

	//button for ChangeView
	public void addGoCancelButtonListener(ActionListener listener) {
		goChangeButton.addActionListener(listener);
	}

	//button for SearchView
	public void addGoSearchButtonListener(ActionListener listener) {
		goSearchButton.addActionListener(listener);
	}

}