package pl.edu.wat.timProject.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.edu.wat.timProject.dataModel.hibernate.Colour;
import pl.edu.wat.timProject.services.ColourService;

@Component
@Path("/colour")
public class ColourJSONService {

	@Autowired
	private ColourService colourService;

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Colour> getColoursInJSON() {
		List<Colour> colours = colourService.listAll();

		return colours;
	}

	public ColourService getClothesService() {
		return colourService;
	}

	public void setColourService(ColourService ColourService) {
		this.colourService = ColourService;
	}
}
