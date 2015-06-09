package pl.edu.wat.timProject.dataModel.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "weather")
public class Weather implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "weather_id", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long weatherId;

	@Column(name = "weather_name", nullable = false)
	private String weatherName;

	@Column(name = "temperature_from", nullable = false)
	private Double temperatureFrom;

	@Column(name = "temperature_to", nullable = false)
	private Double temperatureTo;

	public long getWeatherId() {
		return weatherId;
	}

	public void setWeatherId(long weatherId) {
		this.weatherId = weatherId;
	}

	public String getWeatherName() {
		return weatherName;
	}

	public void setWeatherName(String weatherName) {
		this.weatherName = weatherName;
	}

	public Double getTemperatureFrom() {
		return temperatureFrom;
	}

	public void setTemperatureFrom(Double temperatureFrom) {
		this.temperatureFrom = temperatureFrom;
	}

	public Double getTemperatureTo() {
		return temperatureTo;
	}

	public void setTemperatureTo(Double temperatureTo) {
		this.temperatureTo = temperatureTo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((temperatureFrom == null) ? 0 : temperatureFrom.hashCode());
		result = prime * result
				+ ((temperatureTo == null) ? 0 : temperatureTo.hashCode());
		result = prime * result + (int) (weatherId ^ (weatherId >>> 32));
		result = prime * result
				+ ((weatherName == null) ? 0 : weatherName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Weather other = (Weather) obj;
		if (temperatureFrom == null) {
			if (other.temperatureFrom != null)
				return false;
		} else if (!temperatureFrom.equals(other.temperatureFrom))
			return false;
		if (temperatureTo == null) {
			if (other.temperatureTo != null)
				return false;
		} else if (!temperatureTo.equals(other.temperatureTo))
			return false;
		if (weatherId != other.weatherId)
			return false;
		if (weatherName == null) {
			if (other.weatherName != null)
				return false;
		} else if (!weatherName.equals(other.weatherName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Weather [weatherId=" + weatherId + ", weatherName="
				+ weatherName + ", temperatureFrom=" + temperatureFrom
				+ ", temperatureTo=" + temperatureTo + "]";
	}
}
