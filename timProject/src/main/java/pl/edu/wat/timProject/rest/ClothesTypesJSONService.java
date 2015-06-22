package pl.edu.wat.timProject.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.edu.wat.timProject.dataModel.hibernate.ClothesType;
import pl.edu.wat.timProject.services.ClothesTypeService;

@Component
@Path("/clothesTypes")
public class ClothesTypesJSONService {

	@Autowired
	private ClothesTypeService clothesTypeService;

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClothesType> getClothesTypesInJSON() {
		List<ClothesType> clothesTypes = clothesTypeService.listAll();

		return clothesTypes;
	}

	public ClothesTypeService getClothesService() {
		return clothesTypeService;
	}

	public void setClothesService(ClothesTypeService clothesTypeService) {
		this.clothesTypeService = clothesTypeService;
	}
}
