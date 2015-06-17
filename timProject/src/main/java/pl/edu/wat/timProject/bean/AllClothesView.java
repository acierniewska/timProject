package pl.edu.wat.timProject.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import pl.edu.wat.timProject.dataModel.hibernate.Clothes;
import pl.edu.wat.timProject.dataModel.hibernate.Tag;
import pl.edu.wat.timProject.services.ClothesService;
import pl.edu.wat.timProject.services.TagService;

@ManagedBean(name = "allClothesView")
@ApplicationScoped
public class AllClothesView {
	@ManagedProperty("#{clothesService}")
	private ClothesService clothesService;
	@ManagedProperty("#{tagService}")
	private TagService tagService;

	private List<Clothes> clothes;
	private Clothes selectedClothes = new Clothes();

	@PostConstruct
	public void init() {
		clothes = clothesService.listAll();
	}

	public List<Tag> completeTag(String query) {
		List<Tag> allThemes = tagService.listAll();
		List<Tag> clothesTag = new ArrayList<Tag>();

		for (int i = 0; i < allThemes.size(); i++) {
			Tag tag = allThemes.get(i);
			if (tag.getTagName().startsWith(query)
					&& !selectedClothes.getClothesTags().contains(tag)) {
				clothesTag.add(tag);
			}
		}

		return clothesTag;
	}

	public void update() {
		clothesService.update(selectedClothes);
	}

	public void handleSelect(SelectEvent e) {
		Tag t = (Tag) e.getObject();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Add Player", ""));
	}

	public ClothesService getClothesService() {
		return clothesService;
	}

	public void setClothesService(ClothesService clothesService) {
		this.clothesService = clothesService;
	}

	public List<Clothes> getClothes() {
		return clothes;
	}

	public void setClothes(List<Clothes> clothes) {
		this.clothes = clothes;
	}

	public Clothes getSelectedClothes() {
		return selectedClothes;
	}

	public void setSelectedClothes(Clothes selectedClothes) {
		this.selectedClothes = selectedClothes;
	}

	public TagService getTagService() {
		return tagService;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

}
