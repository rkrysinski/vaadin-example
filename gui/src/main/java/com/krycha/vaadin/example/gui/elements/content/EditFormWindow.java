package com.krycha.vaadin.example.gui.elements.content;

import java.io.Serializable;
import java.lang.reflect.Method;

import com.krycha.vaadin.example.entity.Customer;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Window;
import org.apache.derby.iapi.error.StandardException;
import org.apache.derby.iapi.reference.SQLState;
import org.apache.log4j.Logger;

public class EditFormWindow extends Window implements Button.ClickListener {

	private static final long serialVersionUID = -7674224855345526078L;
	private static final Logger LOG = Logger.getLogger(EditFormWindow.class);
	private Button saveButton;
	private Button cancelButton;
	private ErrorLabel error = new ErrorLabel("", ContentMode.HTML);
	private Customer bean;
	private BeanFieldGroup<Customer> binder;

	public EditFormWindow(Customer bean) {
		super();
		this.bean = bean;

		binder = new BeanFieldGroup<Customer>(Customer.class);
		binder.setBuffered(false);
		binder.setItemDataSource(this.bean);
		FormLayout layout = new FormLayout();
		layout.setMargin(true);
		layout.addComponent(binder.buildAndBind("Short Name", "shortName"));
		layout.addComponent(binder.buildAndBind("Description", "description"));

		error.setVisible(false);
		layout.addComponent(error);


		HorizontalLayout footer = new HorizontalLayout();
		saveButton = new Button("Save", this);
		cancelButton = new Button("Cancel", this);
		footer.addComponent(saveButton);
		footer.addComponent(cancelButton);

		layout.addComponent(footer);

		setSizeUndefined();
		center();
		setModal(true);
		setContent(layout);
		setCaption(buildCaption());
		setStyleName("entity");
	}

	private String buildCaption() {
        return String.format("%s", bean.getShortName());
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == saveButton) {
			if (binder.isValid()) {
				try {
					binder.commit();
					error.clearError();
					fireEvent(new EditorSavedEvent<Customer>(this, bean));
					Notification.show("Success!");
					close();
				} catch (CommitException e) {
					showErrorMessage();
				} catch (Exception e) {
					String errorMessage = null;
					Throwable rootCause = e.getCause();
					while (rootCause.getCause() != null) {
						rootCause = rootCause.getCause();
					}
					if (rootCause instanceof StandardException) {
						StandardException sqlException = (StandardException)rootCause;
						if (SQLState.LANG_DUPLICATE_KEY_CONSTRAINT.equals(sqlException.getSQLState())) {
							errorMessage = "Error: "
									+ bean.getClass().getSimpleName().toLowerCase()
									+ " already exists";
						}
					}
					if (errorMessage == null) {
						errorMessage = "Internal error: please check logs for details";
						LOG.error("Error committing form for bean: " + bean.toString(), e);
					}
					error.setError(errorMessage);
				}
			} else {
				showErrorMessage();
			}
		} else if (event.getButton() == cancelButton) {
			binder.discard();
			close();
		}
	}

    private void showErrorMessage() {
		for (Field<?> field : binder.getFields()) {
			ErrorMessage errMsg = ((AbstractField<?>) field).getErrorMessage();
			if (errMsg != null) {
				error.setError("Error in " + field.getCaption() + ": "
						+ errMsg.getFormattedHtmlMessage());
				break;
			}
		}
	}

	public void addListener(EditorSavedListener<?> listener) {
        try {
            Method method = EditorSavedListener.class.getDeclaredMethod(
                    "editorSaved", new Class[] { EditorSavedEvent.class });
            addListener(EditorSavedEvent.class, listener, method);
        } catch (final java.lang.NoSuchMethodException e) {
            // This should never happen
            throw new java.lang.RuntimeException(
                    "Internal error, editor saved method not found");
        }
    }

    public void removeListener(EditorSavedListener<?> listener) {
        removeListener(EditorSavedEvent.class, listener);
    }

    public static class EditorSavedEvent<T> extends Component.Event {

		private static final long serialVersionUID = -7855123463236539594L;
		private T bean;

        public EditorSavedEvent(Component source, T bean) {
            super(source);
            this.bean = bean;
        }

        public T getSavedBean() {
            return bean;
        }
    }

    public interface EditorSavedListener<T> extends Serializable {
        public void editorSaved(EditorSavedEvent<T> event);
    }

    public static class ErrorLabel extends Label {

		private static final long serialVersionUID = 2119299267850318384L;

		public ErrorLabel() {
            setVisible(false);
        }

		public ErrorLabel(String content, ContentMode contentMode) {
			super(content, contentMode);
		}

		public void setError(String error) {
            setValue(error);
            setComponentError(new UserError("error"));
            setVisible(true);
        }

        public void clearError() {
            setValue(null);
            setComponentError(new UserError("error"));
            setVisible(false);
        }
    }

}
