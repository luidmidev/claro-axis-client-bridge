package ec.com.dudu.claro.axisrestbridge.shared.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.*;

import java.net.URI;
import java.util.Map;
import java.util.Set;

public abstract class RestOperationsBridge implements RestOperations {

    protected abstract RestOperations getRestOperations();

    @Override
    public <T> T getForObject(@NotNull String url, @NotNull Class<T> responseType, Object @NotNull ... uriVariables) throws RestClientException {
        return getRestOperations().getForObject(url, responseType, uriVariables);
    }

    @Override
    public <T> T getForObject(@NotNull String url, @NotNull Class<T> responseType, @NotNull Map<String, ?> uriVariables) throws RestClientException {
        return getRestOperations().getForObject(url, responseType, uriVariables);
    }

    @Override
    public <T> T getForObject(@NotNull URI url, @NotNull Class<T> responseType) throws RestClientException {
        return getRestOperations().getForObject(url, responseType);
    }

    @Override
    public <T> @NotNull ResponseEntity<T> getForEntity(@NotNull String url, @NotNull Class<T> responseType, Object @NotNull ... uriVariables) throws RestClientException {
        return getRestOperations().getForEntity(url, responseType, uriVariables);
    }

    @Override
    public <T> @NotNull ResponseEntity<T> getForEntity(@NotNull String url, @NotNull Class<T> responseType, @NotNull Map<String, ?> uriVariables) throws RestClientException {
        return getRestOperations().getForEntity(url, responseType, uriVariables);
    }

    @Override
    public <T> @NotNull ResponseEntity<T> getForEntity(@NotNull URI url, @NotNull Class<T> responseType) throws RestClientException {
        return getRestOperations().getForEntity(url, responseType);
    }

    @Override
    public @NotNull HttpHeaders headForHeaders(@NotNull String url, Object @NotNull ... uriVariables) throws RestClientException {
        return getRestOperations().headForHeaders(url, uriVariables);
    }

    @Override
    public @NotNull HttpHeaders headForHeaders(@NotNull String url, @NotNull Map<String, ?> uriVariables) throws RestClientException {
        return getRestOperations().headForHeaders(url, uriVariables);
    }

    @Override
    public @NotNull HttpHeaders headForHeaders(@NotNull URI url) throws RestClientException {
        return getRestOperations().headForHeaders(url);
    }

    @Override
    public URI postForLocation(@NotNull String url, Object request, Object @NotNull ... uriVariables) throws RestClientException {
        return getRestOperations().postForLocation(url, request, uriVariables);
    }

    @Override
    public URI postForLocation(@NotNull String url, Object request, @NotNull Map<String, ?> uriVariables) throws RestClientException {
        return getRestOperations().postForLocation(url, request, uriVariables);
    }

    @Override
    public URI postForLocation(@NotNull URI url, Object request) throws RestClientException {
        return getRestOperations().postForLocation(url, request);
    }

    @Override
    public <T> T postForObject(@NotNull String url, Object request, @NotNull Class<T> responseType, Object @NotNull ... uriVariables) throws RestClientException {
        return getRestOperations().postForObject(url, request, responseType, uriVariables);
    }

    @Override
    public <T> T postForObject(@NotNull String url, Object request, @NotNull Class<T> responseType, @NotNull Map<String, ?> uriVariables) throws RestClientException {
        return getRestOperations().postForObject(url, request, responseType, uriVariables);
    }

    @Override
    public <T> T postForObject(@NotNull URI url, Object request, @NotNull Class<T> responseType) throws RestClientException {
        return getRestOperations().postForObject(url, request, responseType);
    }

    @Override
    public <T> @NotNull ResponseEntity<T> postForEntity(@NotNull String url, Object request, @NotNull Class<T> responseType, Object @NotNull ... uriVariables) throws RestClientException {
        return getRestOperations().postForEntity(url, request, responseType, uriVariables);
    }

    @Override
    public <T> @NotNull ResponseEntity<T> postForEntity(@NotNull String url, Object request, @NotNull Class<T> responseType, @NotNull Map<String, ?> uriVariables) throws RestClientException {
        return getRestOperations().postForEntity(url, request, responseType, uriVariables);
    }

    @Override
    public <T> @NotNull ResponseEntity<T> postForEntity(@NotNull URI url, Object request, @NotNull Class<T> responseType) throws RestClientException {
        return getRestOperations().postForEntity(url, request, responseType);
    }

    @Override
    public void put(@NotNull String url, Object request, Object @NotNull ... uriVariables) throws RestClientException {
        getRestOperations().put(url, request, uriVariables);
    }

    @Override
    public void put(@NotNull String url, Object request, @NotNull Map<String, ?> uriVariables) throws RestClientException {
        getRestOperations().put(url, request, uriVariables);
    }

    @Override
    public void put(@NotNull URI url, Object request) throws RestClientException {
        getRestOperations().put(url, request);
    }

    @Override
    public <T> T patchForObject(@NotNull String url, Object request, @NotNull Class<T> responseType, Object @NotNull ... uriVariables) throws RestClientException {
        return getRestOperations().patchForObject(url, request, responseType, uriVariables);
    }

    @Override
    public <T> T patchForObject(@NotNull String url, Object request, @NotNull Class<T> responseType, @NotNull Map<String, ?> uriVariables) throws RestClientException {
        return getRestOperations().patchForObject(url, request, responseType, uriVariables);
    }

    @Override
    public <T> T patchForObject(@NotNull URI url, Object request, @NotNull Class<T> responseType) throws RestClientException {
        return getRestOperations().patchForObject(url, request, responseType);
    }

    @Override
    public void delete(@NotNull String url, Object @NotNull ... uriVariables) throws RestClientException {
        getRestOperations().delete(url, uriVariables);
    }

    @Override
    public void delete(@NotNull String url, @NotNull Map<String, ?> uriVariables) throws RestClientException {
        getRestOperations().delete(url, uriVariables);
    }

    @Override
    public void delete(@NotNull URI url) throws RestClientException {
        getRestOperations().delete(url);
    }

    @Override
    public @NotNull Set<HttpMethod> optionsForAllow(@NotNull String url, Object @NotNull ... uriVariables) throws RestClientException {
        return getRestOperations().optionsForAllow(url, uriVariables);
    }

    @Override
    public @NotNull Set<HttpMethod> optionsForAllow(@NotNull String url, @NotNull Map<String, ?> uriVariables) throws RestClientException {
        return getRestOperations().optionsForAllow(url, uriVariables);
    }

    @Override
    public @NotNull Set<HttpMethod> optionsForAllow(@NotNull URI url) throws RestClientException {
        return getRestOperations().optionsForAllow(url);
    }

    @Override
    public <T> @NotNull ResponseEntity<T> exchange(@NotNull String url, @NotNull HttpMethod method, HttpEntity<?> requestEntity, @NotNull Class<T> responseType, Object @NotNull ... uriVariables) throws RestClientException {
        return getRestOperations().exchange(url, method, requestEntity, responseType, uriVariables);
    }

    @Override
    public <T> @NotNull ResponseEntity<T> exchange(@NotNull String url, @NotNull HttpMethod method, HttpEntity<?> requestEntity, @NotNull Class<T> responseType, @NotNull Map<String, ?> uriVariables) throws RestClientException {
        return getRestOperations().exchange(url, method, requestEntity, responseType, uriVariables);
    }

    @Override
    public <T> @NotNull ResponseEntity<T> exchange(@NotNull URI url, @NotNull HttpMethod method, HttpEntity<?> requestEntity, @NotNull Class<T> responseType) throws RestClientException {
        return getRestOperations().exchange(url, method, requestEntity, responseType);
    }

    @Override
    public <T> @NotNull ResponseEntity<T> exchange(@NotNull String url, @NotNull HttpMethod method, HttpEntity<?> requestEntity, @NotNull ParameterizedTypeReference<T> responseType, Object @NotNull ... uriVariables) throws RestClientException {
        return getRestOperations().exchange(url, method, requestEntity, responseType, uriVariables);
    }

    @Override
    public <T> @NotNull ResponseEntity<T> exchange(@NotNull String url, @NotNull HttpMethod method, HttpEntity<?> requestEntity, @NotNull ParameterizedTypeReference<T> responseType, @NotNull Map<String, ?> uriVariables) throws RestClientException {
        return getRestOperations().exchange(url, method, requestEntity, responseType, uriVariables);
    }

    @Override
    public <T> @NotNull ResponseEntity<T> exchange(@NotNull URI url, @NotNull HttpMethod method, HttpEntity<?> requestEntity, @NotNull ParameterizedTypeReference<T> responseType) throws RestClientException {
        return getRestOperations().exchange(url, method, requestEntity, responseType);
    }

    @Override
    public <T> @NotNull ResponseEntity<T> exchange(@NotNull RequestEntity<?> requestEntity, @NotNull Class<T> responseType) throws RestClientException {
        return getRestOperations().exchange(requestEntity, responseType);
    }

    @Override
    public <T> @NotNull ResponseEntity<T> exchange(@NotNull RequestEntity<?> requestEntity, @NotNull ParameterizedTypeReference<T> responseType) throws RestClientException {
        return getRestOperations().exchange(requestEntity, responseType);
    }

    @Override
    public <T> T execute(@NotNull String uriTemplate, @NotNull HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor, Object @NotNull ... uriVariables) throws RestClientException {
        return getRestOperations().execute(uriTemplate, method, requestCallback, responseExtractor, uriVariables);
    }

    @Override
    public <T> T execute(@NotNull String uriTemplate, @NotNull HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor, @NotNull Map<String, ?> uriVariables) throws RestClientException {
        return getRestOperations().execute(uriTemplate, method, requestCallback, responseExtractor, uriVariables);
    }

    @Override
    public <T> T execute(@NotNull URI url, @NotNull HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws RestClientException {
        return getRestOperations().execute(url, method, requestCallback, responseExtractor);
    }

}
