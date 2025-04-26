package dc.on.persona_predictor.config;

import dc.on.persona_predictor.constants.Constant;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(Constant.API_TITLE)
                        .version(Constant.VERSION)
                        .description(Constant.DESCRIPTION));
    }
}
