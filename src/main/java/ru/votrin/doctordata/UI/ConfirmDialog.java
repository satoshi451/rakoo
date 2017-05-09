package ru.votrin.doctordata.UI;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;


/**
 * Created by wiseman on 09.05.17.
 */
public class ConfirmDialog extends Window {
    private final Button ok;


    public ConfirmDialog() {
        setWidth("200px");
        setHeight("100px");
        setVisible(false);

        ok = new Button("ОК");
        setContent(new VerticalLayout(new Label("Посмотреть детальнее?"), ok));
    }

    public void show(){
        setVisible(true);
    }
    public Button getReportButton(){
        return ok;
    }
}

