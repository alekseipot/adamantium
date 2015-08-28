package com.adamantium.company.gwtp.server.dispatch;

import com.adamantium.company.gwtp.shared.dispatch.GetAddressAction;
import com.adamantium.company.gwtp.shared.dispatch.GetAddressResult;
import com.adamantium.dao.CompanyDAO;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import org.springframework.beans.factory.annotation.Autowired;

public class GetAddressHandler extends AbstractActionHandler<GetAddressAction, GetAddressResult> {

    @Autowired
    private CompanyDAO companyDAO;

    public GetAddressHandler() {
        super(GetAddressAction.class);
    }

    @Override
    public GetAddressResult execute(GetAddressAction getAddressAction, ExecutionContext executionContext) throws ActionException {
        String companyAddress = companyDAO.getAddressById(Integer.valueOf(getAddressAction.getCompanyId()));
        return new GetAddressResult(companyAddress);
    }

    @Override
    public void undo(GetAddressAction getAddressAction, GetAddressResult getAddressResult, ExecutionContext executionContext) throws ActionException {
        // Not undoable
    }

    @Override
    public Class<GetAddressAction> getActionType() {
        return GetAddressAction.class;
    }
}
