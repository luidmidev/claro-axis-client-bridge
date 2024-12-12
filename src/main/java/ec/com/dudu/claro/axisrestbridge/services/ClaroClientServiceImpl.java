package ec.com.dudu.claro.axisrestbridge.services;

import ec.com.dudu.claro.axisrestbridge.client.ClaroAxisClient;
import ec.com.dudu.claro.axisrestbridge.schemas.Debt;
import ec.com.dudu.claro.axisrestbridge.schemas.DebtDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;


@Slf4j
@Service
@RequiredArgsConstructor
public class ClaroClientServiceImpl implements ClaroClientService {

    private static final String URI = "/axis/paginas/cliente";

    private final ClaroAxisClient client;

    @Override
    public Debt getDebt(String identification) {
        var response = getDebtHTML(identification);
        log.info("getDebt::Response: {}", response);
        return null;
    }

    @Override
    public String getDebtHTML(String identification) {
        var entity = getFormIdentification(identification);
        return client.exchange(URI + "/clp_resumen_detalle_deuda.jsp", HttpMethod.POST, entity, String.class).getBody();
    }

    @Override
    public DebtDetail getDebtDetail(String identification) {
        var response = getDebtDetailHTML(identification);
        log.info("getDebtDetail::Response: {}", response);
        return null;
    }

    @Override
    public String getDebtDetailHTML(String identification) {
        var entity = getFormIdentification(identification);
        return client.exchange(URI + "/clp_detalle_deuda.jsp", HttpMethod.POST, entity, String.class).getBody();
    }

    private static HttpEntity<LinkedMultiValueMap<String, String>> getFormIdentification(String identification) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        var form = new LinkedMultiValueMap<String, String>();
        form.add("identificacion", identification);

        return new HttpEntity<>(form, headers);
    }

}

