package pl.edu.wat.timProject.bean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import pl.edu.wat.timProject.dataModel.hibernate.Event;
import pl.edu.wat.timProject.dataModel.hibernate.Tag;
import pl.edu.wat.timProject.services.EventService;
import pl.edu.wat.timProject.services.TagService;

@ManagedBean(name = "tagReg")
@ViewScoped
// @ApplicationScoped
public class RegisterTag implements Serializable {
	private static final long serialVersionUID = -4359280736416081580L;

	@ManagedProperty("#{tagService}")
	private TagService tagService;
	@ManagedProperty("#{eventService}")
	private EventService eventService;
	@ManagedProperty("#{allTagView}")
	private AllTagView allTagView;

	private Tag tag = new Tag();
	private Event event = new Event();

	private DualListModel<Event> events;

	@PostConstruct
	public void init() {
		events = new DualListModel<Event>(eventService.listAll(),
				new ArrayList<Event>());
	}

	public String register() {
		tagService.register(tag);
		for (Event e : events.getTarget()) {
			e.getEventsTag().add(tag);
			eventService.update(e);
		}

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("Tag " + this.tag.getTagName()
						+ " zosta³ dodany."));

		tag = new Tag();
		init();
		allTagView.init();

		return "";
	}

	public TagService getTagService() {
		return tagService;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public DualListModel<Event> getEvents() {
		return events;
	}

	public void setEvents(DualListModel<Event> events) {
		this.events = events;
	}

	public void onEventTransfer(TransferEvent e) {
	}

	public AllTagView getAllTagView() {
		return allTagView;
	}

	public void setAllTagView(AllTagView allTagView) {
		this.allTagView = allTagView;
	}
}
