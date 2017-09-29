package neu.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import neu.edu.controller.input.CategoryBean;
import neu.edu.controller.input.UserProjectBean;
import neu.edu.dao.UserDAO;
import neu.edu.entity.Category;
import neu.edu.entity.Project;
import neu.edu.entity.Users;

@Service
public class ProjectService {
	
	@Autowired
	private UserDAO userDAO;
	
	
	public boolean addProject(UserProjectBean userProjectBean,Integer userId){
		Project p = new Project();
		p.setProjectName(userProjectBean.getProjectName());
		p.setProjectDesc(userProjectBean.getProjectDesc());
		p.setAmountGoal(userProjectBean.getAmountGoal());
		p.setDayStart(userProjectBean.getDayStart());
		p.setDayEnd(userProjectBean.getDayEnd());
		p.setCategory(userProjectBean.getCategory());
		boolean success = userDAO.addProject(p, userId);

		return success;
	}
	
	
	public boolean updateProject(UserProjectBean userProjectBean,Integer projectId,Integer userId) {
		
		Project p = new Project();
		p.setProjectId(projectId);
		p.setProjectName(userProjectBean.getProjectName());
		p.setProjectDesc(userProjectBean.getProjectDesc());
		p.setAmountGoal(userProjectBean.getAmountGoal());
		p.setDayStart(userProjectBean.getDayStart());
		p.setDayEnd(userProjectBean.getDayEnd());
		p.setCategory(userProjectBean.getCategory());
		
		boolean success = userDAO.updateProject(p, userId);
		return success;
	}
	
	@Transactional
	public boolean deleteProject(Integer projectId) {
		return userDAO.deleteProject(projectId);
	}
	
	@Transactional
	public List<UserProjectBean> getAllProject(Integer userId) {
		List<Project> projects = userDAO.getAllProjects(userId);
		
		List<UserProjectBean> response = new ArrayList<>();
		for(Project p : projects){
			UserProjectBean userProjectBean = new UserProjectBean();
			userProjectBean.setProjectName(p.getProjectName());
			userProjectBean.setProjectDesc(p.getProjectDesc());
			userProjectBean.setAmountGoal(p.getAmountGoal());
			userProjectBean.setDayStart(p.getDayStart());
			userProjectBean.setDayEnd(p.getDayEnd());
			userProjectBean.setUserId(p.getUsers().getUserId());
			userProjectBean.setCategory(p.getCategory());
			
			userProjectBean.setProjectId(p.getProjectId());
			response.add(userProjectBean);
		}
		return response;
	}
	
	//--------------------------Category Service--------------------
	
	@Transactional
	public boolean deleteCat(String catname) {
		return userDAO.deleteCat(catname);
	}
	
	@Transactional
	public boolean addCat2(String catname) {
		return userDAO.add2Cat(catname);
	}
	
	@Transactional
	public List<CategoryBean> getAllCat() {
		List<Category> cats = userDAO.getAllCat();
		List<CategoryBean> response = new ArrayList<>();
		for(Category cat : cats){
			CategoryBean catBean = new CategoryBean();	
			catBean.setCategoryName(cat.getCategoryName());
			catBean.setCategoryShow(cat.getCategoryShow());	
			response.add(catBean);
		}
		return response;
	}
	
	public Category addCat(CategoryBean catBean){
		Category cat = new Category();
		cat.setCategoryName(catBean.getCategoryName());
		cat.setCategoryShow(catBean.getCategoryShow());
		
		Category catRet = userDAO.addCat(cat);
		return catRet;
	}


	//-----------------------Get all projects for view (Admin/Customer)------------------------
	
	@Transactional
	public List<UserProjectBean> getProjects() {
		List<Project> projects = userDAO.getProjects();
		
		List<UserProjectBean> response = new ArrayList<>();
		for(Project p : projects){
			UserProjectBean userProjectBean = new UserProjectBean();
			userProjectBean.setProjectName(p.getProjectName());
			userProjectBean.setProjectDesc(p.getProjectDesc());
			userProjectBean.setAmountGoal(p.getAmountGoal());
			userProjectBean.setDayStart(p.getDayStart());
			userProjectBean.setDayEnd(p.getDayEnd());
			userProjectBean.setUserId(p.getUsers().getUserId());
			userProjectBean.setCategory(p.getCategory());
			
			userProjectBean.setProjectId(p.getProjectId());
			response.add(userProjectBean);
		}
		return response;
	}


	
	
	
}
