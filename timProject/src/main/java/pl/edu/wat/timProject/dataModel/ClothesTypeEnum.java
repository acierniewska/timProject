package pl.edu.wat.timProject.dataModel;

public enum ClothesTypeEnum {
	TOP(0, "góra"), BOTTOM(1, "dó³"), COVER(2, "okrycie wierzchnie"), BOTH(3,
			"strój jednoczêœciowy");
	private int intValue;
	private String name;

	private ClothesTypeEnum(int intValue, String name) {
		this.intValue = intValue;
		this.name = name;
	}

	public static ClothesTypeEnum fromIntValue(int intVal) {
		for (ClothesTypeEnum e : ClothesTypeEnum.values()) {
			if (e.intValue == intVal) {
				return e;
			}
		}

		return null;
	}

	@Override
	public String toString() {
		return name;
	}

	public int getIntValue() {
		return intValue;
	}
}
