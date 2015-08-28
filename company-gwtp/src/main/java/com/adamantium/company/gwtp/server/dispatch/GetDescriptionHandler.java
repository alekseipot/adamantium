package com.adamantium.company.gwtp.server.dispatch;

import com.adamantium.company.gwtp.shared.dispatch.GetDescriptionAction;
import com.adamantium.company.gwtp.shared.dispatch.GetDescriptionResult;
import com.adamantium.dao.CompanyDAO;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import org.springframework.beans.factory.annotation.Autowired;

public class GetDescriptionHandler extends AbstractActionHandler<GetDescriptionAction, GetDescriptionResult> {

    @Autowired
    private CompanyDAO companyDAO;

    public GetDescriptionHandler() {
        super(GetDescriptionAction.class);
    }

    @Override
    public GetDescriptionResult execute(GetDescriptionAction getDescriptionAction, ExecutionContext executionContext) throws ActionException {
        String description = companyDAO.getDescriptionById(Integer.valueOf(getDescriptionAction.getCompanyId()));
        return new GetDescriptionResult(description);
    }

    @Override
    public void undo(GetDescriptionAction getAddressAction, GetDescriptionResult getDescriptionResult, ExecutionContext executionContext) throws ActionException {
        // Not undoable
    }

    @Override
    public Class<GetDescriptionAction> getActionType() {
        return GetDescriptionAction.class;
    }
}
