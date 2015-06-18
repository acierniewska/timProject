package pl.edu.wat.timProject.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DualListModel;

import pl.edu.wat.timProject.dataModel.hibernate.ClothesType;
import pl.edu.wat.timProject.dataModel.hibernate.Event;
import pl.edu.wat.timProject.dataModel.hibernate.Tag;
import pl.edu.wat.timProject.services.ClothesTypeService;
import pl.edu.wat.timProject.services.EventService;
import pl.edu.wat.timProject.services.TagService;

@ManagedBean
@ViewScoped
// @ApplicationScoped
public class AllEventView implements Serializable {
	private static final long serialVersionUID = -4398851965938204711L;

	@ManagedProperty("#{eventService}")
	private EventService eventService;
	@ManagedProperty("#{tagService}")
	private TagService tagService;
	@ManagedProperty("#{clothesTypeService}")
	private ClothesTypeService clothesTypeService;

	private Event selectedEvent = new Event();
	private List<Event> events;

	private DualListModel<Tag> tags;
	private DualListModel<ClothesType> clothesType;

	@PostConstruct
	public void init() {
		events = eventService.listAll();
		tags = new DualListModel<Tag>(tagService.listAll(),
				new ArrayList<Tag>());
		clothesType = new DualListModel<ClothesType>(
				clothesTypeService.listAll(), new ArrayList<ClothesType>());
	}

	public void update() {
		selectedEvent.setEventsClothesTypes(clothesType.getTarget());
		selectedEvent.setEventsTag(tags.getTarget());
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

		List<Tag> srcTag = tagService.listAll();
		srcTag.removeAll(selectedEvent.getEventsTag());
		this.tags = new DualListModel<Tag>(srcTag, selectedEvent.getEventsTag());

		List<ClothesType> srcClothesTag = clothesTypeService.listAll();
		srcClothesTag.removeAll(selectedEvent.getEventsTag());
		this.clothesType = new DualListModel<ClothesType>(srcClothesTag,
				selectedEvent.getEventsClothesTypes());
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public DualListModel<Tag> getTags() {
		return tags;
	}

	public void setTags(DualListModel<Tag> tags) {
		this.tags = tags;
	}

	public DualListModel<ClothesType> getClothesType() {
		return clothesType;
	}

	public void setClothesType(DualListModel<ClothesType> clothesType) {
		this.clothesType = clothesType;
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

}
