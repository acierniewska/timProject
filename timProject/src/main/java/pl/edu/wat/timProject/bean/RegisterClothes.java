package pl.edu.wat.timProject.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

import pl.edu.wat.timProject.dataModel.hibernate.Clothes;
import pl.edu.wat.timProject.dataModel.hibernate.ClothesType;
import pl.edu.wat.timProject.dataModel.hibernate.Colour;
import pl.edu.wat.timProject.dataModel.hibernate.Tag;
import pl.edu.wat.timProject.services.ClothesService;
import pl.edu.wat.timProject.services.ClothesTypeService;
import pl.edu.wat.timProject.services.ColourService;
import pl.edu.wat.timProject.services.TagService;

@ManagedBean(name = "clothesReg", eager = true)
@ViewScoped
public class RegisterClothes implements Serializable {
	private static final long serialVersionUID = -4359280736416081580L;

	@ManagedProperty("#{clothesService}")
	private ClothesService clothesService;

	@ManagedProperty("#{clothesTypeService}")
	private ClothesTypeService clothesTypeService;

	@ManagedProperty("#{colourService}")
	private ColourService colourService;

	@ManagedProperty("#{tagService}")
	private TagService tagService;

	private Clothes clothes = new Clothes();
	private Tag tag;
	private UploadedFile file;
	private ClothesType ct;

	private List<Tag> tags = new ArrayList<Tag>();
	private List<ClothesType> clothesTypes;
	private List<Colour> colours;

	@PostConstruct
	public void init() {
		this.clothesTypes = clothesTypeService.listAll();
		this.colours = colourService.listAll();
	}

	public String register() {
		addUploadedFileToClothes();
		clothes.setClothesTags(tags);
		clothesService.register(clothes);

		clothes = new Clothes();
		tags = new ArrayList<Tag>();

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("The Employee "
						+ " Is Registered Successfully"));

		return "";
	}

	private void addUploadedFileToClothes() {
		byte[] foto = null;
		try {
			foto = IOUtils.toByteArray(file.getInputstream());
			clothes.setClothesPic(foto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Tag> completeTag(String query) {
		List<Tag> allThemes = tagService.listAll();
		List<Tag> clothesTag = new ArrayList<Tag>();

		for (int i = 0; i < allThemes.size(); i++) {
			Tag tag = allThemes.get(i);
			if (tag.getTagName().startsWith(query) && !tags.contains(tag)) {
				clothesTag.add(tag);
			}
		}

		return clothesTag;
	}

	public ClothesService getClothesService() {
		return clothesService;
	}

	public void setClothesService(ClothesService clothesService) {
		this.clothesService = clothesService;
	}

	public ClothesTypeService getClothesTypeService() {
		return clothesTypeService;
	}

	public void setClothesTypeService(ClothesTypeService clothesTypeService) {
		this.clothesTypeService = clothesTypeService;
	}

	public ColourService getColourService() {
		return colourService;
	}

	public void setColourService(ColourService colourService) {
		this.colourService = colourService;
	}

	public TagService getTagService() {
		return tagService;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	public Clothes getClothes() {
		return clothes;
	}

	public void setClothes(Clothes clothes) {
		this.clothes = clothes;
	}

	public List<ClothesType> getClothesTypes() {
		return clothesTypes;
	}

	public void setClothesTypes(List<ClothesType> clothesTypes) {
		this.clothesTypes = clothesTypes;
	}

	public List<Colour> getColours() {
		return colours;
	}

	public void setColours(List<Colour> colours) {
		this.colours = colours;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public void handleSelect(SelectEvent e) {
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public ClothesType getCt() {
		return ct;
	}

	public void setCt(ClothesType ct) {
		this.ct = ct;
	}
}
