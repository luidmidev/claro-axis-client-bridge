package ec.com.dudu.claro.axisrestbridge.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Debt extends DebtBase {
    private Double overdueDebts;
    private Double outstandingDebts;

    @JsonProperty("totalDebt")
    public Double getTotalDebt() {
        return overdueDebts + outstandingDebts;
    }
}
