package cursoSelenium.utils;

/**
 * The Class JsUtils.
 */
public class JsUtils {

	/**
	 * Instantiates a new js utils.
	 */
	private JsUtils() {
	}

	/**
	 * Gets the element by id.
	 *
	 * @param id
	 *            the id
	 * @return the element by id
	 */
	public static String getElementById(final String id) {
		return String.format("return document.getElementById('%s')", id);
	}

	/**
	 * Gets the elements by tag name.
	 *
	 * @param tagName
	 *            the tag name
	 * @return the elements by tag name
	 */
	public static String getElementsByTagName(final String tagName) {
		return String.format("return document.getElementsByTagName('%s')",
				tagName);
	}
}
