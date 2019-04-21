package com.lbs.bilkent;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@SpringUI(path = "user")
public class UserUI extends UI {

    private List<User> userList;
    private Grid<User> userGrid;

    @Autowired
    private UserService userService;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        VerticalLayout contentLayout = new VerticalLayout();

        FormLayout formLayout = new FormLayout();

        TextField usernameField = new TextField("Username");

        DateField birthDateField = new DateField("Birth Date");

        Button saveButton = new Button("save");
        saveButton.addClickListener(e-> {
            if(!usernameField.getValue().equals("")){
                userService.addUser(usernameField.getValue(), birthDateField.getValue());
                userList = userService.getUsers();
                userGrid.setDataProvider(new ListDataProvider<>(userList));//.getDataProvider().refreshAll();
                usernameField.setValue("");
                birthDateField.setValue(null);
            }
        });

        formLayout.addComponents(usernameField, birthDateField, saveButton);
        formLayout.setWidth(500, Unit.PIXELS);

        contentLayout.addComponent(formLayout);
        contentLayout.setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);

        userList = new ArrayList<>();
        userList = userService.getUsers();

        userGrid = new Grid<>();
        userGrid.addColumn(e-> e.getUsername()).setCaption("Username");
        userGrid.addColumn(e->e.getBirthdate()).setCaption("BirthDate");
        userGrid.setDataProvider(new ListDataProvider<>(userList));
        userGrid.setSizeFull();

        contentLayout.addComponent(userGrid);

        setContent(contentLayout);

    }
}
