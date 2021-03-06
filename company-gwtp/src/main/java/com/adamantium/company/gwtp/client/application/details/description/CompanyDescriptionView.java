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

package com.adamantium.company.gwtp.client.application.details.description;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import javax.inject.Inject;

/**
 * The view implementation for {@link CompanyDescriptionPresenter } .
 */
public class CompanyDescriptionView extends ViewImpl implements CompanyDescriptionPresenter.MyView {

    interface Binder extends UiBinder<Widget, CompanyDescriptionView> {
    }

    @UiField
    HTML companyDescription;

    @Inject
    CompanyDescriptionView(
            Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setCompanyDescription(String companyDescription) {
        this.companyDescription.setHTML(companyDescription);
    }

}
