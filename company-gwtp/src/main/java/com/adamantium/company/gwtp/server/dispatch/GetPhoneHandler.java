package com.adamantium.company.gwtp.server.dispatch;

import com.adamantium.company.gwtp.shared.dispatch.GetPhoneAction;
import com.adamantium.company.gwtp.shared.dispatch.GetPhoneResult;
import com.adamantium.dao.CompanyDAO;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import org.springframework.beans.factory.annotation.Autowired;

public class GetPhoneHandler extends AbstractActionHandler<GetPhoneAction, GetPhoneResult> {

    @Autowired
    private CompanyDAO companyDAO;

    public GetPhoneHandler() {
        super(GetPhoneAction.class);
    }

    @Override
    public GetPhoneResult execute(GetPhoneAction getPhoneAction, ExecutionContext executionContext) throws ActionException {
        String companyPhone = companyDAO.getPhoneById(Integer.valueOf(getPhoneAction.getCompanyId()));
        return new GetPhoneResult(companyPhone);
    }

    @Override
    public void undo(GetPhoneAction getPhoneAction, GetPhoneResult getPhoneResult, ExecutionContext executionContext) throws ActionException {
        // Not undoable
    }

    @Override
    public Class<GetPhoneAction> getActionType() {
        return GetPhoneAction.class;
    }
}
