package pl.edu.wat.timProject.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DualListModel;

import pl.edu.wat.timProject.dataModel.hibernate.Event;
import pl.edu.wat.timProject.dataModel.hibernate.Tag;
import pl.edu.wat.timProject.services.EventService;
import pl.edu.wat.timProject.services.TagService;

@ManagedBean(name = "allTagView")
@ViewScoped
public class AllTagView implements Serializable {
	private static final long serialVersionUID = -674077618619786130L;

	@ManagedProperty("#{tagService}")
	private TagService tagService;
	@ManagedProperty("#{eventService}")
	private EventService eventService;

	private List<Tag> tags;
	private DualListModel<Event> events;

	private Tag selectedTag = new Tag();

	@PostConstruct
	public void init() {
		tags = tagService.listAll();
		events = new DualListModel<Event>(eventService.listAll(),
				new ArrayList<Event>());
	}

	public void updateSelectedTag() {
		tagService.update(selectedTag);
		for (Event e : events.getTarget()) {
			if (eventHasTag(e))
				continue;
			e.getEventsTag().add(selectedTag);
			eventService.update(e);
		}
		init();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("Pogoda " + selectedTag.getTagName()
						+ " zosta³a zmodyfikowana."));
	}

	private boolean eventHasTag(Event e) {
		boolean contains = false;
		for (Tag t : e.getEventsTag())
			if (t.getTagId() == selectedTag.getTagId())
				contains = true;

		return contains;
	}

	public void deleteSelectedTag() {
		for (Event e : events.getTarget()) {
			if (e.getEventsTag().contains(selectedTag)) {
				e.getEventsTag().remove(selectedTag);
				eventService.update(e);
			}
		}

		tagService.delete(selectedTag);
		init();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("Pogoda " + selectedTag.getTagName()
						+ " zosta³a usuniêta."));
	}

	public TagService getTagService() {
		return tagService;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Tag getSelectedTag() {
		return selectedTag;
	}

	public void setSelectedTag(Tag selectedTag) {
		this.selectedTag = selectedTag;

		List<Event> src = new ArrayList<Event>();
		List<Event> target = new ArrayList<Event>();
		for (Event e : eventService.listAll()) {
			if (e.getEventsTag().contains(selectedTag))
				target.add(e);
			else
				src.add(e);
		}

		events.setSource(src);
		events.setTarget(target);

	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public DualListModel<Event> getEvents() {
		return events;
	}

	public void setEvents(DualListModel<Event> events) {
		this.events = events;
	}

}
