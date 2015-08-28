package com.adamantium.company.gwtp.server.dispatch;

import com.adamantium.company.gwtp.shared.dispatch.GetCompanyListAction;
import com.adamantium.company.gwtp.shared.dispatch.GetCompanyListResult;
import com.adamantium.company.gwtp.shared.dto.CompanyTableInfo;
import com.adamantium.dao.CompanyDAO;
import com.adamantium.entity.Company;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.AbstractActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class GetCompanyListHandler extends AbstractActionHandler<GetCompanyListAction, GetCompanyListResult> {

    @Autowired
    private CompanyDAO companyDAO;

    public GetCompanyListHandler() {
        super(GetCompanyListAction.class);
    }

    @Override
    public GetCompanyListResult execute(GetCompanyListAction getCompanyListAction, ExecutionContext executionContext) throws ActionException {
        List<Company> companyDBList = companyDAO.getAll();
        ArrayList<CompanyTableInfo> companyList = new ArrayList<>();
        for (Company companyFromDB : companyDBList) {
            companyList.add(new CompanyTableInfo(companyFromDB.getId(), companyFromDB.getName()));
        }
        return new GetCompanyListResult(companyList);
    }

    @Override
    public void undo(GetCompanyListAction getCompanyListAction, GetCompanyListResult getCompanyListResult, ExecutionContext executionContext) throws ActionException {
        // Not undoable
    }

    @Override
    public Class<GetCompanyListAction> getActionType() {
        return GetCompanyListAction.class;
    }
}
