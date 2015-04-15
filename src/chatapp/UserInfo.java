package chatapp;

public class UserInfo {
	// Make userInfo a sengleton object
	private static UserInfo uniqIstance;
	private String email;
	private String userName;
	private Integer age;
	
	
	private UserInfo() {
	}


	public static synchronized UserInfo getInstance() {
		if(uniqIstance == null) {
			uniqIstance = new UserInfo();
		}
		return uniqIstance;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	
}
