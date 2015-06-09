package pl.edu.wat.timProject.dataModel.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "colour")
public class Colour implements Serializable {
	private static final long serialVersionUID = 464266206479027196L;
	
	@Column(name = "colour_id", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long colourId;

	@Column(name = "colour_name", nullable = false)
	private String colourName;

	public long getColourId() {
		return colourId;
	}

	public void setColourId(long colourId) {
		this.colourId = colourId;
	}

	public String getColourName() {
		return colourName;
	}

	public void setColourName(String colourName) {
		this.colourName = colourName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (colourId ^ (colourId >>> 32));
		result = prime * result
				+ ((colourName == null) ? 0 : colourName.hashCode());
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
		Colour other = (Colour) obj;
		if (colourId != other.colourId)
			return false;
		if (colourName == null) {
			if (other.colourName != null)
				return false;
		} else if (!colourName.equals(other.colourName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Colour [colourId=" + colourId + ", colourName=" + colourName
				+ "]";
	}
}
