package com.example.admin;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.crudui.crud.impl.GridBasedCrudComponent;

@SpringUI
@Theme(ValoTheme.THEME_NAME)
public class VaadinUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        GridBasedCrudComponent<Company> crud = new GridBasedCrudComponent<>(Company.class);
        crud.getGrid().setColumns("name", "phone", "email", "address");
        crud.getCrudFormFactory().setVisiblePropertyIds("name", "phone", "email", "address");
        crud.setOperations(
                () -> Services.companyService().findAll().getContent(),
                company -> Services.companyService().add(company),
                company -> Services.companyService().update(company.getId(), company),
                company -> Services.companyService().delete(company.getId())
        );

        setContent(crud);
    }

}
