package pl.edu.wat.timProject.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DualListModel;

import pl.edu.wat.timProject.dataModel.ClothesTypeEnum;
import pl.edu.wat.timProject.dataModel.hibernate.ClothesType;
import pl.edu.wat.timProject.dataModel.hibernate.Event;
import pl.edu.wat.timProject.dataModel.hibernate.Weather;
import pl.edu.wat.timProject.services.ClothesTypeService;
import pl.edu.wat.timProject.services.EventService;
import pl.edu.wat.timProject.services.WeatherService;

@ManagedBean(name = "allClothesTypeView")
@SessionScoped
public class AllClothesTypeView implements Serializable {
	private static final long serialVersionUID = -674077618619786130L;

	@ManagedProperty("#{clothesTypeService}")
	private ClothesTypeService clothesTypeService;
	@ManagedProperty("#{eventService}")
	private EventService eventService;
	@ManagedProperty("#{weatherService}")
	private WeatherService weatherService;

	private List<ClothesTypeEnum> enums;
	private List<Weather> weathers;
	private List<ClothesType> clothesTypes;
	private DualListModel<Event> events;

	private ClothesTypeEnum clothesTypeEnum;
	private ClothesType selectedClothesType = new ClothesType();

	@PostConstruct
	public void init() {
		this.weathers = weatherService.listAll();
		this.enums = new LinkedList<>();
		for (ClothesTypeEnum ctEnum : ClothesTypeEnum.values())
			enums.add(ctEnum);
		clothesTypes = clothesTypeService.listAll();
		events = new DualListModel<Event>(eventService.listAll(),
				new ArrayList<Event>());
	}

	public void updateSelectedClothesType() {
		selectedClothesType.setClothesTypeValue(clothesTypeEnum.ordinal());
		clothesTypeService.update(selectedClothesType);
		for (Event e : events.getTarget()) {
			if (eventHasClothesType(e))
				continue;
			e.getEventsClothesTypes().add(selectedClothesType);
			eventService.update(e);
		}
		init();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("Pogoda "
						+ selectedClothesType.getClothesTypeName()
						+ " zosta³a zmodyfikowana."));
	}

	private boolean eventHasClothesType(Event e) {
		boolean contains = false;
		for (ClothesType t : e.getEventsClothesTypes())
			if (t.getClothesTypeId() == selectedClothesType.getClothesTypeId())
				contains = true;

		return contains;
	}

	public void deleteSelectedClothesType() {
		for (Event e : events.getTarget()) {
			if (e.getEventsClothesTypes().contains(selectedClothesType)) {
				e.getEventsClothesTypes().remove(selectedClothesType);
				eventService.update(e);
			}
		}

		clothesTypeService.delete(selectedClothesType);
		init();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("Pogoda "
						+ selectedClothesType.getClothesTypeName()
						+ " zosta³a usuniêta."));
	}

	public ClothesTypeService getClothesTypeService() {
		return clothesTypeService;
	}

	public void setClothesTypeService(ClothesTypeService clothesTypeService) {
		this.clothesTypeService = clothesTypeService;
	}

	public List<ClothesType> getClothesTypes() {
		return clothesTypes;
	}

	public void setClothesTypes(List<ClothesType> clothesTypes) {
		this.clothesTypes = clothesTypes;
	}

	public ClothesType getSelectedClothesType() {
		return selectedClothesType;
	}

	public void setSelectedClothesType(ClothesType selectedClothesType) {
		this.selectedClothesType = selectedClothesType;

		clothesTypeEnum = enumFromIntValue(selectedClothesType);
		List<Event> src = new ArrayList<Event>();
		List<Event> target = new ArrayList<Event>();
		for (Event e : eventService.listAll()) {
			if (e.getEventsClothesTypes().contains(this.selectedClothesType))
				target.add(e);
			else
				src.add(e);
		}

		events.setSource(src);
		events.setTarget(target);

	}

	public ClothesTypeEnum enumFromIntValue(ClothesType selectedClothesType) {
		if (selectedClothesType.getClothesTypeValue() == null)
			return ClothesTypeEnum.BOTTOM;
		return ClothesTypeEnum.values()[selectedClothesType
				.getClothesTypeValue()];
	}

	public String nameFromIntValue(ClothesType selectedClothesType) {
		return enumFromIntValue(selectedClothesType).getName();
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

	public WeatherService getWeatherService() {
		return weatherService;
	}

	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	public List<ClothesTypeEnum> getEnums() {
		return enums;
	}

	public void setEnums(List<ClothesTypeEnum> enums) {
		this.enums = enums;
	}

	public List<Weather> getWeathers() {
		return weathers;
	}

	public void setWeathers(List<Weather> weathers) {
		this.weathers = weathers;
	}

	public ClothesTypeEnum getClothesTypeEnum() {
		return clothesTypeEnum;
	}

	public void setClothesTypeEnum(ClothesTypeEnum clothesTypeEnum) {
		this.clothesTypeEnum = clothesTypeEnum;
	}

}
