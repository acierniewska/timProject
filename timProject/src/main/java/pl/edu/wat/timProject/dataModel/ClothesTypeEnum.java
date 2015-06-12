package pl.edu.wat.timProject.dataModel;

public enum ClothesTypeEnum {
	TOP("g�ra"), BOTTOM("d�"), COVER("okrycie wierzchnie"), BOTH(
			"str�j jednocz�ciowy");

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
