package pl.edu.wat.timProject.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import pl.edu.wat.timProject.bean.RegisterClothes;
import pl.edu.wat.timProject.dataModel.hibernate.Tag;

@ManagedBean
@RequestScoped
public class TagConverter implements Converter {

	@ManagedProperty(value = "#{clothesReg}")
	RegisterClothes rc;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		for (Tag t : rc.getTagService().listAll()) {
			if (t.toString().equals(value)) {
				return t;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value instanceof Tag) {
			Tag tag = (Tag) value;
			return tag.toString();
		}
		return "";
	}

	public RegisterClothes getRc() {
		return rc;
	}

	public void setRc(RegisterClothes rc) {
		this.rc = rc;
	}

}
