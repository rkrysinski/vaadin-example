package com.krycha.vaadin.example.gui.elements.content;

import com.vaadin.data.Property;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;

public class IntSelector extends CustomField<String> {
	private static final long serialVersionUID = 4382983233965763460L;
	private TextField count = new TextField();
	private NativeButton plus = new NativeButton("+");
	private NativeButton minus = new NativeButton("-");

	public IntSelector() {
		super();

		count.setImmediate(true);
		count.setWidth("46px");

		count.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = -6714245151014674054L;

			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				setValue(count.getValue(), false);
			}
		});

		plus.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -5841572658386411636L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					int num = Integer.parseInt(count.getValue());
					setValue(Integer.toString(++num));
				} catch (NumberFormatException e) {
					// ignore
				}
			}
		});

		minus.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -5841572658386411636L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					int num = Integer.parseInt(count.getValue());
					setValue(Integer.toString(--num));
				} catch (NumberFormatException e) {
					// ignore
				}
			}
		});
	}


    @SuppressWarnings("rawtypes")
	@Override
    public void setPropertyDataSource(Property newDataSource) {
        super.setPropertyDataSource(newDataSource);
        setCount(((Integer) newDataSource.getValue()).toString());
    }

    @Override
    public void setValue(String newValue) throws ReadOnlyException,
            Converter.ConversionException {
        super.setValue(newValue);
        setCount(newValue);
    }

	private void setCount(String newValue) {
		count.setValue(newValue != null ? newValue : "0");
	}


	@Override
	protected Component initContent() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.addComponent(minus);
        layout.setComponentAlignment(minus, Alignment.MIDDLE_RIGHT);
        layout.addComponent(count);
        layout.addComponent(plus);
        layout.setComponentAlignment(plus, Alignment.MIDDLE_LEFT);
        return layout;
	}

	@Override
	public Class<? extends String> getType() {
		return String.class;
	}

}
