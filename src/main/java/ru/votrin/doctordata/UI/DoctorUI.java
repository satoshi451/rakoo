package ru.votrin.doctordata.UI;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

/**
 * Created by wiseman on 08.04.17.
 */


@SpringUI
@Theme("valo")
public class DoctorUI extends UI{

    @Override
    protected void init(VaadinRequest request) {
        setContent(new Button("Click me", e -> Notification.show("Hello Spring+Vaadin user!")));
    }
}

