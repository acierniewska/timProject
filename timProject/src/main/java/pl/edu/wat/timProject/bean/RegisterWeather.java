package pl.edu.wat.timProject.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pl.edu.wat.timProject.dataModel.hibernate.Weather;
import pl.edu.wat.timProject.services.WeatherService;

@ManagedBean(name = "weatherReg")
@SessionScoped
public class RegisterWeather implements Serializable {
	private static final long serialVersionUID = -4359280736416081580L;

	@ManagedProperty("#{weatherService}")
	private WeatherService weatherService;

	@ManagedProperty("#{allWeatherView}")
	private AllWeatherView allWeatherView;

	private Weather weather = new Weather();

	@PostConstruct
	public void init() {
		weather.setTemperatureFrom(15);
		weather.setTemperatureTo(30);
	}

	public String register() {
		weatherService.register(weather);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("The Employee "
						+ this.weather.getWeatherName()
						+ " Is Registered Successfully"));

		weather = new Weather();
		allWeatherView.init();

		return "";
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

	public AllWeatherView getAllWeatherView() {
		return allWeatherView;
	}

	public void setAllWeatherView(AllWeatherView allWeatherView) {
		this.allWeatherView = allWeatherView;
	}
}
