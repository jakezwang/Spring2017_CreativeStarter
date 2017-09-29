package neu.edu.controller.input;

public class UserProjectBean {
	
	private Integer projectId;
	private String projectName;
	private String projectDesc;
	private String amountGoal;
	private String dayStart;
	private String dayEnd;
	private String category;
	private Integer userId;
	
	public UserProjectBean() {
		// TODO Auto-generated constructor stub
	}

	
	
	public Integer getProjectId() {
		return projectId;
	}



	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public String getProjectName() {
		return projectName;
	}



	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}



	public String getProjectDesc() {
		return projectDesc;
	}



	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}



	public String getAmountGoal() {
		return amountGoal;
	}

	public void setAmountGoal(String amountGoal) {
		this.amountGoal = amountGoal;
	}

	public String getDayStart() {
		return dayStart;
	}

	public void setDayStart(String dayStart) {
		this.dayStart = dayStart;
	}

	public String getDayEnd() {
		return dayEnd;
	}

	public void setDayEnd(String dayEnd) {
		this.dayEnd = dayEnd;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	
	
	
	
	

}
