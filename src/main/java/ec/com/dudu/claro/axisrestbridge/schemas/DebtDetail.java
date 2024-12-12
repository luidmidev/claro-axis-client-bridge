package ec.com.dudu.claro.axisrestbridge.schemas;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DebtDetail extends DebtBase {

    private List<DebtAccount> accounts = new ArrayList<>();

    public void addAccount(DebtAccount account) {
        accounts.add(account);
    }

    @Data
    public static class DebtAccount {
        private String accountNumber;
        private boolean active;
        private List<DebtData> debts = new ArrayList<>();

        public void addDebt(DebtData debt) {
            debts.add(debt);
        }
    }

    @Data
    public static class DebtData {
        private String type;
        private String value;
        private String age;
    }
}
