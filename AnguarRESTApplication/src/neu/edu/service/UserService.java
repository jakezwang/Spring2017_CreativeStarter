package neu.edu.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import neu.edu.bean.UserSessionInfo;
import neu.edu.controller.input.ServiceBean;
import neu.edu.controller.input.UserAccountBean;
import neu.edu.controller.input.UserCreditCardBean;
import neu.edu.controller.input.UserProjectBean;
import neu.edu.controller.input.UserRegisterBean;
import neu.edu.dao.UserDAO;
import neu.edu.entity.Users;
import neu.edu.entity.Creditcard;
import neu.edu.entity.Project;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDao;
	
	
	//--------------------------User--------------------------
	
	public boolean updateUser(int id, UserAccountBean accountBean) {
		Users u = new Users();
		u.setUserId(id);
		u.setUserName(accountBean.getUserName());
		u.setFirstName(accountBean.getFirstName());
		u.setLastName(accountBean.getLastName());
		u.setPassword("pass");
		u.setEmail(accountBean.getEmail());
		u.setType(accountBean.getType());
		
		boolean success = userDao.updateUsers(u);
		return success;
	}
	
	public Integer validateUser(String username,String password){
		System.out.println("Service is called Called");
		Users users = userDao.validateUser(username,password);
		
		if (users == null) {
			System.out.println("User Not Found");
			return null;
		} else {
			System.out.println("User  Found");
			return users.getUserId();
		}
	}

	public UserSessionInfo fetchUserAccountDetails(Integer userId) {
		// TODO Auto-generated method stub
		UserSessionInfo userSessionInfo=null;
		
		Users users = userDao.fetchUserAccount(userId);
		
		if(users!=null){
			userSessionInfo = new UserSessionInfo(users.getUserId());
			userSessionInfo.setUserName(users.getUserName());
			userSessionInfo.setFirstName(users.getFirstName());
			userSessionInfo.setLastName(users.getLastName());
			userSessionInfo.setEmail(users.getEmail());
			userSessionInfo.setRole(users.getType());
		}
		
		for(Creditcard c  :  users.getCreditcards()){
			if (users.getCreditcards() != null){
				
			}
			UserCreditCardBean uccBean = new UserCreditCardBean();

			uccBean.setCardId(c.getCardId());
			uccBean.setCardNumber(c.getCardNumber());
			uccBean.setCardFullName(c.getCardFullName());
			uccBean.setCardExpireDate(c.getCardExpireDate());
			uccBean.setCardType(c.getCardType());
			uccBean.setCardSecurity(c.getCardSecurity());
			uccBean.setUserId(c.getUsers().getUserId());
			
			userSessionInfo.getUserCCBean().add(uccBean);
		}
		
		for(Project p  :  users.getProjects()){
			UserProjectBean upBean = new UserProjectBean();
			
			upBean.setProjectId(p.getProjectId());
			upBean.setProjectName(p.getProjectName());
			upBean.setProjectDesc(p.getProjectDesc());
			upBean.setAmountGoal(p.getAmountGoal());
			upBean.setDayStart(p.getDayStart());
			upBean.setDayEnd(p.getDayEnd());
			upBean.setUserId(p.getUsers().getUserId());
			
			userSessionInfo.getUserProjectBean().add(upBean);
//			System.out.println("Current project Username is: " + p.getUsers().getUserName());
		}
		return userSessionInfo;
	}
	
	public Users registerUser(UserRegisterBean rB){
		Users ua = new Users();
		
		ua.setUserName(rB.getUserName());
		ua.setFirstName(rB.getFirstName());
		ua.setLastName(rB.getLastName());
		ua.setPassword(rB.getPassword());
		ua.setType(rB.getType());
		ua.setEmail(rB.getEmail());
		
		Users user = userDao.createUser(ua);
		if (user == null) {
			System.out.println("User Registration Failed");
			return null;
		} else {
			System.out.println("User Registration Succeed");
			return user;
		}
	}

	//----------------------Service Entity---------------------------
	
	public List<ServiceBean> getAllService(Integer projectId, Integer ccId) {
		System.out.println("Enter userService");
		List<neu.edu.entity.Service> ss = userDao.getAllService(projectId,ccId);
		
		List<ServiceBean> response = new ArrayList<>();
		for(neu.edu.entity.Service s : ss){
			ServiceBean sBean = new ServiceBean();
			sBean.setPaymentId(s.getPaymentId());
			sBean.setPaymentAmount(s.getPaymentAmount());
			sBean.setPaymentDate(s.getPaymentDate());
			sBean.setPaymentStatus(s.getPaymentStatus());
			response.add(sBean);
		}
		return response;
	}
	
	public boolean addService(ServiceBean sBean,Integer projectId, Integer ccId) {
		neu.edu.entity.Service s = new neu.edu.entity.Service();
		s.setPaymentAmount(sBean.getPaymentAmount());
		s.setPaymentDate(sBean.getPaymentDate());
		s.setPaymentStatus(sBean.getPaymentStatus());
		
		boolean success = userDao.addService(s,projectId, ccId);
		return success;
	}

	public boolean deleteService(int paymentId) {
		return userDao.deleteService(paymentId);
	}
	
	//-------------------------------Get Services by CC or by Project------------------------

	public List<ServiceBean> getByProject(int projectId) {
		List<neu.edu.entity.Service> ss = userDao.getByProject(projectId);
		List<ServiceBean> response = new ArrayList<>();
		for(neu.edu.entity.Service s : ss){
			ServiceBean sBean = new ServiceBean();
			sBean.setPaymentId(s.getPaymentId());
			sBean.setPaymentAmount(s.getPaymentAmount());
			sBean.setPaymentDate(s.getPaymentDate());
			sBean.setPaymentStatus(s.getPaymentStatus());
			sBean.setCcID(s.getCreditcard().getCardId());
			response.add(sBean);
		}
		return response;
	}

	public List<ServiceBean> getByCC(int ccId) {
		List<neu.edu.entity.Service> ss = userDao.getByCC(ccId);
		List<ServiceBean> response = new ArrayList<>();
		for(neu.edu.entity.Service s : ss){
			ServiceBean sBean = new ServiceBean();
			sBean.setPaymentId(s.getPaymentId());
			sBean.setPaymentAmount(s.getPaymentAmount());
			sBean.setPaymentDate(s.getPaymentDate());
			sBean.setPaymentStatus(s.getPaymentStatus());
			sBean.setProjectId(s.getProject().getProjectId());
			response.add(sBean);
		}
		return response;
	}
	
	//=-

	
	
	
	

	
	

}
