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

import com.adamantium.company.gwtp.client.place.NameTokens;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.*;
import com.gwtplatform.mvp.client.annotations.ChangeTab;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.RequestTabs;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

/**
 * The main {@link com.gwtplatform.mvp.client.Presenter} of the application. It contains a number of tabs allowing
 * access to the various parts of the application. Tabs are refreshed whenever the current user's privileges change in
 * order to hide areas that cannot be accessed.
 */
public class DetailsPresenter
        extends TabContainerPresenter<DetailsPresenter.DetailsView, DetailsPresenter.MyProxy> implements DetailsUiHandlers{

    /**
     * {@link DetailsPresenter}'s proxy.
     */
    @ProxyStandard
    public interface MyProxy extends Proxy<DetailsPresenter> {
    }

    /**
     * {@link DetailsPresenter}'s view.
     */
    public interface DetailsView extends TabView, HasUiHandlers<DetailsUiHandlers> {
        void refreshTabs();
    }

    /**
     * This will be the event sent to our "unknown" child presenters, in order for them to register their tabs.
     */
    @RequestTabs
    public static final Type<RequestTabsHandler> SLOT_RequestTabs = new Type<>();

    /**
     * Fired by child proxie's when their tab content is changed.
     */
    @ChangeTab
    public static final Type<ChangeTabHandler> SLOT_ChangeTab = new Type<>();

    /**
     * Use this in leaf presenters, inside their {@link #revealInParent} method.
     */
    @ContentSlot
    public static final Type<RevealContentHandler<?>> SLOT_SetTabContent = new Type<>();

    @Inject
    DetailsPresenter(EventBus eventBus,
                     DetailsView view,
                     MyProxy proxy) {
        super(eventBus, view, proxy, SLOT_SetTabContent, SLOT_RequestTabs, SLOT_ChangeTab, RevealType.Root);
    }

    @Override
    public void goToCompanyList() {
        Window.open("#list", "_self", null);
//        PlaceRequest responsePlaceRequest = new PlaceRequest.Builder()
//                .nameToken(NameTokens.list)
//                .build();
//        placeManager.revealPlace(responsePlaceRequest);
    }

}
