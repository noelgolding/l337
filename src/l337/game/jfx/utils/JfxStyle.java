package l337.game.jfx.utils;

public abstract class JfxStyle {
	// style attribute names
	public static final String FX_BACKGROUND_COLOR = "-fx-background-color";
	public static final String FX_BORDER_COLOR = "-fx-border-color";

	// color attribute values
	public static final String FX_WHITE = "white";
	public static final String FX_BLACK = "black";
	public static final String FX_RED = "red";
	
	/**
	 * TODO better javadoc
	 * @param attributeName
	 * @param attributeValue
	 * @return "attributeName: attributeValue[0] attributeValue[...];"
	 */
	public static String jfxStyle(String attributeName, String attributeValue){
		if (attributeName == null 
				|| attributeName.trim().isEmpty()
				|| attributeValue == null
				|| attributeValue.trim().isEmpty())
			return "";
		return String.format("%s: %s;",attributeName, attributeValue);
	}
}
