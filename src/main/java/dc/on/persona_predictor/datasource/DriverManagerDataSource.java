package dc.on.persona_predictor.datasource;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import javax.sql.DataSource;

public class DriverManagerDataSource{
    @Value("${env}")
    private static Environment env;

    @Value("${spring.datasource.driver")
    private static String driver;

    @Value("${spring.datasource.url}")
    private static String url;

    @Bean(name ="PostgreSQL")
    @Primary
    public static DataSource PostgreSQLDataSource()
    {

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driver);
        dataSourceBuilder.url(url);
        return dataSourceBuilder.build();
    }

    //@Override
    public void setEnvironment( final Environment environment) {
        env=environment;
    }
}