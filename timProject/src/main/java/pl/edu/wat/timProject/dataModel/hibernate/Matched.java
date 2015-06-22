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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "matched")
public class Matched implements Serializable {
	private static final long serialVersionUID = 7609361674077660210L;

	@Id
	@Column(name = "matched_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long matchedId;

	@Column(name = "matched_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private java.util.Date matchedDate;

	@Column(name = "rate", nullable = false)
	private int rate;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "matched_clothes", joinColumns = { @JoinColumn(name = "matched_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "clothes_id", nullable = false, updatable = false) })
	private List<Clothes> matchedClothes;

	public long getMatchedId() {
		return matchedId;
	}

	public void setMatchedId(long matchedId) {
		this.matchedId = matchedId;
	}

	public java.util.Date getMatchedDate() {
		return matchedDate;
	}

	public void setMatchedDate(java.util.Date matchedDate) {
		this.matchedDate = matchedDate;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public List<Clothes> getMatchedClothes() {
		return matchedClothes;
	}

	public void setMatchedClothes(List<Clothes> matchedClothes) {
		this.matchedClothes = matchedClothes;
	}

	@Override
	public String toString() {
		return "Matched [matchedId=" + matchedId + ", matchedDate="
				+ matchedDate + ", rate=" + rate + ", matchedClothes="
				+ matchedClothes + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((matchedClothes == null) ? 0 : matchedClothes.hashCode());
		result = prime * result
				+ ((matchedDate == null) ? 0 : matchedDate.hashCode());
		result = prime * result + (int) (matchedId ^ (matchedId >>> 32));
		result = prime * result + rate;
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
		Matched other = (Matched) obj;
		if (matchedClothes == null) {
			if (other.matchedClothes != null)
				return false;
		} else if (!matchedClothes.equals(other.matchedClothes))
			return false;
		if (matchedDate == null) {
			if (other.matchedDate != null)
				return false;
		} else if (!matchedDate.equals(other.matchedDate))
			return false;
		if (matchedId != other.matchedId)
			return false;
		if (rate != other.rate)
			return false;
		return true;
	}

}
