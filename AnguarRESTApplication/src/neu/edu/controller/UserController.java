package neu.edu.controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import neu.edu.bean.UserSessionInfo;
import neu.edu.controller.error.ResponseError;
import neu.edu.controller.input.ServiceBean;
import neu.edu.controller.input.UserAccountBean;
import neu.edu.controller.input.UserLoginBean;
import neu.edu.controller.input.UserProjectBean;
import neu.edu.controller.input.UserRegisterBean;
import neu.edu.entity.Users;
import neu.edu.service.UserService;

@Controller
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

	@Autowired
	private UserService userService;
	
	//--------------------------------User Entity-----------------------
	@POST
	@Path("/auth")
	public Response validateUser(UserLoginBean loginBean) {
		Integer userId = userService.validateUser(loginBean.getUsername(), loginBean.getPassword());
		if (userId == null) {
			ResponseError authResponseErr = new ResponseError();
			authResponseErr.setMessage("user-not-found");
			return Response.ok().status(422).entity(authResponseErr).build();
		} else {
			UserSessionInfo userSessionInfo = userService.fetchUserAccountDetails(userId);
			return Response.ok().status(200).entity(userSessionInfo).build();
		}
	}
	
	@GET
	@Path("/get/{id}")
	public Response getUser(@PathParam("id") String id){
		UserSessionInfo userSessionInfo = userService.fetchUserAccountDetails(Integer.parseInt(id));
		return Response.ok().status(200).entity(userSessionInfo).build();
	}

	private String generateKey() {
		Random rand = new Random();
		int n = rand.nextInt(50) + 1;
		return n + (new Date().toString());
	}
	
	@POST
	@Path("/update/{id}")
	public Response updateUser(@PathParam("id") String id, UserAccountBean accountBean) {
		boolean success = userService.updateUser(Integer.parseInt(id),accountBean);
		
		if (success == false) {
			ResponseError responseErr = new ResponseError();
			responseErr.setMessage("user-update-failed");
			return Response.ok().status(422).entity(responseErr).build();	
		} else {
			UserSessionInfo userSessionInfo = userService.fetchUserAccountDetails(Integer.parseInt(id));
			return Response.ok().status(200).entity(userSessionInfo).build();
		}
	}
	
//	@POST
//	@Path("/update/firstname/{id}/{fname}")
//	public void updateUserFName(@PathParam("id") String id,@PathParam("fname") String fname) {
//	}
	
	@POST
	@Path("/register")
	public Response registerUser(UserRegisterBean rBean) {
		Users users = userService.registerUser(rBean);
		if (users == null) {
			ResponseError authResponseErr = new ResponseError();
			authResponseErr.setMessage("registration-failed");
			return Response.ok().status(422).entity(authResponseErr).build();
		} else {
			UserSessionInfo userSessionInfo = userService.fetchUserAccountDetails(users.getUserId());
			return Response.ok().status(200).entity(userSessionInfo).build();
		}
	}
	
	//---------------------------------Service Entity----------------
	@GET
	@Path("/service/get/{projectId}/{ccId}")
	public Response getAllProject(@PathParam("projectId") String pId, @PathParam("ccId") String cId){
		List<ServiceBean> sBeans =  userService.getAllService(Integer.parseInt(pId),Integer.parseInt(cId));
		return  Response.ok().status(200).entity(sBeans).build();
	}
	
	@POST
	@Path("/service/add/{projectId}/{ccId}")
	public Response addService(ServiceBean sBean, @PathParam("projectId") String projectId, @PathParam("ccId") String ccId){
		boolean success = userService.addService(sBean, Integer.parseInt(projectId),Integer.parseInt(ccId));
		if (success == false) {
			ResponseError responseErr = new ResponseError();
			responseErr.setMessage("service-add-failed");
			return Response.ok().status(422).entity(responseErr).build();	
		} else {
			List<ServiceBean> sBeans =  userService.getAllService(Integer.parseInt(projectId),Integer.parseInt(ccId));
			return  Response.ok().status(200).entity(sBeans).build();
		}
	}
	
	@GET
	@Path("/service/delete/{paymentId}")
	public Response deleteService(@PathParam("paymentId") String paymentId){
		boolean success = userService.deleteService(Integer.parseInt(paymentId));
		
		if (success == false) {
			ResponseError responseErr = new ResponseError();
			responseErr.setMessage("service-delete-failed");
			return Response.ok().status(422).entity(responseErr).build();	
		} else {
			return  Response.ok().status(200).entity("Delete Succeed").build();
		}
	}
	
	//------------------Get Service by Project or by CC------------------
	@GET
	@Path("/service/getbyproject/{projectId}")
	public Response getByProject(@PathParam("projectId") String pId){
		List<ServiceBean> sBeans =  userService.getByProject(Integer.parseInt(pId));
		return  Response.ok().status(200).entity(sBeans).build();
	}
	
	@GET
	@Path("/service/getbycc/{ccId}")
	public Response getByCC(@PathParam("ccId") String cId){
		List<ServiceBean> sBeans =  userService.getByCC(Integer.parseInt(cId));
		return  Response.ok().status(200).entity(sBeans).build();
	}
	
	
	
	
	
}
