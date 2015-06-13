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
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clothes")
public class Clothes implements Serializable {
	private static final long serialVersionUID = -8559346466207614057L;

	@Column(name = "clothes_id", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long clothesId;

	@ManyToOne(targetEntity = ClothesType.class)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "clothes_type_id", referencedColumnName = "clothes_type_id", nullable = false) })
	private ClothesType clothesType;

	@ManyToOne(targetEntity = Colour.class)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "colour_id", referencedColumnName = "colour_id", nullable = false) })
	private Colour mainColor;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "clothes_tag", joinColumns = { @JoinColumn(name = "clothes_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "tag_id", nullable = false, updatable = false) })
	private List<Tag> clothesTags;

	@Column(name = "clothes_desc", nullable = true)
	private String clothesDesc;

	@Column(name = "clothes_pic", length = 100000, nullable = false)
	private byte[] clothesPic;

	public long getClothesId() {
		return clothesId;
	}

	public void setClothesId(long clothesId) {
		this.clothesId = clothesId;
	}

	public ClothesType getClothesType() {
		return clothesType;
	}

	public void setClothesType(ClothesType clothesType) {
		this.clothesType = clothesType;
	}

	public Colour getMainColor() {
		return mainColor;
	}

	public void setMainColor(Colour mainColor) {
		this.mainColor = mainColor;
	}

	public List<Tag> getClothesTags() {
		return clothesTags;
	}

	public void setClothesTags(List<Tag> clothesTags) {
		this.clothesTags = clothesTags;
	}

	public String getClothesDesc() {
		return clothesDesc;
	}

	public void setClothesDesc(String clothesDesc) {
		this.clothesDesc = clothesDesc;
	}

	public byte[] getClothesPic() {
		return clothesPic;
	}

	public void setClothesPic(byte[] clothesPic) {
		this.clothesPic = clothesPic;
	}

}
