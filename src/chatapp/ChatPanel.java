package chatapp;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;

public class ChatPanel extends JPanel {
	private JTextField textField;
	private LogginPanel loginPanel;
	private UpdatePanel updatePanel;
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
			System.out.println("hello my database");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Boolean updateState() {
		try {
			connectToDB();
			String sqlquery = "update user set state='0' where email=?";
			preparedStatement =  connect.prepareStatement(sqlquery);
			preparedStatement.setString(1, UserInfo.getInstance().getEmail());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
	}	

	public ChatPanel(final JFrame mainFrame) {
		
		JList<AbstractListModel> list = new JList();
		list.setForeground(SystemColor.textHighlight);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Med", "Mohja", "Melek", "Marwa", "Manel"};
			public int getSize() {
				return values.length;
			}
			
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JLabel lblListOfFreinds = new JLabel(" List of freinds:");
		
		JLabel lblNewLabel = new JLabel("welcome to chat screen :) ");
		
		JTextArea textArea = new JTextArea();
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnEnvoyer = new JButton("envoyer");
		
		JButton btnLogIut = new JButton("log out");
		btnLogIut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateState();
				loginPanel = new LogginPanel(mainFrame);
				mainFrame.setContentPane(loginPanel);
				mainFrame.setVisible(true);
				revalidate();
				repaint();
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Mohja\\Desktop\\application chat\\chat-forum-parler-utilisateur-icone-7966-96.png"));
		
		JButton btnUpDate = new JButton("up date");
		btnUpDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnUpdateInf = new JButton("update inf");
		btnUpdateInf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel = new UpdatePanel(mainFrame);
				mainFrame.setContentPane(updatePanel);
				mainFrame.setVisible(true);
				revalidate();
				repaint();
			}
		});
		
	
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(list, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
									.addGap(13))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnUpDate)
									.addPreferredGap(ComponentPlacement.UNRELATED)))
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addGap(236)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
										.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(23)
											.addComponent(btnEnvoyer))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(18)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(btnUpdateInf)
												.addComponent(btnLogIut))))
									.addGap(24))))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblListOfFreinds, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(601, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
							.addGap(6))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblListOfFreinds, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(7)
									.addComponent(btnLogIut)
									.addGap(18)
									.addComponent(btnUpdateInf))
								.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(32)
									.addComponent(btnEnvoyer))))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnUpDate)))
					.addGap(71))
		);
		setLayout(groupLayout);

	}
}
