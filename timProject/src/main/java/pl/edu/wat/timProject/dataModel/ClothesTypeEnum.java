package pl.edu.wat.timProject.dataModel;

public enum ClothesTypeEnum {
	TOP("góra"), BOTTOM("dó³"), COVER("okrycie wierzchnie"), BOTH(
			"strój jednoczêœciowy");

	private String name;

	private ClothesTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
