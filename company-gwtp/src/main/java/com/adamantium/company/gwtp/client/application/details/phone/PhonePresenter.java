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

import com.adamantium.company.gwtp.client.application.details.contacts.ContactsPresenter;
import com.adamantium.company.gwtp.client.place.NameTokens;
import com.adamantium.company.gwtp.client.place.TokenParameters;
import com.adamantium.company.gwtp.shared.dispatch.GetPhoneAction;
import com.adamantium.company.gwtp.shared.dispatch.GetPhoneResult;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.TabInfo;
import com.gwtplatform.mvp.client.proxy.TabContentProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import javax.inject.Inject;

public class PhonePresenter extends Presenter<PhonePresenter.MyView, PhonePresenter.MyProxy> {
    private String companyId = "";
    private final DispatchAsync dispatcher;
    private Storage stock = Storage.getLocalStorageIfSupported();

    /**
     * {@link PhonePresenter}'s proxy.
     */
    @ProxyCodeSplit
    @NameToken(NameTokens.phoneDetails)
    @TabInfo(container = ContactsPresenter.class, label = "Phone", priority = 1)
    // The second tab in the home tab
    public interface MyProxy extends TabContentProxyPlace<PhonePresenter> {
    }

    /**
     * {@link PhonePresenter}'s view.
     */
    public interface MyView extends View {
        void setPhone(String phone);
    }

    @Inject
    PhonePresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            DispatchAsync dispatcher) {
        super(eventBus, view, proxy, ContactsPresenter.SLOT_SetTabContent);
        this.dispatcher = dispatcher;
    }


    @Override
    public void prepareFromRequest(PlaceRequest request) {
        super.prepareFromRequest(request);
        if (request.getParameter(TokenParameters.SELECTED_COMPANY_ID, null) != null) {
            companyId = request.getParameter(TokenParameters.SELECTED_COMPANY_ID, null);
            stock.setItem("companyId", companyId);
        } else {
            companyId = stock.getItem("companyId");
        }
    }

    @Override
    protected void onReset() {
        super.onReset();

        dispatcher.execute(new GetPhoneAction(companyId), new AsyncCallback<GetPhoneResult>() {
            @Override
            public void onFailure(Throwable caught) {
                getView().setPhone("");
                Window.alert("Error: unable to get company phone from server");
            }

            @Override
            public void onSuccess(GetPhoneResult result) {
                if (Cookies.getCookie("JSESSIONID") == null) {
                    System.out.println("Set security cookie.");
                    Cookies.setCookie("JSESSIONID", "JSESSIONID");
                }
                getView().setPhone(result.getPhone());
            }
        });
    }
}
