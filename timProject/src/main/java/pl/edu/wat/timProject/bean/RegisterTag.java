package pl.edu.wat.timProject.bean;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import pl.edu.wat.timProject.dataModel.hibernate.Tag;
import pl.edu.wat.timProject.services.TagService;

@ManagedBean(name = "tagReg")
@ApplicationScoped
public class RegisterTag implements Serializable {
	private static final long serialVersionUID = -4359280736416081580L;

	@ManagedProperty("#{tagService}")
	private TagService tagService;
	private Tag tag = new Tag();

	public String register() {
		tagService.register(tag);

		tag = new Tag();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("The Employee " + this.tag.getTagName()
						+ " Is Registered Successfully"));
		return "";
	}

	public TagService getTagService() {
		return tagService;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}
}
