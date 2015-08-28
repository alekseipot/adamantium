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

package com.adamantium.company.gwtp.client.application.details.phone;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import javax.inject.Inject;

/**
 * The view implementation for {@link PhonePresenter} .
 */
public class PhoneView extends ViewImpl implements PhonePresenter.MyView {

    interface Binder extends UiBinder<Widget, PhoneView> {
    }

    @UiField
    HTML phone;

    @Inject
    PhoneView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setPhone(String phone) {
        this.phone.setHTML(phone);
    }

}