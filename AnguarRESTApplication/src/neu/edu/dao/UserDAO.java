package neu.edu.dao;

import java.util.List;

import org.apache.catalina.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import neu.edu.controller.input.UserProjectBean;
import neu.edu.entity.Category;
import neu.edu.entity.Creditcard;
import neu.edu.entity.Project;
//import neu.edu.bean.UserProjectBean;
//import neu.edu.bean.UserRegistrationBean;
//import neu.edu.entity.UserProject;
//import neu.edu.entity.UserProjectId;
import neu.edu.entity.Users;

@Repository
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	
	public boolean updateUsers(Users u) {
		Session session = sessionFactory.openSession();	
		session.saveOrUpdate(u);
		session.flush();
		return true;
	}
	
	public Users validateUser(String username, String password) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Users where userName=:userName and password=:password ");
		query.setString("userName", username);
		query.setString("password", password);
		Users users = (Users) query.uniqueResult();
		return users;
	}
	
	@Transactional
	public Users createUser(Users users) {
		Session session = sessionFactory.openSession();
		session.save(users);
		return users;
	}

	public Users fetchUserAccount(Integer userId) {
		Session session = sessionFactory.openSession();
		return session.load(Users.class, userId);
	}

//-----------------------------Project----------------------------
	@Transactional
	public boolean addProject(Project project,Integer userId) {
		Session session = sessionFactory.openSession();
		Users users = session.load(Users.class, userId);
		project.setUsers(users);
		session.save(project);
		session.flush();
		return true;
	}

	
	@Transactional
	public boolean updateProject(Project project, Integer userId) {
		Session session = sessionFactory.openSession();	
		Users users = session.load(Users.class, userId);
		project.setUsers(users);
		session.saveOrUpdate(project);
		session.flush();
		return true;
	}
	
	@Transactional
	public boolean deleteProject(Integer projectId) {
		Session session = sessionFactory.openSession();
		Project project = new Project();
		project.setProjectId(projectId);
		try {
			session.delete(project);
			session.flush();
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}
	
	@Transactional
	public List<Project> getAllProjects(Integer userId) {
		Session session = sessionFactory.openSession();
		return session.createQuery(" from Project where userId = :userid")
			    .setParameter("userid", userId)
			    .list();
	}
	
//-----------------------------CreditCard----------------------------
	@Transactional
	public List<Creditcard> getAllCC(Integer userId) {
		Session session = sessionFactory.openSession();
		return session.createQuery(" from Creditcard where userId = :userid")
			    .setParameter("userid", userId)
			    .list();
	}
	
	@Transactional
	public boolean addCC(Creditcard cc,Integer userId) {
		Session session = sessionFactory.openSession();
		Users users = session.load(Users.class, userId);
		cc.setUsers(users);
		session.save(cc);
		session.flush();
		return true;
	}
	
	@Transactional
	public boolean deleteCC(Integer ccId) {
		Session session = sessionFactory.openSession();
		Creditcard c = new Creditcard();
		c.setCardId(ccId);
		try {
			session.delete(c);
			session.flush();
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}
	
	@Transactional
	public boolean updateCC(Creditcard cc,Integer userId) {
		Session session = sessionFactory.openSession();	
		Users users = session.load(Users.class, userId);
		cc.setUsers(users);
		session.saveOrUpdate(cc);
		session.flush();
		return true;
	}
	
//-----------------------------Category----------------------------
	
	@Transactional
	public List<Category> getAllCat() {
		Session session = sessionFactory.openSession();
		return session.createQuery(" from Category where categoryShow = 1").list();
	}
	

	public Category addCat(Category cat) {
		Session session = sessionFactory.openSession();
		session.save(cat);
		session.flush();
		return cat;
	}
	
	@Transactional
	public boolean deleteCat(String catname) {
		Session session = sessionFactory.openSession();
		Category c = new Category();
		c.setCategoryName(catname);
		try {
			session.delete(c);
			session.flush();
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}
	
	@Transactional
	public boolean add2Cat(String catname) {
		Session session = sessionFactory.openSession();
		Category c = new Category();
		c.setCategoryName(catname);
		c.setCategoryShow("1");
		session.save(c);
		session.flush();
		return true;
	}
	
	
//-----------------------------Service----------------------------
	
	public List<neu.edu.entity.Service> getAllService(int projectId, int ccId) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Service where project.projectId= :pId and creditcard.cardId= :cId");
		query.setParameter("pId",projectId);
		query.setParameter("cId",ccId);
		List results = query.list();
		session.flush();
		return results;
	}
	
	public boolean addService(neu.edu.entity.Service ser,Integer projectId, Integer ccId ) {
		Session session = sessionFactory.openSession();
		Project project = session.load(Project.class, projectId);
		Creditcard cc = session.load(Creditcard.class, ccId);
		ser.setProject(project);
		ser.setCreditcard(cc);

		session.save(ser);
		session.flush();
		return true;
	}
	
	public boolean deleteService(Integer paymentId) {
		Session session = sessionFactory.openSession();
		neu.edu.entity.Service payment = new neu.edu.entity.Service();
		payment.setPaymentId(paymentId);
		try {
			session.delete(payment);
			session.flush();
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}

	//--------------------Get Services by Project or by Credit Card--------------------------
	
	public List<neu.edu.entity.Service> getByProject(int projectId) {
		Session session = sessionFactory.openSession();
		return session.createQuery(" from Service where projectId = :pId")
			    .setParameter("pId", projectId)
			    .list();
	}

	public List<neu.edu.entity.Service> getByCC(int ccId) {
		Session session = sessionFactory.openSession();
		return session.createQuery(" from Service where creditcard.cardId = :cId")
			    .setParameter("cId", ccId)
			    .list();
	}

	//-------------------Get All Projects for Admin and Customer---------------------------
	
	@Transactional
	public List<Project> getProjects() {
		Session session = sessionFactory.openSession();
		return session.createQuery(" from Project").list();
	}

	public List<Creditcard> getCCs() {
		Session session = sessionFactory.openSession();
		return session.createQuery(" from Creditcard").list();
	}

	
	
	

}
