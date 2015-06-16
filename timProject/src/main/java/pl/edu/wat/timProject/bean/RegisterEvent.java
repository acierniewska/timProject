package pl.edu.wat.timProject.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import pl.edu.wat.timProject.dataModel.hibernate.ClothesType;
import pl.edu.wat.timProject.dataModel.hibernate.Event;
import pl.edu.wat.timProject.dataModel.hibernate.Tag;
import pl.edu.wat.timProject.services.ClothesTypeService;
import pl.edu.wat.timProject.services.EventService;
import pl.edu.wat.timProject.services.TagService;

@ManagedBean(name = "eventReg")
@ViewScoped
public class RegisterEvent {
	@ManagedProperty("#{eventService}")
	private EventService eventService;
	@ManagedProperty("#{tagService}")
	private TagService tagService;
	@ManagedProperty("#{clothesTypeService}")
	private ClothesTypeService clothesTypeService;

	private Event event = new Event();

	private DualListModel<Tag> tags;
	private DualListModel<ClothesType> clothesTypes;

	@PostConstruct
	public void init() {
		tags = new DualListModel<Tag>(tagService.listAll(),
				new ArrayList<Tag>());
		clothesTypes = new DualListModel<ClothesType>(
				clothesTypeService.listAll(), new ArrayList<ClothesType>());
	}

	public void onTagTransfer(TransferEvent event) {
		List<Tag> selectedTags = (List<Tag>) event.getItems();

	}

	public void onClothesTypeTransfer(TransferEvent event) {

	}

	public String register() {
		event.setEventsClothesTypes(clothesTypes.getTarget());
		event.setEventsTag(tags.getTarget());
		eventService.register(event);

		event = new Event();
		init();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("The Employee "
						+ " Is Registered Successfully"));

		return "";
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
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

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public DualListModel<Tag> getTags() {
		return tags;
	}

	public void setTags(DualListModel<Tag> tags) {
		this.tags = tags;
	}

	public DualListModel<ClothesType> getClothesTypes() {
		return clothesTypes;
	}

	public void setClothesTypes(DualListModel<ClothesType> clothesTypes) {
		this.clothesTypes = clothesTypes;
	}

}
