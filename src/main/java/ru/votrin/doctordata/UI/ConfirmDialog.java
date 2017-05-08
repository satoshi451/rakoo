package ru.votrin.doctordata.UI;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import ru.votrin.doctordata.model.Patient;

import java.util.Set;

/**
 * Created by wiseman on 09.05.17.
 */
public class ConfirmDialog extends Window {
    private Set<Patient> items;

    public ConfirmDialog() {
        setWidth("200px");
        setHeight("100px");
        setVisible(false);

        Button ok = new Button("ОК");
        ok.addClickListener(this::click);
        setContent(new VerticalLayout(new Label("Посмотреть детальнее?"), ok));



    }

    private void click(Button.ClickEvent clickEvent) {

    }

    public void setItems(Set<Patient> items){
        this.items = items;
    }
    public void show(){
        setVisible(true);
    }
}

