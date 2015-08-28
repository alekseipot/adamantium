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

import com.adamantium.company.gwtp.shared.dto.CompanyTableInfo;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import java.util.List;

public class ListView extends ViewWithUiHandlers<ListUiHandlers> implements ListPresenter.MyView {

    interface Binder extends UiBinder<Widget, ListView> {
    }

    @UiField
    Button detailsButton;

    @UiField(provided = true)
    CellTable<CompanyTableInfo> cellTable;

    @UiField(provided = true)
    SimplePager pager;

    @Inject
    ListView(Binder uiBinder) {
        // Create a CellTable.
        cellTable = new CellTable<CompanyTableInfo>();
        buildColumns(cellTable);
        // Add a selection model to handle user selection.
        final SingleSelectionModel<CompanyTableInfo> selectionModel = new SingleSelectionModel<CompanyTableInfo>();
        cellTable.setSelectionModel(selectionModel);
//        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
//            public void onSelectionChange(SelectionChangeEvent event) {
//                CompanyTableInfo selected = selectionModel.getSelectedObject();
//                if (selected != null) {
//                    Window.alert("You selected: " + selected.getName());
//                }
//            }
//        });
        buildPager();
        initWidget(uiBinder.createAndBindUi(this));
    }

    private void buildPager() {
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, false, 0, true);
        pager.setDisplay(cellTable);
    }

    private void buildColumns(CellTable<CompanyTableInfo> cellTable) {
        // Create name column.
        TextColumn<CompanyTableInfo> idColumn = new TextColumn<CompanyTableInfo>() {
            @Override
            public String getValue(CompanyTableInfo company) {
                return String.valueOf(company.getId());
            }
        };

        // Create address column.
        TextColumn<CompanyTableInfo> nameColumn = new TextColumn<CompanyTableInfo>() {
            @Override
            public String getValue(CompanyTableInfo company) {
                return company.getName();
            }
        };

        // Add the columns.
        cellTable.addColumn(idColumn, "Id");
        cellTable.addColumn(nameColumn, "Name");
    }

    @UiHandler("detailsButton")
    void showDetails(ClickEvent event) {
        CompanyTableInfo selectedCompany = (CompanyTableInfo) ((SingleSelectionModel) cellTable.getSelectionModel())
                .getSelectedObject();
        if (selectedCompany != null) {
            getUiHandlers().showDetails(selectedCompany.getId());
        } else {
            Window.alert("Please, select any company before");
        }
    }


    @Override
    public void setRowData(List<CompanyTableInfo> companyTableInfoList) {
        this.cellTable.setRowCount(companyTableInfoList.size(), true);
        this.cellTable.setRowData(0, companyTableInfoList);
    }
}
