package pl.edu.wat.timProject.bean;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pl.edu.wat.timProject.dataModel.ClothesTypeEnum;
import pl.edu.wat.timProject.dataModel.hibernate.ClothesType;
import pl.edu.wat.timProject.dataModel.hibernate.Weather;
import pl.edu.wat.timProject.services.ClothesTypeService;
import pl.edu.wat.timProject.services.WeatherService;

@ManagedBean(name = "clothesTypeReg")
@SessionScoped
public class RegisterClothesType implements Serializable {
	private static final long serialVersionUID = -4359280736416081580L;

	@ManagedProperty("#{clothesTypeService}")
	private ClothesTypeService clothesTypeService;

	@ManagedProperty("#{weatherService}")
	private WeatherService weatherService;

	private ClothesType clothesType = new ClothesType();
	private Weather weather = new Weather();
	private List<Weather> weathers;
	private ClothesTypeEnum clothesTypeEnum;

	private List<ClothesTypeEnum> enums;

	@PostConstruct
	public void init() {
		this.weathers = weatherService.listWeather();
		this.enums = new LinkedList<>();
		for (ClothesTypeEnum ctEnum : ClothesTypeEnum.values())
			enums.add(ctEnum);
		
	}

	public String register() {
		clothesType.setClothesTypeValue(clothesTypeEnum.ordinal());
		clothesType.setWeather(getWeather());
		clothesTypeService.register(clothesType);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("The Employee "
						+ this.clothesType.getClothesTypeName()
						+ " Is Registered Successfully"));
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

}
