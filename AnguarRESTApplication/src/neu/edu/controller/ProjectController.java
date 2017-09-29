package neu.edu.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import neu.edu.service.ProjectService;
import neu.edu.bean.UserSessionInfo;
import neu.edu.controller.error.ResponseError;
import neu.edu.controller.input.CategoryBean;
import neu.edu.controller.input.UserProjectBean;
import neu.edu.controller.input.UserRegisterBean;
import neu.edu.entity.Category;
import neu.edu.entity.Users;

@Controller
@Path("/project")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ProjectController {
	
	
	@Autowired
	private ProjectService projectService;
	
	@GET
	@Path("/{id}/get")
	public Response getAllProject(@PathParam("id") String id){
		List<UserProjectBean> userProjectBeans =  projectService.getAllProject(new Integer(id));
		return  Response.ok().status(200).entity(userProjectBeans).build();
	}
	
	@GET
	@Path("/getall")
	public Response getAllProject(){
		List<UserProjectBean> userProjectBeans =  projectService.getProjects();
		return  Response.ok().status(200).entity(userProjectBeans).build();
	}
	
	@POST
	@Path("/{id}/add")
	public Response addProject(UserProjectBean userProjectBean, @PathParam("id") String userId){
		boolean success = projectService.addProject(userProjectBean, Integer.parseInt(userId));
		if (success == false) {
			ResponseError regResponseErr = new ResponseError();
			regResponseErr.setMessage("project-add-failed");
			return Response.ok().status(422).entity(regResponseErr).build();	
		} else {
			List<UserProjectBean> userProjectBeans =  projectService.getAllProject(new Integer(userId));
			return  Response.ok().status(200).entity(userProjectBeans).build();
		}
	}
	
	@GET
	@Path("/{id}/{projectId}/delete")
	public Response deleteProject(@PathParam("projectId") String projectId, @PathParam("id") String userId){
		boolean success = projectService.deleteProject(Integer.parseInt(projectId));
		
		if (success == false) {
			ResponseError responseErr = new ResponseError();
			responseErr.setMessage("project-delete-failed");
			return Response.ok().status(422).entity(responseErr).build();	
		} else {
			List<UserProjectBean> userProjectBeans =  projectService.getAllProject(new Integer(userId));
			return  Response.ok().status(200).entity(userProjectBeans).build();
		}
	}
	
	@PUT
	@Path("/{id}/{projectId}/update")
	public Response updateProject(UserProjectBean userProjectBean,@PathParam("projectId") String projectId,@PathParam("id") String userId){
		
		boolean success = projectService.updateProject(userProjectBean,Integer.parseInt(projectId),Integer.parseInt(userId));
		
		if (success == false) {
			ResponseError responseErr = new ResponseError();
			responseErr.setMessage("project-update-failed");
			return Response.ok().status(422).entity(responseErr).build();	
		} else {
			List<UserProjectBean> userProjectBeans =  projectService.getAllProject(new Integer(userId));
			return  Response.ok().status(200).entity(userProjectBeans).build();
		}
	}
	
	//--------------------Category Entity-------------------------------
	
	@GET
	@Path("/deletecat/{catname}")
	public Response deleteCat(@PathParam("catname") String catname){
		boolean success = projectService.deleteCat(catname);
		
		if (success == false) {
			ResponseError responseErr = new ResponseError();
			responseErr.setMessage("category-delete-failed");
			return Response.ok().status(422).entity(responseErr).build();	
		} else {
			List<CategoryBean> catBeans =  projectService.getAllCat();
			return  Response.ok().status(200).entity(catBeans).build();
		}
	}
	
	@GET
	@Path("/addcat2/{catname}")
	public Response addCat2(@PathParam("catname") String catname){
		boolean success = projectService.addCat2(catname);
		if (success == false) {
			ResponseError responseErr = new ResponseError();
			responseErr.setMessage("category-delete-failed");
			return Response.ok().status(422).entity(responseErr).build();	
		} else {
			List<CategoryBean> catBeans =  projectService.getAllCat();
			return  Response.ok().status(200).entity(catBeans).build();
		}
	}
	
	@POST
	@Path("/addcat")
	public Response addCat(CategoryBean catBean) {
		Category cat = projectService.addCat(catBean);
		if (cat == null) {
			ResponseError responseErr = new ResponseError();
			responseErr.setMessage("category-add-failed");
			return Response.ok().status(422).entity(responseErr).build();
		} else {
			List<CategoryBean> catBeans =  projectService.getAllCat();
			return Response.ok().status(200).entity(catBeans).build();
		}
	}
	
	@GET
	@Path("/getcat/")
	public Response getCat(){
		List<CategoryBean> catBeans =  projectService.getAllCat();
		return  Response.ok().status(200).entity(catBeans).build();
	}
	

	
}
