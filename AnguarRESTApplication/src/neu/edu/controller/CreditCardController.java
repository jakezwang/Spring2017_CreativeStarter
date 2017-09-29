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

import neu.edu.service.CCService;
import neu.edu.controller.error.ResponseError;
import neu.edu.controller.input.UserCreditCardBean;
import neu.edu.controller.input.UserProjectBean;

@Controller
@Path("/cc")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CreditCardController {
	
	
	@Autowired
	private CCService ccService;
	
	@GET
	@Path("/{id}/get")
	public Response getAllCC(@PathParam("id") String id){
		List<UserCreditCardBean> userCCBeans =  ccService.getAllCC(new Integer(id));
		return  Response.ok().status(200).entity(userCCBeans).build();
	}
	
	@GET
	@Path("/getall")
	public Response getCCs(){
		List<UserCreditCardBean> userCCBeans =  ccService.getCCs();
		return  Response.ok().status(200).entity(userCCBeans).build();
	}
	
	@POST
	@Path("/{id}/add")
	public Response addCC(UserCreditCardBean userCCBean, @PathParam("id") String userId){
		boolean success = ccService.addCC(userCCBean, Integer.parseInt(userId));
		if (success == false) {
			ResponseError regResponseErr = new ResponseError();
			regResponseErr.setMessage("creditcard-add-failed");
			return Response.ok().status(422).entity(regResponseErr).build();	
		} else {
			List<UserCreditCardBean> userCCBeans =  ccService.getAllCC(new Integer(userId));
			return  Response.ok().status(200).entity(userCCBeans).build();
		}
	}
	
	@GET
	@Path("/{id}/{ccId}/delete")
	public Response deleteCC(@PathParam("ccId") String ccId, @PathParam("id") String userId){
		boolean success = ccService.deleteCC(Integer.parseInt(ccId));
		
		if (success == false) {
			ResponseError responseErr = new ResponseError();
			responseErr.setMessage("project-delete-failed");
			return Response.ok().status(422).entity(responseErr).build();	
		} else {
			List<UserCreditCardBean> userCCBeans =  ccService.getAllCC(new Integer(userId));
			return  Response.ok().status(200).entity(userCCBeans).build();
		}
	}
	
	@PUT
	@Path("/{id}/{ccId}/update")
	public Response updateCC(UserCreditCardBean userCCBean,@PathParam("ccId") String ccId,@PathParam("id") String userId){
		
		boolean success = ccService.updateCC(userCCBean,Integer.parseInt(ccId),Integer.parseInt(userId));
		
		if (success == false) {
			ResponseError responseErr = new ResponseError();
			responseErr.setMessage("project-update-failed");
			return Response.ok().status(422).entity(responseErr).build();	
		} else {
			List<UserCreditCardBean> userCCBeans =  ccService.getAllCC(new Integer(userId));
			return  Response.ok().status(200).entity(userCCBeans).build();
		}
	}
	

	
}
