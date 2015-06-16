package pl.edu.wat.timProject.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import pl.edu.wat.timProject.bean.RegisterEvent;
import pl.edu.wat.timProject.dataModel.hibernate.Event;

@ManagedBean
@RequestScoped
public class EventConverter implements Converter {

	@ManagedProperty(value = "#{eventReg}")
	RegisterEvent re;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		for (Event e : re.getEventService().listAll()) {
			if (e.toString().equals(value)) {
				return e;
			}
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value instanceof Event) {
			Event event = (Event) value;
			return event.toString();
		}

		return "";
	}

	public RegisterEvent getRe() {
		return re;
	}

	public void setRe(RegisterEvent re) {
		this.re = re;
	}
}
