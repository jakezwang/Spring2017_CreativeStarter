package neu.edu.entity;
// Generated Apr 26, 2017 9:36:03 PM by Hibernate Tools 5.2.1.Final

/**
 * Category generated by hbm2java
 */
public class Category implements java.io.Serializable {

	private String categoryName;
	private String categoryShow;

	public Category() {
	}

	public Category(String categoryName) {
		this.categoryName = categoryName;
	}

	public Category(String categoryName, String categoryShow) {
		this.categoryName = categoryName;
		this.categoryShow = categoryShow;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryShow() {
		return this.categoryShow;
	}

	public void setCategoryShow(String categoryShow) {
		this.categoryShow = categoryShow;
	}

}
