package com.krycha.vaadin.example.gui.elements.content;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.krycha.vaadin.example.entity.Customer;
import com.krycha.vaadin.example.entity.Measurement;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
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
import org.joda.time.DateTime;

public abstract class FormWindow<T> extends Window implements Button.ClickListener {

	private static final long serialVersionUID = 2527105328312484996L;
	private static final Logger LOG = Logger.getLogger(FormWindow.class.getName());
	private Class<T> type;
	private Button saveButton;
	private Button cancelButton;
	private ErrorLabel error = new ErrorLabel("", ContentMode.HTML);
	protected T bean;
	protected BeanFieldGroup<T> binder;

	public FormWindow(T bean, Class<T> type) {
		super();
		this.bean = bean;
		this.type = type;

		binder = new BeanFieldGroup<T>(this.type);
		binder.setFieldFactory(new DefaultFieldGroupFieldFactory() {
			private static final long serialVersionUID = 8661439259768671014L;

			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public <F extends Field> F createField(Class<?> type,
					Class<F> fieldType) {
				if (type.isAssignableFrom(Customer.class)) {
					return (F) new CustomerSelector();
				} else if (type.isAssignableFrom(Measurement.class)) {
					return (F) new MeasurementSelector();
				} else if (type.isAssignableFrom(DateTime.class)) {
					return (F) new DateSelector();
				} else if (type.isAssignableFrom(Integer.class)) {
					return (F) new IntSelector();
				}
				return super.createField(type, fieldType);
			}
		});
		binder.setBuffered(false);
		binder.setItemDataSource(this.bean);
		FormLayout layout = new FormLayout();
		layout.setImmediate(true);
		layout.setMargin(true);
		for (Field<?> field: getFields()) {
			layout.addComponent(field);
		}

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
		setCaption(getCaption());
		setStyleName("entity");
	}

	public abstract List<Field<?>> getFields();

	public abstract String getCaption();

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == saveButton) {
			if (binder.isValid()) {
				try {
					binder.commit();
					error.clearError();
					fireEvent(new EditorSavedEvent<T>(this, bean));
					Notification.show("Success!");
					close();
				} catch (CommitException e) {
					showErrorMessage();
				} catch (Exception e) {
					String errorMessage = null;
//					TODO: check what GAE throws in case where keys are duplicated
//					Throwable rootCause = e.getCause();
//					while (rootCause.getCause() != null) {
//						rootCause = rootCause.getCause();
//					}
//					if (rootCause instanceof StandardException) {
//						StandardException sqlException = (StandardException)rootCause;
//						if (SQLState.LANG_DUPLICATE_KEY_CONSTRAINT.equals(sqlException.getSQLState())) {
//							errorMessage = "Error: "
//									+ bean.getClass().getSimpleName().toLowerCase()
//									+ " already exists";
//						}
//					}
					if (errorMessage == null) {
						errorMessage = "Internal error: please check logs for details";
						LOG.log(Level.WARNING, "Error committing form for bean: " + bean.toString(), e);
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
