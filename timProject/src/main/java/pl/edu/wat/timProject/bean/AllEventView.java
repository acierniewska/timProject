package pl.edu.wat.timProject.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import pl.edu.wat.timProject.dataModel.hibernate.Event;
import pl.edu.wat.timProject.services.EventService;

@ManagedBean
@ApplicationScoped
public class AllEventView {
	@ManagedProperty("#{eventService}")
	private EventService eventService;

	private Event selectedEvent = new Event();
	private List<Event> events;

	@PostConstruct
	public void init() {
		events = eventService.listAll();
	}

	public void update() {
		eventService.update(selectedEvent);
		init();
	}

	public void delete() {
		eventService.delete(selectedEvent);
		init();
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public Event getSelectedEvent() {
		return selectedEvent;
	}

	public void setSelectedEvent(Event selectedEvent) {
		this.selectedEvent = selectedEvent;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
}
