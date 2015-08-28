package com.adamantium.company.gwtp.server.dispatch;

import com.adamantium.company.gwtp.shared.dispatch.GetNameAction;
import com.adamantium.company.gwtp.shared.dispatch.GetNameResult;
import com.adamantium.dao.CompanyDAO;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import org.springframework.beans.factory.annotation.Autowired;

public class GetNameHandler extends AbstractActionHandler<GetNameAction, GetNameResult> {

    @Autowired
    private CompanyDAO companyDAO;

    public GetNameHandler() {
        super(GetNameAction.class);
    }

    @Override
    public GetNameResult execute(GetNameAction getNameAction, ExecutionContext executionContext) throws ActionException {
        String companyName = companyDAO.getNameById(Integer.valueOf(getNameAction.getCompanyId()));
        return new GetNameResult(companyName);
    }

    @Override
    public void undo(GetNameAction getAddressAction, GetNameResult getNameResult, ExecutionContext executionContext) throws ActionException {
        // Not undoable
    }

    @Override
    public Class<GetNameAction> getActionType() {
        return GetNameAction.class;
    }
}
