package ru.votrin.doctordata.UI;


import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by wiseman on 10.04.17.
 */
class DoctorMenu extends MenuBar {
    private MenuBar horizontalMenuBar;

    public DoctorMenu() {
        super();

        //addStyleName(ValoTheme.MENUBAR_BORDERLESS);

        addItem("item1", null);
        addItem("item1", null);
        addItem("item1", null);
        addItem("item1", null);
//        addComponent(horizontalMenuBar);

    }

}
