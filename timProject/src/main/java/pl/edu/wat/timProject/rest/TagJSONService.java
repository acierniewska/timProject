package pl.edu.wat.timProject.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.edu.wat.timProject.dataModel.hibernate.Tag;
import pl.edu.wat.timProject.services.TagService;

@Component
@Path("/tag")
public class TagJSONService {

	@Autowired
	private TagService colourService;

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tag> getTagsInJSON() {
		List<Tag> colours = colourService.listAll();

		return colours;
	}

	public TagService getClothesService() {
		return colourService;
	}

	public void setTagService(TagService TagService) {
		this.colourService = colourService;
	}
}
