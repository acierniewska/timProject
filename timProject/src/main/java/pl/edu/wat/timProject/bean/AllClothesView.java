package pl.edu.wat.timProject.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import pl.edu.wat.timProject.dataModel.hibernate.Clothes;
import pl.edu.wat.timProject.services.ClothesService;

@ManagedBean(name = "allClothesView")
@ApplicationScoped
public class AllClothesView {
	@ManagedProperty("#{clothesService}")
	private ClothesService clothesService;

	private List<Clothes> clothes;

	@PostConstruct
	public void init() {
		clothes = clothesService.listAll();
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
}
