/**
 * 
 */
package org.net9.minipie.app.client.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.net9.minipie.app.client.MiniApp;
import org.net9.minipie.app.client.MiniAppServiceAsync;
import org.net9.minipie.app.client.data.PersonBean;
import org.net9.minipie.app.client.model.ContactEntry;
import org.net9.minipie.app.client.model.DetailData;
import org.net9.minipie.app.client.model.PersonEntry;
import org.net9.minipie.app.client.model.UserEntry;
import org.net9.minipie.app.client.widgets.PersonHeader;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.grid.CheckColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridGroupRenderer;
import com.extjs.gxt.ui.client.widget.grid.GroupColumnData;
import com.extjs.gxt.ui.client.widget.grid.GroupingView;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout.HBoxLayoutAlign;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author LeeThree
 * 
 */
public class DetailPage extends LayoutContainer {

	protected final PersonEntry entry;

	public DetailPage(PersonEntry entry) {
		this.entry = entry;
		final MiniAppServiceAsync service = (MiniAppServiceAsync) Registry
				.get(MiniApp.SERVICE);
		this.mask("Loading...");
		if (entry instanceof UserEntry) {
			service.viewUserContact(entry.getId(),
					new AsyncCallback<PersonBean>() {

						@Override
						public void onFailure(Throwable caught) {
							Info.display("Failed loading information", caught
									.getMessage());
							DetailPage.this.mask();
						}

						@Override
						public void onSuccess(PersonBean result) {
							initialize(new UserEntry(result));
						}

					});
		} else if (entry instanceof ContactEntry) {
			service.viewContact(entry.getId(), new AsyncCallback<PersonBean>() {

				@Override
				public void onFailure(Throwable caught) {
					Info.display("Failed loading information", caught
							.getMessage());
					DetailPage.this.mask();
				}

				@Override
				public void onSuccess(PersonBean result) {
					initialize(new ContactEntry(result));
				}

			});
		} else {
			// TODO to-be-modified
			initialize(entry);
		}
	}

	// @Override
	// protected void onRender(Element parent, int index) {
	// super.onRender(parent, index);
	//
	// }

	private void initialize(PersonEntry person) {
		this.setLayout(new RowLayout());
		this.add(PersonHeader.createHeader(person), new RowData(1, 140));
		// this.add(new Editor(new LabelField("name!")));
		LayoutContainer info = new LayoutContainer();
		// HBoxLayout layout = new HBoxLayout();
		// layout.setHBoxLayoutAlign(HBoxLayoutAlign.STRETCH);
		// info.setLayout(new RowLayout(Orientation.HORIZONTAL));
		info.setLayout(new BorderLayout());

		BorderLayoutData westLayoutData = new BorderLayoutData(
				LayoutRegion.WEST, 250, 220, 400);
		westLayoutData.setSplit(true);
		info.add(createBasicInfoPanel(person), westLayoutData);
		info.add(createDetailInfoPanel(person), new BorderLayoutData(
				LayoutRegion.CENTER, 500));
		// info.layout();
		this.add(info, new RowData(1, 1));
		this.unmask();
		this.layout();
	}

	private ContentPanel createDetailInfoPanel(PersonEntry person) {
		GroupingStore<DetailData> store = new GroupingStore<DetailData>();
		store.add(person.getAllDetails());
		store.groupBy("detail");

		ColumnConfig detail = new ColumnConfig("detail", "Detail", 0);
		ColumnConfig type = new ColumnConfig("type", "Type", 30);
		ColumnConfig value = new ColumnConfig("value", "Value", 50);
		ColumnConfig primary = new CheckColumnConfig("primary", "Primary", 20);
		List<ColumnConfig> config = new ArrayList<ColumnConfig>();
		config.add(detail);
		config.add(type);
		config.add(value);
		config.add(primary);

		final ColumnModel cm = new ColumnModel(config);

		GroupingView view = new GroupingView();
		view.setShowGroupedColumn(false);
		view.setForceFit(true);
		view.setGroupRenderer(new GridGroupRenderer() {
			public String render(GroupColumnData data) {
				String l = data.models.size() == 1 ? "Item" : "Items";
				return data.group + " (" + data.models.size() + " " + l + ")";
			}
		});

		Grid<DetailData> grid = new Grid<DetailData>(store, cm);
		grid.setView(view);
		// grid.setBorders(true);

		ContentPanel detailInfo = new ContentPanel(new FitLayout());
		detailInfo.setHeading("Detailed Information");
		detailInfo.add(grid);

		return detailInfo;
	}

	private FormPanel createBasicInfoPanel(PersonEntry person) {
		FormPanel panel = new FormPanel();
		panel.setHeading("Basic Information");
		// Map<String, String> basic = person.getBean().properties;
		// panel.setDeferHeight(true);
		panel.setBodyBorder(false);
		panel.setFrame(false);
		// panel.setFieldWidth(180);
		panel.setLabelAlign(LabelAlign.TOP);
		panel.setStyleName("basic");
		// panel.setLabelWidth(150);
		panel.setLabelSeparator(" : ");

		LabelField idField = new LabelField(Long.toString(person.getId()));
		idField.setFieldLabel("ID");
		panel.add(idField);

		LabelField nameField = new LabelField(person.getName());
		nameField.setFieldLabel("Name");
		panel.add(nameField);

		LabelField usernameField = new LabelField(person
				.getProperty("username"));
		usernameField.setFieldLabel("Username");
		panel.add(usernameField);

		LabelField genderField = new LabelField(person.getProperty("gender"));
		genderField.setFieldLabel("Gender");
		panel.add(genderField);

		LabelField birthdayField = new LabelField(person
				.getProperty("birthday"));
		birthdayField.setFieldLabel("Birthday");
		panel.add(birthdayField);

		LabelField noteField = new LabelField(person.getProperty("note"));
		noteField.setFieldLabel("Note");
		panel.add(noteField);

		panel.setScrollMode(Scroll.AUTOY);

		return panel;
		// this.add(panel);
	}
}
