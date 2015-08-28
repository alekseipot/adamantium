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

package com.adamantium.company.gwtp.client.application.list;

import com.adamantium.company.gwtp.client.place.NameTokens;
import com.adamantium.company.gwtp.client.place.TokenParameters;
import com.adamantium.company.gwtp.shared.dispatch.GetCompanyListAction;
import com.adamantium.company.gwtp.shared.dispatch.GetCompanyListResult;
import com.adamantium.company.gwtp.shared.dto.CompanyTableInfo;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import javax.inject.Inject;
import java.util.List;

public class ListPresenter extends Presenter<ListPresenter.MyView, ListPresenter.MyProxy>
        implements ListUiHandlers {
    @ProxyCodeSplit
    @NameToken(NameTokens.list)
    public interface MyProxy extends ProxyPlace<ListPresenter> {
    }

    public interface MyView extends View, HasUiHandlers<ListUiHandlers> {
        void setRowData(List<CompanyTableInfo> companyTableInfoList);
    }

    private final DispatchAsync dispatcher;
    private final PlaceManager placeManager;

    private String textToServer;

    @Inject
    ListPresenter(EventBus eventBus,
                  MyView view,
                  MyProxy proxy,
                  PlaceManager placeManager,
                  DispatchAsync dispatcher) {
        super(eventBus, view, proxy, RevealType.Root);

        this.placeManager = placeManager;
        this.dispatcher = dispatcher;

        getView().setUiHandlers(this);
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        super.prepareFromRequest(request);

        textToServer = request.getParameter(TokenParameters.TEXT_TO_SERVER, null);
    }

    @Override
    public void onClose() {
        PlaceRequest homePlaceRequest = new PlaceRequest.Builder().nameToken(NameTokens.home).build();
        placeManager.revealPlace(homePlaceRequest);
    }

    @Override
    public void showDetails(int id) {
        PlaceRequest responsePlaceRequest = new PlaceRequest.Builder()
                .nameToken(NameTokens.nameDetails)
                .with(TokenParameters.SELECTED_COMPANY_ID, String.valueOf(id))
                .build();
        placeManager.revealPlace(responsePlaceRequest);
    }

    @Override
    protected void onReset() {
        super.onReset();

//        List<CompanyTableInfo> Company = new ArrayList<>();
//        Company.add(new CompanyTableInfo(1, "name1"));
//        Company.add(new CompanyTableInfo(2, "name2"));
//        Company.add(new CompanyTableInfo(3, "name3"));
//        Company.add(new CompanyTableInfo(4, "name4"));
//        getView().setRowData(Company);

//        getView().setCompanyName(textToServer);
//        getView().setServerResponse("Waiting for response...");

        dispatcher.execute(new GetCompanyListAction(), new AsyncCallback<GetCompanyListResult>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error: unable to get company list from server");
            }

            @Override
            public void onSuccess(GetCompanyListResult result) {
                if (Cookies.getCookie("JSESSIONID") == null) {
                    System.out.println("Set security cookie.");
                    Cookies.setCookie("JSESSIONID", "JSESSIONID");
                }
                getView().setRowData(result.getCompanyList());
            }
        });
    }
}
