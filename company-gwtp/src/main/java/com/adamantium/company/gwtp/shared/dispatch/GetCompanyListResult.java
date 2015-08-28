package com.adamantium.company.gwtp.shared.dispatch;

import com.adamantium.company.gwtp.shared.dto.CompanyTableInfo;
import com.gwtplatform.dispatch.rpc.shared.Result;

import java.util.ArrayList;

public class GetCompanyListResult implements Result {

    private ArrayList<CompanyTableInfo> companyList;

    public GetCompanyListResult(ArrayList<CompanyTableInfo> companyList) {
        this.companyList = new ArrayList<>(companyList.size());
        this.companyList.addAll(companyList);
    }

    private GetCompanyListResult() {
    }

    public ArrayList<CompanyTableInfo> getCompanyList() {
        return companyList;
    }
}
