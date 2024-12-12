package ec.com.dudu.claro.axisrestbridge.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClaroClientServiceTest {

    @Autowired
    ClaroClientService service;

    @Test
    void getDebt() {
        var html = service.getDebtHTML("1720656790");
        System.out.println("HTML: ");
        System.out.println(html);
        Assertions.assertTrue(true);
    }

    @Test
    void getDebtDetail() {
        service.getDebtDetail("1720656790");
        Assertions.assertTrue(true);
    }
}