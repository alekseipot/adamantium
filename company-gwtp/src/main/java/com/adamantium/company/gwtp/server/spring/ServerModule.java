/*
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.adamantium.company.gwtp.server.spring;

import com.adamantium.company.gwtp.server.dispatch.*;
import com.adamantium.company.gwtp.shared.dispatch.*;
import com.adamantium.dao.CompanyDAO;
import com.adamantium.dao.CompanyDaoImpl;
import com.gwtplatform.dispatch.rpc.server.actionvalidator.ActionValidator;
import com.gwtplatform.dispatch.rpc.server.spring.HandlerModule;
import com.gwtplatform.dispatch.rpc.server.spring.LoggerFactoryBean;
import com.gwtplatform.dispatch.rpc.server.spring.actionvalidator.DefaultActionValidator;
import com.gwtplatform.dispatch.rpc.server.spring.configuration.DefaultModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Module which binds the handlers and configurations.
 */
@Configuration
@Import(DefaultModule.class)
@ComponentScan(basePackages = "com.gwtplatform.dispatch.rpc.server.spring")
public class ServerModule extends HandlerModule {
    public ServerModule() {
    }

    @Bean
    public CompanyDAO getCompanyDAO() {
        return new CompanyDaoImpl();
    }

    @Bean
    public SendTextToServerHandler getSendTextToServerHandler() {
        return new SendTextToServerHandler();
    }

    @Bean
    public GetCompanyListHandler getGetCompanyListHandler() {
        return new GetCompanyListHandler();
    }

    @Bean
    public GetAddressHandler getGetAddressHandler() {
        return new GetAddressHandler();
    }

    @Bean
    public GetPhoneHandler getGetPhoneHandler() {
        return new GetPhoneHandler();
    }

    @Bean
    public GetNameHandler GetNameHandler() {
        return new GetNameHandler();
    }

    @Bean
    public GetDescriptionHandler GetDescriptionHandler() {
        return new GetDescriptionHandler();
    }

    @Bean
    public ActionValidator getDefaultActionValidator() {
        return new DefaultActionValidator();
    }

    @Bean
    public LoggerFactoryBean getLogger() {
        Logger logger = Logger.getAnonymousLogger();
        logger.setLevel(Level.FINEST);
        return new LoggerFactoryBean(logger);
    }

    protected void configureHandlers() {
        bindHandler(SendTextToServerAction.class, SendTextToServerHandler.class);
        bindHandler(GetCompanyListAction.class, GetCompanyListHandler.class);
        bindHandler(GetAddressAction.class, GetAddressHandler.class);
        bindHandler(GetPhoneAction.class, GetPhoneHandler.class);
        bindHandler(GetNameAction.class, GetNameHandler.class);
        bindHandler(GetDescriptionAction.class, GetDescriptionHandler.class);
    }
}
