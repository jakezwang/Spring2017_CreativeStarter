package neu.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import neu.edu.controller.input.UserCreditCardBean;
import neu.edu.controller.input.UserProjectBean;
import neu.edu.dao.UserDAO;
import neu.edu.entity.Creditcard;
import neu.edu.entity.Project;
import neu.edu.entity.Users;

@Service
public class CCService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Transactional
	public List<UserCreditCardBean> getAllCC(Integer userId) {
		List<Creditcard> ccs = userDAO.getAllCC(userId);
		List<UserCreditCardBean> response = new ArrayList<>();
		for(Creditcard cc : ccs){
			UserCreditCardBean userCCBean = new UserCreditCardBean();
			userCCBean.setCardId(cc.getCardId());
			userCCBean.setCardNumber(cc.getCardNumber());
			userCCBean.setCardFullName(cc.getCardFullName());
			userCCBean.setCardType(cc.getCardType());
			userCCBean.setCardExpireDate(cc.getCardExpireDate());
			userCCBean.setCardSecurity(cc.getCardSecurity());
			userCCBean.setUserId(userId);
			response.add(userCCBean);
		}
		return response;
	}
	
	public boolean addCC(UserCreditCardBean userCCBean,Integer userId){
		Creditcard c = new Creditcard();
		c.setCardNumber(userCCBean.getCardNumber());
		c.setCardFullName(userCCBean.getCardFullName());
		c.setCardType(userCCBean.getCardType());
		c.setCardExpireDate(userCCBean.getCardExpireDate());
		c.setCardSecurity(userCCBean.getCardSecurity());
		boolean success = userDAO.addCC(c, userId);
		return success;
	}
	
	@Transactional
	public boolean deleteCC(Integer ccId) {
		return userDAO.deleteCC(ccId);
	}
	
	public boolean updateCC(UserCreditCardBean userCCBean,Integer ccId,Integer userId) {
		boolean deleteStatus = userDAO.deleteCC(ccId);
		
		Creditcard c = new Creditcard();
		c.setCardNumber(userCCBean.getCardNumber());
		c.setCardFullName(userCCBean.getCardFullName());
		c.setCardType(userCCBean.getCardType());
		c.setCardExpireDate(userCCBean.getCardExpireDate());
		c.setCardSecurity(userCCBean.getCardSecurity());
		boolean success = userDAO.addCC(c, userId);
		return success;
	}

	public List<UserCreditCardBean> getCCs() {
		List<Creditcard> ccs = userDAO.getCCs();
		List<UserCreditCardBean> response = new ArrayList<>();
		for(Creditcard cc : ccs){
			UserCreditCardBean userCCBean = new UserCreditCardBean();
			userCCBean.setCardId(cc.getCardId());
			userCCBean.setCardNumber(cc.getCardNumber());
			userCCBean.setCardFullName(cc.getCardFullName());
			userCCBean.setCardType(cc.getCardType());
			userCCBean.setCardExpireDate(cc.getCardExpireDate());
			userCCBean.setCardSecurity(cc.getCardSecurity());
			userCCBean.setUserId(cc.getUsers().getUserId());
			response.add(userCCBean);
		}
		return response;
	}


}
