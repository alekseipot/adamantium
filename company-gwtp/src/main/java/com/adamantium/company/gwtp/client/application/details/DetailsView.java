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

import com.adamantium.company.gwtp.client.application.details.ui.linkmenu.LinkMenu;
import com.adamantium.company.gwtp.client.application.details.ui.tabs.RoundTabPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.Tab;
import com.gwtplatform.mvp.client.TabData;
import com.gwtplatform.mvp.client.ViewImpl;

import javax.inject.Inject;

public class DetailsView extends ViewImpl implements DetailsPresenter.DetailsView {

    private DetailsUiHandlers uiHandlers;

    interface Binder extends UiBinder<Widget, DetailsView> {
    }

    @UiField(provided = true)
    RoundTabPanel tabPanel;
    @UiField(provided = true)
    LinkMenu linkMenu;
    @UiField
    Button goToList;


    @Inject
    DetailsView(Binder uiBinder,
                RoundTabPanel tabPanel,
                LinkMenu linkMenu) {
        this.tabPanel = tabPanel;
        this.linkMenu = linkMenu;

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setUiHandlers(DetailsUiHandlers detailsUiHandlers) {
        this.uiHandlers = detailsUiHandlers;
    }

    public DetailsUiHandlers getUiHandlers() {
        return uiHandlers;
    }

    @Override
    public Tab addTab(TabData tabData, String historyToken) {
        return tabPanel.addTab(tabData, historyToken);
    }

    @Override
    public void removeTab(Tab tab) {
        tabPanel.removeTab(tab);
    }

    @Override
    public void removeTabs() {
        tabPanel.removeTabs();
    }

    @Override
    public void setActiveTab(Tab tab) {
        tabPanel.setActiveTab(tab);
    }

    @Override
    public void changeTab(Tab tab, TabData tabData, String historyToken) {
        tabPanel.changeTab(tab, tabData, historyToken);
    }

    @Override
    public void setInSlot(Object slot, IsWidget content) {
        if (slot == DetailsPresenter.SLOT_SetTabContent) {
            tabPanel.setPanelContent(content);
        } else {
            super.setInSlot(slot, content);
        }
    }

    @Override
    public void refreshTabs() {
        tabPanel.refreshTabs();
    }

    @UiHandler("goToList")
    void onDemo(ClickEvent event) {
        getUiHandlers().goToCompanyList();
    }
}
