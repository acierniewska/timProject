package pl.edu.wat.timProject.dataModel.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clothes_type")
public class ClothesType implements Serializable {
	private static final long serialVersionUID = 1306262380834946252L;

	@Column(name = "clothes_type_id", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long clothesTypeId;

	@Column(name = "clothes_type_name", nullable = false)
	private String clothesTypeName;

	@Column(name = "clothes_type_value", nullable = false)
	private Integer clothesTypeValue;

	@ManyToOne(targetEntity = Weather.class)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "weather_id", referencedColumnName = "weather_id", nullable = false) })
	private Weather weather;

	public long getClothesTypeId() {
		return clothesTypeId;
	}

	public void setClothesTypeId(long clothesTypeId) {
		this.clothesTypeId = clothesTypeId;
	}

	public String getClothesTypeName() {
		return clothesTypeName;
	}

	public void setClothesTypeName(String clothesTypeName) {
		this.clothesTypeName = clothesTypeName;
	}

	public Integer getClothesTypeValue() {
		return clothesTypeValue;
	}

	public void setClothesTypeValue(Integer clothesTypeValue) {
		this.clothesTypeValue = clothesTypeValue;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (clothesTypeId ^ (clothesTypeId >>> 32));
		result = prime * result
				+ ((clothesTypeName == null) ? 0 : clothesTypeName.hashCode());
		result = prime
				* result
				+ ((clothesTypeValue == null) ? 0 : clothesTypeValue.hashCode());
		result = prime * result + ((weather == null) ? 0 : weather.hashCode());
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
		ClothesType other = (ClothesType) obj;
		if (clothesTypeId != other.clothesTypeId)
			return false;
		if (clothesTypeName == null) {
			if (other.clothesTypeName != null)
				return false;
		} else if (!clothesTypeName.equals(other.clothesTypeName))
			return false;
		if (clothesTypeValue == null) {
			if (other.clothesTypeValue != null)
				return false;
		} else if (!clothesTypeValue.equals(other.clothesTypeValue))
			return false;
		if (weather == null) {
			if (other.weather != null)
				return false;
		} else if (!weather.equals(other.weather))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClothesType [clothesTypeId=" + clothesTypeId
				+ ", clothesTypeName=" + clothesTypeName
				+ ", clothesTypeValue=" + clothesTypeValue + ", weather="
				+ weather + "]";
	}

}
