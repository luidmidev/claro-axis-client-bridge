package ec.com.dudu.claro.axisrestbridge.services;

import ec.com.dudu.claro.axisrestbridge.schemas.Debt;
import ec.com.dudu.claro.axisrestbridge.schemas.DebtDetail;

public interface ClaroClientService {

    Debt getDebt(String identification);

    String getDebtHTML(String identification);

    DebtDetail getDebtDetail(String identification);

    String getDebtDetailHTML(String identification);
}
