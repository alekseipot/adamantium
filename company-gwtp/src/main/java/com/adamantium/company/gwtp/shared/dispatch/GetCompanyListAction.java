package com.adamantium.company.gwtp.shared.dispatch;

import com.gwtplatform.dispatch.rpc.shared.ActionImpl;

public class GetCompanyListAction extends ActionImpl<GetCompanyListResult> {

    public GetCompanyListAction() {
    }

    @Override
    public boolean isSecured() {
        return false;
    }
}
