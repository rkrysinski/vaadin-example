package com.krycha.vaadin.example.gui.elements;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class Header extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private static final long serialVersionUID = -9146079595482387345L;
	@AutoGenerated
	private HorizontalLayout mainLayout;
	@AutoGenerated
	private Label HeaderLabel;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public Header() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}

	@AutoGenerated
	private HorizontalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new HorizontalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// HeaderLabel
		HeaderLabel = new Label();
		HeaderLabel.setImmediate(false);
		HeaderLabel.setWidth("-1px");
		HeaderLabel.setHeight("-1px");
		HeaderLabel.setValue("Header");
		mainLayout.addComponent(HeaderLabel);

		return mainLayout;
	}

}
