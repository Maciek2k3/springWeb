package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyDetails companyDetails;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/task_front/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("message","New Card created");
        context.setVariable("preview", "Trello app - new card added");
        context.setVariable("company_name",companyDetails.getAppName());
        context.setVariable("company_ownerName",companyDetails.getOwnerName());
        context.setVariable("companyOwnerSuername", companyDetails.getOwnerSurname());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

}

