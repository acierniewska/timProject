package pl.edu.wat.timProject.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.edu.wat.timProject.dataModel.hibernate.Clothes;
import pl.edu.wat.timProject.dataModel.hibernate.ClothesType;
import pl.edu.wat.timProject.dataModel.hibernate.Colour;
import pl.edu.wat.timProject.dataModel.hibernate.Event;
import pl.edu.wat.timProject.dataModel.hibernate.Matched;
import pl.edu.wat.timProject.dataModel.hibernate.Tag;
import pl.edu.wat.timProject.services.ClothesService;
import pl.edu.wat.timProject.services.ClothesTypeService;
import pl.edu.wat.timProject.services.ColourService;
import pl.edu.wat.timProject.services.EventService;
import pl.edu.wat.timProject.services.TagService;

@Component
@Path("/clothes")
public class JSONService {
	@Autowired
	private ClothesService clothesService;
	@Autowired
	private TagService tagService;
	@Autowired
	private ClothesTypeService clothesTypeService;
	@Autowired
	private ColourService colourService;
	@Autowired
	private EventService eventService;
	@Autowired
	private ClothesMatcher clothesMatcher;

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Clothes> getClothesInJSON() {
		List<Clothes> clothes = clothesService.listAll();

		return clothes;
	}

	@GET
	@Path("/match")
	@Produces(MediaType.APPLICATION_JSON)
	public Matched getMatchInJSON(
			@QueryParam(value = "eventName") String eventName,
			@QueryParam(value = "temp") String temp) {
		int temperature = Integer.valueOf(temp);
		Event event = eventService.getByName(eventName);
		clothesMatcher.setTemp(temperature);
		clothesMatcher.setEvent(event);
		Matched match = clothesMatcher.addMatch();

		return match;
	}

	@POST
	@Path("/rate")
	@Consumes("application/json;charset=utf-8")
	public void updateRateInJSON(String src) {
		try {
			JSONObject jsonObject = new JSONObject(src);
			long matchedId = Long.valueOf(jsonObject.getString("matchedId"));
			int rate = Integer.valueOf(jsonObject.getString("rate"));
			clothesMatcher.updateMatched(matchedId, rate);
		} catch (NumberFormatException | JSONException e) {
			e.printStackTrace();
		}

	}

	@POST
	@Path("/post")
	@Consumes("application/json;charset=utf-8")
	public void createClothes(String src) {
		Clothes clothes = new Clothes();
		try {
			JSONObject jsonObject = new JSONObject(src);
			byte[] clothesPic = org.apache.commons.codec.binary.Base64
					.decodeBase64(jsonObject.getString("clothesPic").getBytes());
			clothes.setClothesPic(clothesPic);

			String colourName = jsonObject.getString("colourName");
			Colour colour = colourService.getByName(colourName);
			clothes.setMainColor(colour);

			String clothesTypeName = jsonObject.getString("clothesTypeName");
			ClothesType clothesType = clothesTypeService
					.getByName(clothesTypeName);
			clothes.setClothesType(clothesType);

			String clothesTags = jsonObject.getString("clothesTags");
			clothesTags = clothesTags.substring(1, clothesTags.length() - 1);
			List<Tag> tags = new ArrayList<>();
			for (String tag : clothesTags.trim().split(",")) {
				Tag t = tagService.getByName(tag.trim());
				tags.add(t);
			}
			clothes.setClothesTags(tags);

			String clothesDesc = jsonObject.getString("clothesDesc");
			clothes.setClothesDesc(clothesDesc);

			clothesService.register(clothes);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public ClothesService getClothesService() {
		return clothesService;
	}

	public void setClothesService(ClothesService clothesService) {
		this.clothesService = clothesService;
	}

	public TagService getTagService() {
		return tagService;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	public ClothesTypeService getClothesTypeService() {
		return clothesTypeService;
	}

	public void setClothesTypeService(ClothesTypeService clothesTypeService) {
		this.clothesTypeService = clothesTypeService;
	}

	public ColourService getColourService() {
		return colourService;
	}

	public void setColourService(ColourService colourService) {
		this.colourService = colourService;
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public ClothesMatcher getClothesMatcher() {
		return clothesMatcher;
	}

	public void setClothesMatcher(ClothesMatcher clothesMatcher) {
		this.clothesMatcher = clothesMatcher;
	}
}
