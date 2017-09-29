package neu.edu.controller.input;


public class ServiceBean {
	
	private Integer paymentId;
	private String paymentAmount;
	private String paymentDate;
	private String paymentStatus;
	private Integer projectId;
	private Integer ccID;
	
	public ServiceBean() {
		// TODO Auto-generated constructor stub
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getCcID() {
		return ccID;
	}

	public void setCcID(Integer ccID) {
		this.ccID = ccID;
	}
	
	

}
