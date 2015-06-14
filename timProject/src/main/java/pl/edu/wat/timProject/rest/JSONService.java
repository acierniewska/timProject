package pl.edu.wat.timProject.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.edu.wat.timProject.dataModel.hibernate.Clothes;
import pl.edu.wat.timProject.services.ClothesService;

@Component
@Path("/json/clothes")
public class JSONService {

	@Autowired
	private ClothesService clothesService;

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Clothes> getClothesInJSON() {
		List<Clothes> clothes = clothesService.listAll();
		/*
		 * for (Clothes c : clothes) { for (Tag t : c.getClothesTags()) {
		 * t.getClothesTags().clear(); } }
		 */
		return clothes;
	}

	public ClothesService getClothesService() {
		return clothesService;
	}

	public void setClothesService(ClothesService clothesService) {
		this.clothesService = clothesService;
	}
}
