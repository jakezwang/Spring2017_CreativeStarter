package neu.edu.controller.input;

public class UserCreditCardBean {

	private Integer cardId;
	private String cardNumber;
	private String cardFullName;
	private String cardType;
	private String cardExpireDate;
	private String cardSecurity;
	private Integer userId;
	
	public UserCreditCardBean() {
		// TODO Auto-generated constructor stub
	}
	
	

	public UserCreditCardBean(Integer cardId, String cardNumber, String cardFullName, String cardType,
			String cardExpireDate, String cardSecurity) {
		super();
		this.cardId = cardId;
		this.cardNumber = cardNumber;
		this.cardFullName = cardFullName;
		this.cardType = cardType;
		this.cardExpireDate = cardExpireDate;
		this.cardSecurity = cardSecurity;
	}



	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardFullName() {
		return cardFullName;
	}

	public void setCardFullName(String cardFullName) {
		this.cardFullName = cardFullName;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardExpireDate() {
		return cardExpireDate;
	}

	public void setCardExpireDate(String cardExpireDate) {
		this.cardExpireDate = cardExpireDate;
	}

	public String getCardSecurity() {
		return cardSecurity;
	}

	public void setCardSecurity(String cardSecurity) {
		this.cardSecurity = cardSecurity;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	

}
