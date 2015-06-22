package pl.edu.wat.timProject.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.edu.wat.timProject.dataModel.hibernate.Event;
import pl.edu.wat.timProject.services.EventService;

@Component
@Path("/event")
public class EventJSONService {

	@Autowired
	private EventService eventService;

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getEventsInJSON() {
		List<Event> clothesTypes = eventService.listAll();

		return clothesTypes;
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
}
