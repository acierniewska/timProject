package pl.edu.wat.timProject.dataModel.hibernate;

import java.io.Serializable;
import java.util.Set;

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

@Entity
@Table(name = "tag")
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "tag_id", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long tagId;

	@Column(name = "tag_name", nullable = false, unique = true)
	private String tagName;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "clothes_tag", joinColumns = { @JoinColumn(name = "tag_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "clothes_id", nullable = false, updatable = false) })
	private Set<Tag> clothesTags;

	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Set<Tag> getClothesTags() {
		return clothesTags;
	}

	public void setClothesTags(Set<Tag> clothesTags) {
		this.clothesTags = clothesTags;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((clothesTags == null) ? 0 : clothesTags.hashCode());
		result = prime * result + (int) (tagId ^ (tagId >>> 32));
		result = prime * result + ((tagName == null) ? 0 : tagName.hashCode());
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
		Tag other = (Tag) obj;
		if (clothesTags == null) {
			if (other.clothesTags != null)
				return false;
		} else if (!clothesTags.equals(other.clothesTags))
			return false;
		if (tagId != other.tagId)
			return false;
		if (tagName == null) {
			if (other.tagName != null)
				return false;
		} else if (!tagName.equals(other.tagName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tag [tagId=" + tagId + ", tagName=" + tagName
				+ ", clothesTags=" + clothesTags + "]";
	}
}
