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
import javax.swing.JTextField;

public class UpdatePanel extends JPanel {
	private LogginPanel loginPanel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private Connection connect = null;
	private PreparedStatement preparedStatement = null;

	public void connectToDB() {

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/test?"
							+ "user=root&password=admin1");

			connect.createStatement();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public String getName() {
		return textField.getText();
	}

	public void setName(String name) {
		this.textField.setText(name);
	}

	public String getFamilyName() {
		return textField_1.getText();
	}

	public void setFamilyName(String familyName) {
		this.textField_1.setText(familyName);
	}

	public String getUserName() {
		return textField_2.getText();
	}

	public void setUserName(String userName) {
		this.textField_2.setText(userName);
	}

	public int getAge() {
		String age = textField_3.getText();
		return Integer.parseInt(age);
	}

	public void setAge(String age) {
		this.textField_3.setText(age);
	}
	public String getpassword() {
		return textField_4.getText();
	}

	public void setPassword(String password) {
		this.textField_4.setText(password);
	}
	public Boolean updateInf() {
		try {
			System.out.println(getFamilyName());
			System.out.println(UserInfo.getInstance().getEmail());
			connectToDB();
			String sqlQuery = "update user set name=?, familyName=?, userName=?, age=?, password=?   where email=?";
			preparedStatement =  connect.prepareStatement(sqlQuery);
			preparedStatement.setString(1, getName());
			preparedStatement.setString(2, getFamilyName());
			preparedStatement.setString(3, getUserName());
			preparedStatement.setInt(4, getAge());
			preparedStatement.setString(5, getpassword());
			preparedStatement.setString(6, UserInfo.getInstance().getEmail());
			int r = preparedStatement.executeUpdate();
			return r == 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
	}	


	
	public UpdatePanel(final JFrame mainFrame) {
		
		JLabel lblName = new JLabel("name:");
		
		JLabel lblFamilyn = new JLabel("familyName:");
		
		JLabel lblUsername = new JLabel("userName:");
		
		JLabel lblAge = new JLabel("age:");
		
		JLabel lblPassword = new JLabel("password:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JButton btnValidate = new JButton("validate");
		btnValidate.addActionListener(new ActionListener() {
			
			private UserInfo user;
			public void actionPerformed(ActionEvent e) {
				if(updateInf()){
					user = UserInfo.getInstance();
					user.setEmail(getUserName());
					loginPanel = new LogginPanel(mainFrame);
					mainFrame.setContentPane(loginPanel);
					mainFrame.setVisible(true);
					revalidate();
					repaint();
				}
				
				
			}
		});
		
		JButton btnCancel = new JButton("cancel");
		
		JLabel lblChangeYourInformation = new JLabel("change your information");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(34)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblName)
								.addComponent(lblFamilyn)
								.addComponent(lblUsername)
								.addComponent(lblAge)
								.addComponent(lblPassword))
							.addGap(40)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(106)
							.addComponent(btnValidate)
							.addGap(49)
							.addComponent(btnCancel))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(199)
							.addComponent(lblChangeYourInformation)))
					.addContainerGap(193, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblChangeYourInformation)
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFamilyn)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAge)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnValidate)
						.addComponent(btnCancel))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
