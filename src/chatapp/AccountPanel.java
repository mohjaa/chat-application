package chatapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AccountPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LogginPanel loginPanel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private JPasswordField passwordField_1;

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

	public String getSex() {
		return textField.getText();
	}

	public void setSex(String sex) {
		this.textField.setText(sex);
	}

	public String getEmail() {
		return textField_4.getText();
	}

	public void setEmail(String email) {
		this.textField_4.setText(email);
	}

	public String getPassword() {
		return passwordField_1.getText();
	}

	public void setPassword(String password) {
		this.textField_4.setText(password);
	}
	public Boolean insertUser(){
		try{
			String sqlQuery="insert into user(name,familyName,userName,age,sex,email,password, state)value(?,?,?,?,?,?,?,?)";
			preparedStatement = connect.prepareStatement(sqlQuery);
			preparedStatement.setString(1, getName());
			preparedStatement.setString(2, getFamilyName());
			preparedStatement.setString(3, getUserName());
			preparedStatement.setInt(4, getAge());
			preparedStatement.setString(5, getSex());
			preparedStatement.setString(6, getEmail());
			preparedStatement.setString(7, getPassword());
			preparedStatement.setInt(8, 0);
			preparedStatement.executeUpdate();
			return true;
			}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
	}
	

	public AccountPanel(final JFrame mainFrame) {
		connectToDB();
		

		JLabel lblNewLabel = new JLabel("create new account");

		JLabel lblName = new JLabel("name:");

		JLabel lblFamilyName = new JLabel("familyName:");

		JLabel lblPseudoName = new JLabel("userName:");

		JLabel lblAge = new JLabel("age:");

		JLabel lblSexe = new JLabel("sex:");

		JLabel lblEmail = new JLabel("email:");

		textField = new JTextField();
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Male",
				"Female" }));

		textField_4 = new JTextField();
		textField_4.setColumns(10);

		JButton btnNewButton = new JButton("validate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insertUser();
				loginPanel = new LogginPanel(mainFrame);
				mainFrame.setContentPane(loginPanel);
				mainFrame.setVisible(true);
				revalidate();
				repaint();

			}
		});

		JButton btnNewButton_1 = new JButton("cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginPanel = new LogginPanel(mainFrame);
				mainFrame.setContentPane(loginPanel);
				mainFrame.setVisible(true);
				revalidate();
				repaint();
			}
		});

		JLabel lblPassword = new JLabel("password:");

		passwordField_1 = new JPasswordField();
		
		JLabel lblNewLabel_1 = new JLabel("");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(151)
							.addComponent(lblNewLabel)
							.addGap(179)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblName)
								.addComponent(lblFamilyName)
								.addComponent(lblPseudoName)
								.addComponent(lblAge)
								.addComponent(lblSexe)
								.addComponent(lblEmail)
								.addComponent(lblPassword))
							.addGap(43)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_4, 144, 144, Short.MAX_VALUE)
								.addComponent(passwordField_1, 144, 144, Short.MAX_VALUE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_2, 144, 144, Short.MAX_VALUE)
								.addComponent(textField_1, 144, 144, Short.MAX_VALUE)
								.addComponent(textField, 144, 144, Short.MAX_VALUE)
								.addComponent(btnNewButton)
								.addComponent(textField_3))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_1)))
					.addContainerGap(40, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(76)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblName)
									.addGap(27)
									.addComponent(lblFamilyName)
									.addGap(23)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPseudoName)
										.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(38)
							.addComponent(lblSexe)
							.addGap(18)
							.addComponent(lblEmail))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAge)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton)
								.addComponent(btnNewButton_1)))
						.addComponent(lblPassword))
					.addContainerGap(121, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
