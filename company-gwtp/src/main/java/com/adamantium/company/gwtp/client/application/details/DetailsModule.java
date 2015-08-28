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

package com.adamantium.company.gwtp.client.application.details;

import com.adamantium.company.gwtp.client.application.details.address.AddressPresenter;
import com.adamantium.company.gwtp.client.application.details.address.AddressView;
import com.adamantium.company.gwtp.client.application.details.company.CompanyDetailsPresenter;
import com.adamantium.company.gwtp.client.application.details.company.CompanyDetailsSampleView;
import com.adamantium.company.gwtp.client.application.details.contacts.ContactsPresenter;
import com.adamantium.company.gwtp.client.application.details.contacts.ContactsView;
import com.adamantium.company.gwtp.client.application.details.description.CompanyDescriptionPresenter;
import com.adamantium.company.gwtp.client.application.details.description.CompanyDescriptionView;
import com.adamantium.company.gwtp.client.application.details.name.CompanyNamePresenter;
import com.adamantium.company.gwtp.client.application.details.name.CompanyNameView;
import com.adamantium.company.gwtp.client.application.details.phone.PhonePresenter;
import com.adamantium.company.gwtp.client.application.details.phone.PhoneView;
import com.adamantium.company.gwtp.client.application.details.ui.UiModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author Brandon Donnelson
 */
public class DetailsModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new UiModule());

        // Applicaiton Presenters
        bindPresenter(DetailsPresenter.class, DetailsPresenter.DetailsView.class, DetailsView.class,
                DetailsPresenter.MyProxy.class);
        bindPresenter(CompanyDetailsPresenter.class, CompanyDetailsPresenter.MyView.class,
                CompanyDetailsSampleView.class, CompanyDetailsPresenter.MyProxy.class);
        bindPresenter(CompanyNamePresenter.class, CompanyNamePresenter.MyView.class,
                CompanyNameView.class, CompanyNamePresenter.MyProxy.class);
        bindPresenter(CompanyDescriptionPresenter.class, CompanyDescriptionPresenter.MyView.class,
                CompanyDescriptionView.class, CompanyDescriptionPresenter.MyProxy.class);
        bindPresenter(ContactsPresenter.class, ContactsPresenter.MyView.class,
                ContactsView.class, ContactsPresenter.MyProxy.class);
        bindPresenter(AddressPresenter.class, AddressPresenter.MyView.class,
                AddressView.class, AddressPresenter.MyProxy.class);
        bindPresenter(PhonePresenter.class, PhonePresenter.MyView.class,
                PhoneView.class, PhonePresenter.MyProxy.class);
    }
}
