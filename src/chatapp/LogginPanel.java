package chatapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogginPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField textField;

	private JPanel chatPane;
	private JPanel accountPanel;
	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private JPasswordField passwordField;

	public void connectToDB() {

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/test?"
							+ "user=root&password=admin1");

			connect.createStatement();
			System.out.println("hello my database");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String getEmail() {
		return textField.getText();
	}

	public void setEmail(String email) {
		this.textField.setText(email);
	}

	public char[] getPassword() {
		return passwordField.getPassword();
	}

	public void setPassword(String password) {
		this.passwordField.setText(password);
	}

	public Boolean isRegistredUser() {

		try {
			
			String sqlQuery = "select email,password from user where email=? and password=?";
			
			preparedStatement = connect.prepareStatement(sqlQuery);
			preparedStatement.setString(1, getEmail());
			preparedStatement.setString(2, new String(getPassword()));
			ResultSet r = preparedStatement.executeQuery();
			if(r.next()) {
				String sqlquery = "update user set state='1' where email=?";
				preparedStatement=connect.prepareStatement(sqlquery);
				preparedStatement.setString(1, getEmail());
				preparedStatement.executeUpdate();
				return true;
			} else {
				return false;
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
		
	}

	public LogginPanel(final JFrame mainFrame) {
		connectToDB();
		JLabel lblEmail = new JLabel("email:");

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblPassword = new JLabel("password:");

		JButton btnConnect = new JButton("connect");
		btnConnect.addActionListener(new ActionListener() {
			private UserInfo user;

			public void actionPerformed(ActionEvent arg0) {

				if (isRegistredUser()) {
					user = UserInfo.getInstance();
					user.setEmail(getEmail());
					chatPane = new ChatPanel(mainFrame);
					mainFrame.setContentPane(chatPane);
					mainFrame.setVisible(true);
					revalidate();
					repaint();
				}

			}
		});

		JButton btnCreateNewAccount = new JButton("create new account");
		btnCreateNewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accountPanel = new AccountPanel(mainFrame);
				mainFrame.setContentPane(accountPanel);
				mainFrame.setVisible(true);
				revalidate();
				repaint();
			}
		});
		
		passwordField = new JPasswordField();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(45)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEmail)
								.addComponent(lblPassword))
							.addGap(32)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(passwordField)
								.addComponent(textField)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(73)
							.addComponent(btnConnect)
							.addGap(49)
							.addComponent(btnCreateNewAccount)))
					.addContainerGap(130, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(59)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(57)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnConnect)
						.addComponent(btnCreateNewAccount))
					.addContainerGap(97, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
