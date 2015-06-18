package pl.edu.wat.timProject.bean;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import pl.edu.wat.timProject.services.ClothesService;

@ManagedBean
@ApplicationScoped
public class ImageStreamer implements Serializable {
	private static final long serialVersionUID = -2465081057479506994L;

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