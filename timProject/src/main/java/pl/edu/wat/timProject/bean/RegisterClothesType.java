package pl.edu.wat.timProject.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pl.edu.wat.timProject.dataModel.hibernate.ClothesType;
import pl.edu.wat.timProject.services.ClothesTypeService;

@ManagedBean(name="clothesTypeReg")
@SessionScoped
public class RegisterClothesType implements Serializable {
	private static final long serialVersionUID = -4359280736416081580L;

	@ManagedProperty("#{clothesTypeService}")
	private ClothesTypeService clothesTypeService;
	private ClothesType clothesType = new ClothesType();

	public String register() {
		clothesTypeService.register(clothesType);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("The Employee " + this.clothesType.getClothesTypeName()
						+ " Is Registered Successfully"));
		return "";
	}

	public ClothesTypeService getClothesTypeService() {
		return clothesTypeService;
	}

	public void setClothesTypeService(ClothesTypeService clothesTypeService) {
		this.clothesTypeService = clothesTypeService;
	}

	public ClothesType getClothesType() {
		return clothesType;
	}

	public void setClothesType(ClothesType clothesType) {
		this.clothesType = clothesType;
	}
}
