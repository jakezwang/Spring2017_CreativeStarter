package neu.edu.bean;

import java.util.ArrayList;
import java.util.List;

import neu.edu.controller.input.UserAccountBean;
import neu.edu.controller.input.UserCreditCardBean;
import neu.edu.controller.input.UserProjectBean;

public class UserSessionInfo {
	
	private Integer userId;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String role;
	
	private List<UserCreditCardBean> userCCBean;
	private List<UserProjectBean> userProjectBean;
//	
	public UserSessionInfo(Integer userId) {
		this.userId = userId;
		userCCBean = new ArrayList<>();
		userProjectBean = new ArrayList<>();
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


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public List<UserProjectBean> getUserProjectBean() {
		return userProjectBean;
	}

	public void setUserProjectBean(List<UserProjectBean> userProjectBean) {
		this.userProjectBean = userProjectBean;
	}


	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<UserCreditCardBean> getUserCCBean() {
		return userCCBean;
	}

	public void setUserCCBean(List<UserCreditCardBean> userCCBean) {
		this.userCCBean = userCCBean;
	}
	
	
	
	

}
