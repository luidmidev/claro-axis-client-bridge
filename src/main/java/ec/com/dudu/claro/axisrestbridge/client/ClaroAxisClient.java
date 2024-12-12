package ec.com.dudu.claro.axisrestbridge.client;


import ec.com.dudu.claro.axisrestbridge.shared.utils.RestOperationsBridge;
import io.github.luidmidev.springframework.web.problemdetails.ApiError;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.jsoup.Jsoup;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.regex.Pattern;

@Slf4j
@Component
@SuppressWarnings("java:S1075")
@RequiredArgsConstructor
public class ClaroAxisClient extends RestOperationsBridge {

    private static final String BASE_URL = "https://portalcrmdas.claro.com.ec";
    private static final String AXIS_PATH = "/axis";
    private static final String LOGIN_PAGE_PATH = AXIS_PATH + "/login/gef_login.jsp";
    private static final String LOGIN_ACTION_PATH = AXIS_PATH + "/login/gee_emp_login.jsp";


    private final ClaroAxisClientProperties properties;
    private RestTemplate client;

    @PostConstruct
    public void init() {
        client = factoryAuthenticatedClient();
    }

    protected RestTemplate factoryAuthenticatedClient() {
        var httpClient = HttpClients.custom()
                .setDefaultCookieStore(new BasicCookieStore())
                .build();

        var newClient = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
        newClient.setUriTemplateHandler(new DefaultUriBuilderFactory(BASE_URL));
        authenticateClient(newClient);
        return newClient;
    }

    private void authenticateClient(RestTemplate client) {
        try {
            getLoginPageAndCookies(client);
            var hiddenFormUrl = postLoginForm(client);
            postHiddenLoginForm(client, hiddenFormUrl);
        } catch (Exception e) {
            log.error("Error en la autenticación del cliente", e);
            throw ApiError
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .title("Error en la autenticación del cliente")
                    .detail("Ocurrió un error al autenticar el cliente en el portal de Claro: " + e.getMessage());

        }
    }

    private void getLoginPageAndCookies(RestTemplate client) {
        safeExchangeBody(client, LOGIN_PAGE_PATH, HttpMethod.GET, null, String.class);
    }

    private String postLoginForm(RestTemplate client) {
        var formLogin = getLoginForm();

        var loginResponse = safeExchangeBody(client, LOGIN_ACTION_PATH, HttpMethod.POST, formLogin, String.class);
        if (loginResponse.contains("Los  Datos ingresados no son correctos") || loginResponse.contains("Usuario o contraseña incorrectos.")) {
            throw new IllegalStateException("Credenciales incorrectas, respuesta obtenida: " + loginResponse);
        }

        if (loginResponse.contains("Usuario Bloqueado por intentos fallidos en el Sistema")) {
            throw new IllegalStateException("El usuario está bloqueado por intentos fallidos en el sistema, comunicar al administrador");
        }

        return extractHiddenFormUrl(loginResponse);
    }

    private HttpEntity<LinkedMultiValueMap<String, String>> getLoginForm() {

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        var form = new LinkedMultiValueMap<String, String>();
        form.add("user", properties.getUsername());
        form.add("password", properties.getPassword());
        form.add("request", "");
        form.add("numIntentos", "0");
        form.add("hostPiranha", "null");
        form.add("puertoPiranha", "null");
        form.add("180CF6FFF840A6375CC256C3B8149AAB", "142F4F2F8CF01D2D8FEBDC55A4B754A7");
        form.add("loginAxis", "LOGIN");

        return new HttpEntity<>(form, headers);
    }

    private void postHiddenLoginForm(RestTemplate client, String hiddenFormUrl) {
        var hiddenFormPage = safeExchangeBody(client, AXIS_PATH + "/" + hiddenFormUrl, HttpMethod.GET, null, String.class);
        var formData = getHiddenLoginForm(hiddenFormPage);
        var hiddenLoginAction = extractHiddenLoginAction(hiddenFormPage);
        safeExchangeBody(client, hiddenLoginAction, HttpMethod.POST, formData, String.class);
    }


    private static HttpEntity<LinkedMultiValueMap<String, String>> getHiddenLoginForm(String hiddenFormPage) {

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        var document = Jsoup.parse(hiddenFormPage);
        var inputs = document.select("input[type=hidden]");

        var formData = new LinkedMultiValueMap<String, String>();
        for (var input : inputs) {
            var name = input.attr("name");
            var value = input.attr("value");
            formData.add(name, value);
        }

        return new HttpEntity<>(formData, headers);
    }

    private String extractHiddenFormUrl(String response) {
        var regex = "parent\\.open\\(['\"]([^'\"]+)['\"]";

        var matcher = Pattern.compile(regex).matcher(response);

        if (!matcher.find()) {
            throw new IllegalStateException("No se encontró la URL del formulario oculto post login, respuesta obtenida: " + response);
        }

        var preString = matcher.group(1).replace("parent.open('", "");
        return "/login/" + preString.substring(0, preString.length() - 1);
    }

    private static String extractHiddenLoginAction(String response) {
        var regex = "document\\.ge_login\\.action\\s*=\\s*['\"]([^'\"]+)['\"];";
        var matcher = Pattern.compile(regex).matcher(response);
        if (!matcher.find()) {
            throw new IllegalStateException("No se encontró la URL de la fase 2 del login, respuesta obtenida: " + response);
        }

        var preString = matcher.group(1).replace("document.ge_login.action = '", "");
        return preString.substring(0, preString.length() - 2);

    }


    private static <T> T safeExchangeBody(RestTemplate client, String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables) {
        var response = client.exchange(url, method, requestEntity, responseType, uriVariables);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new IllegalStateException("Error en la llamada " + method + " a " + url + ": " + response.getStatusCode());
        }

        var body = response.getBody();
        if (body == null) {
            throw new IllegalStateException("Respuesta vacía en la llamada " + method + " a " + url);
        }
        return body;
    }

    @Override
    protected RestOperations getRestOperations() {
        return client;
    }
}
