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

package com.adamantium.company.gwtp.client.application.details.contacts;

import com.adamantium.company.gwtp.client.application.details.DetailsConstants;
import com.adamantium.company.gwtp.client.application.details.DetailsPresenter;
import com.adamantium.company.gwtp.client.place.NameTokens;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.RequestTabsHandler;
import com.gwtplatform.mvp.client.TabView;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.RequestTabs;
import com.gwtplatform.mvp.client.annotations.TabInfo;
import com.gwtplatform.mvp.client.proxy.NonLeafTabContentProxy;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import javax.inject.Inject;

/**
 * A sample {@link com.gwtplatform.mvp.client.TabContainerPresenter TabContainerPresenter} appearing as a tab within
 * {@link ContactsPresenter} and itself containing two tabs. When the tab for {@link ContactsPresenter}
 * is clicked, them
 * {@link ContactsPresenter} is displayed.
 * <p/>
 * It demonstrates the option 2 described in {@link com.gwtplatform.mvp.client.annotations.TabInfo},
 * together with the use of the {@code nameToken} parameter
 * of {@code @TabInfo} to specify which place to show when the tab is clicked.
 */
public class ContactsPresenter extends ContactsPresenterBase<ContactsPresenter.MyView,
        ContactsPresenter.MyProxy> {
    /**
     * {@link ContactsPresenter}'s proxy.
     */
    @ProxyCodeSplit
    public interface MyProxy extends NonLeafTabContentProxy<ContactsPresenter> {
    }

    /**
     * {@link ContactsPresenter}'s view.
     */
    public interface MyView extends TabView {
    }

    /**
     * This will be the event sent to our "unknown" child presenters, in order for them to register their tabs.
     */
    @RequestTabs
    public static final Type<RequestTabsHandler> SLOT_RequestTabs = new Type<>();

    private final PlaceManager placeManager;

    @Inject
    ContactsPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            PlaceManager placeManager) {
        super(eventBus, view, proxy, SLOT_RequestTabs, DetailsPresenter.SLOT_SetTabContent);

        this.placeManager = placeManager;
    }

    @TabInfo(container = DetailsPresenter.class, priority = 1, // The first tab in the main page
            nameToken = NameTokens.addressDetails)
    // Go to HomeNewsPresenter when clicked
    static String getTabLabel(DetailsConstants constants) {
        return constants.contacts();
    }

    @Override
    public void onReset() {
        super.onReset();

        MyProxy proxy = getProxy();
        proxy.changeTab(proxy.getTabData(), placeManager.getCurrentPlaceRequest().getNameToken());
    }
}
