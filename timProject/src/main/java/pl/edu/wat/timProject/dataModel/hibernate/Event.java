package pl.edu.wat.timProject.dataModel.hibernate;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name = "event")
public class Event implements Serializable {
	private static final long serialVersionUID = -3260672422613929104L;

	@Id
	@Column(name = "event_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long eventId;

	@Column(name = "event_name", nullable = false)
	private String eventName;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "events_tag", joinColumns = { @JoinColumn(name = "event_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "tag_id", nullable = false, updatable = false) })
	private List<Tag> eventsTag;

	@IndexColumn(name = "INDEX_COL")
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "events_clothes_type", joinColumns = { @JoinColumn(name = "event_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "clothes_type_id", nullable = false, updatable = false) })
	private List<ClothesType> eventsClothesTypes;

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public List<Tag> getEventsTag() {
		return eventsTag;
	}

	public void setEventsTag(List<Tag> eventsTag) {
		this.eventsTag = eventsTag;
	}

	public List<ClothesType> getEventsClothesTypes() {
		return eventsClothesTypes;
	}

	public void setEventsClothesTypes(List<ClothesType> eventsClothesTypes) {
		this.eventsClothesTypes = eventsClothesTypes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (eventId ^ (eventId >>> 32));
		result = prime * result
				+ ((eventName == null) ? 0 : eventName.hashCode());
		result = prime
				* result
				+ ((eventsClothesTypes == null) ? 0 : eventsClothesTypes
						.hashCode());
		result = prime * result
				+ ((eventsTag == null) ? 0 : eventsTag.hashCode());
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
		Event other = (Event) obj;
		if (eventId != other.eventId)
			return false;
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		if (eventsClothesTypes == null) {
			if (other.eventsClothesTypes != null)
				return false;
		} else if (!eventsClothesTypes.equals(other.eventsClothesTypes))
			return false;
		if (eventsTag == null) {
			if (other.eventsTag != null)
				return false;
		} else if (!eventsTag.equals(other.eventsTag))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", eventName=" + eventName
				+ ", eventsTag=" + eventsTag + ", eventsClothesTypes="
				+ eventsClothesTypes + "]";
	}
}
