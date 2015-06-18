package pl.edu.wat.timProject.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import pl.edu.wat.timProject.dataModel.hibernate.Weather;
import pl.edu.wat.timProject.services.WeatherService;

@ManagedBean(name = "allWeatherView")
@ViewScoped
public class AllWeatherView implements Serializable {
	private static final long serialVersionUID = -674077618619786130L;

	@ManagedProperty("#{weatherService}")
	private WeatherService weatherService;

	private List<Weather> weathers;

	private Weather selectedWeather = new Weather();

	@PostConstruct
	public void init() {
		weathers = weatherService.listAll();
	}

	public void updateSelectedWeather() {
		weatherService.update(selectedWeather);
		init();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("Pogoda " + selectedWeather.getWeatherName()
						+ " zosta³a zmodyfikowana."));
	}

	public void deleteSelectedWeather() {
		weatherService.delete(selectedWeather);
		init();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("Pogoda " + selectedWeather.getWeatherName()
						+ " zosta³a usuniêta."));
	}

	public WeatherService getWeatherService() {
		return weatherService;
	}

	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	public List<Weather> getWeathers() {
		return weathers;
	}

	public void setWeathers(List<Weather> weathers) {
		this.weathers = weathers;
	}

	public Weather getSelectedWeather() {
		return selectedWeather;
	}

	public void setSelectedWeather(Weather selectedWeather) {
		this.selectedWeather = selectedWeather;
	}
}
