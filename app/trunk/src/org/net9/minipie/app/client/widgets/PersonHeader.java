/**
 * 
 */
package org.net9.minipie.app.client.widgets;

import org.net9.minipie.app.client.model.PersonEntry;
import org.net9.minipie.app.client.model.ProfileEntry;
import org.net9.minipie.app.client.model.UserEntry;

import com.extjs.gxt.ui.client.core.Template;
import com.extjs.gxt.ui.client.util.Params;
import com.extjs.gxt.ui.client.widget.Html;
import com.google.gwt.user.client.ui.HTML;

/**
 * @author LeeThree
 * 
 */
public class PersonHeader {

	public static Html createHeader(PersonEntry entry) {
		return createHeader(entry, HEADER_TEMP);
	}

	public static Html createHeader(ProfileEntry entry) {
		// TODO
		return createHeader(entry, PROFILE_HEADER_TEMP);
	}

	private static Html createHeader(PersonEntry entry, String template) {
		Template basicTemplate = new Template(template);
		HTML html = new HTML();
		basicTemplate.overwrite(html.getElement(), new Params(entry
				.getProperties()));
		return new Html(html.getHTML());
	}

	private static final String HEADER_TEMP = "<div class='header'><div class='thumb'><img src='{image}'"
			+ " width=96 height=96 /></div><div class='text'><h3>{name}</h3>"
			+ "<h4>{category}</h4><p><b>Relationships: </b>{rel}</p>"
			+ "<p><b>Tags: </b>{tags}</p></div></div>";

	private static final String PROFILE_HEADER_TEMP = "<div class='header profile'><div class='thumb'><img src='{image}'"
			+ " width=64 height=64 /></div><div class='text'><h3>{name}</h3>"
			+ "<h4>{category}</h4></div></div>";
}
