/**
 * LoginForm.java
 * in Mini-App
 */
package org.net9.minipie.app.client;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author LeeThree
 * 
 */
public class LoginForm extends Window {
	public static final EventType LOGGED_IN = new EventType();
	private static final String USERNAME_REGEX = "[a-zA-Z][0-9a-zA-Z_]{4,14}";
	private static final String PASSWORD_REGEX = "(.{6,20})";

	private final TextField<String> nameField = new TextField<String>();
	private final TextField<String> passField = new TextField<String>();
	private Button loginButton;

	private final MiniAppServiceAsync service;

	public LoginForm() {
		service = Registry.get(MiniApp.SERVICE);
		service.isLoggedIn(new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				MessageBox box = MessageBox.alert("Network Failure",
						"Sorry, the service is temporarily unavaliable.", null);
				box.setClosable(false);
			}

			@Override
			public void onSuccess(Boolean loggedIn) {
				if (loggedIn)
					fireEvent(LOGGED_IN);
			}

		});
		init();
	}

	private void init() {
		this.setHeading("Login");
		this.setFrame(true);

		this.setShadow(true);
		this.setClosable(false);
		this.setLayout(new FitLayout());
		this.setSize(350, 160);
		this.setResizable(false);
		this.setModal(true);

		FormPanel panel = new FormPanel();
		panel.setHeading("Please login to continue");
		panel.setFrame(false);

		nameField.setFieldLabel("Username");
		nameField.setAllowBlank(false);
		nameField.setAutoValidate(true);
		nameField.setMinLength(5);
		nameField.setMaxLength(15);
		nameField
				.getMessages()
				.setRegexText(
						"Only digits/letters/underlines are allowed. Start with a letter.");
		nameField.setRegex(USERNAME_REGEX);
		panel.add(nameField);

		passField.setAllowBlank(false);
		passField.setFieldLabel("Password");
		passField.setAutoValidate(true);
		passField.setMinLength(6);
		passField.setMaxLength(20);
		passField.setPassword(true);
		passField.setRegex(PASSWORD_REGEX);
		panel.add(passField);

		loginButton = new Button("Login");
		this.setButtonAlign(HorizontalAlignment.RIGHT);

		this.addButton(loginButton);
		this.add(panel);

		loginButton.focus();

		loginButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				login();
			};
		});
	}

	private void login() {
		if (nameField.validate() && passField.validate()) {
			String username = nameField.getValue();
			String password = passField.getValue();
			service.login(username, password, new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					Info.display("Login Failed", caught.getMessage());
					nameField.enable();
					passField.enable();
					loginButton.enable();
					nameField.markInvalid("Incorrect username or password");
					passField.markInvalid("Incorrect username or password");
				}

				@Override
				public void onSuccess(Void v) {
					fireEvent(LOGGED_IN);
				}

			});
			nameField.disable();
			passField.disable();
			loginButton.disable();
		}
	}
}
