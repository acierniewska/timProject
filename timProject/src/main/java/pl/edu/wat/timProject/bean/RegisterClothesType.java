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

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import pl.edu.wat.timProject.dataModel.ClothesTypeEnum;
import pl.edu.wat.timProject.dataModel.hibernate.ClothesType;
import pl.edu.wat.timProject.dataModel.hibernate.Event;
import pl.edu.wat.timProject.dataModel.hibernate.Weather;
import pl.edu.wat.timProject.services.ClothesTypeService;
import pl.edu.wat.timProject.services.EventService;
import pl.edu.wat.timProject.services.WeatherService;

@ManagedBean(name = "clothesTypeReg")
@SessionScoped
public class RegisterClothesType implements Serializable {
	private static final long serialVersionUID = -4359280736416081580L;

	@ManagedProperty("#{clothesTypeService}")
	private ClothesTypeService clothesTypeService;
	@ManagedProperty("#{weatherService}")
	private WeatherService weatherService;
	@ManagedProperty("#{eventService}")
	private EventService eventService;

	@ManagedProperty("#{allClothesTypeView}")
	private AllClothesTypeView allClothesTypeView;

	private ClothesType clothesType = new ClothesType();
	private Weather weather = new Weather();
	private ClothesTypeEnum clothesTypeEnum;

	private List<ClothesTypeEnum> enums;
	private List<Weather> weathers;

	private DualListModel<Event> events;

	@PostConstruct
	public void init() {
		this.weathers = weatherService.listAll();
		this.enums = new LinkedList<>();
		for (ClothesTypeEnum ctEnum : ClothesTypeEnum.values())
			enums.add(ctEnum);

		initDualListModel();
	}

	private void initDualListModel() {
		events = new DualListModel<Event>(eventService.listAll(),
				new ArrayList<Event>());
	}

	public String register() {
		clothesType.setClothesTypeValue(clothesTypeEnum.ordinal());
		clothesType.setWeather(getWeather());
		clothesTypeService.register(clothesType);

		for (Event e : events.getTarget()) {
			e.getEventsClothesTypes().add(clothesType);
			eventService.update(e);
		}

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("Rodzaj ubrania "
						+ this.clothesType.getClothesTypeName()
						+ " zosta³ poprawnie dodany"));
		clothesType = new ClothesType();
		setWeather(new Weather());
		clothesTypeEnum = null;
		initDualListModel();
		allClothesTypeView.init();

		return "";
	}

	public ClothesTypeService getClothesTypeService() {
		return clothesTypeService;
	}

	public void setClothesTypeService(ClothesTypeService clothesTypeService) {
		this.clothesTypeService = clothesTypeService;
	}

	public ClothesType getClothesType() {
		return clothesType;
	}

	public void setClothesType(ClothesType clothesType) {
		this.clothesType = clothesType;
	}

	public WeatherService getWeatherService() {
		return weatherService;
	}

	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	public List<Weather> getWeathers() {
		return weathers;
	}

	public void setWeathers(List<Weather> wathers) {
		this.weathers = wathers;
	}

	public ClothesTypeEnum getClothesTypeEnum() {
		return clothesTypeEnum;
	}

	public void setClothesTypeEnum(ClothesTypeEnum clothesTypeEnum) {
		this.clothesTypeEnum = clothesTypeEnum;
	}

	public List<ClothesTypeEnum> getEnums() {
		return enums;
	}

	public void setEnums(List<ClothesTypeEnum> enums) {
		this.enums = enums;
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

	public void onEventTransfer(TransferEvent e) {
	}

	public AllClothesTypeView getAllClothesTypeView() {
		return allClothesTypeView;
	}

	public void setAllClothesTypeView(AllClothesTypeView allClothesTypeView) {
		this.allClothesTypeView = allClothesTypeView;
	}
}
