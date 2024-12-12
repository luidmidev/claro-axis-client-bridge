package ec.com.dudu.claro.axisrestbridge.controllers;

import ec.com.dudu.claro.axisrestbridge.services.ClaroClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/claro/axis/clients")
public class ClaroClientServiceController {
    //
    private final ClaroClientService service;

    @GetMapping("/html/debt")
    public ResponseEntity<String> getDebt(@RequestParam String identification) {
        var html = service.getDebtHTML(identification);
        return ResponseEntity.status(200).contentType(MediaType.TEXT_HTML).body(html);
    }

    @GetMapping("/html/debt-detail")
    public ResponseEntity<String> getDebtDetail(@RequestParam String identification) {
        var html = service.getDebtDetailHTML(identification);
        return ResponseEntity.status(200).contentType(MediaType.TEXT_HTML).body(html);
    }
}
