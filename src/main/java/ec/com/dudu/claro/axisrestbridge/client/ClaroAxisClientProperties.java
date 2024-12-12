package ec.com.dudu.claro.axisrestbridge.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "claro.axis.client")
public class ClaroAxisClientProperties {
    private String username;
    private String password;
}
