package pl.edu.wat.timProject.bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import pl.edu.wat.timProject.services.ClothesService;

@ManagedBean
@ApplicationScoped
public class ImageStreamer {

	@ManagedProperty("#{clothesService}")
	private ClothesService clothesService;

	public byte[] getById(Long id) {
		if (id == null)
			return new byte[1];

		return clothesService.getPic(id);
	}

	public ClothesService getClothesService() {
		return clothesService;
	}

	public void setClothesService(ClothesService clothesService) {
		this.clothesService = clothesService;
	}

}