package dc.on.persona_predictor.datasource;


import dc.on.persona_predictor.constants.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class DriverManagerDataSource{
    @Value(Constant.PROP_ENV)
    private static Environment env;

    @Value(Constant.PROP_DATASOURCE_DRIVER)
    private static String driver;

    @Value(Constant.PROP_DATASOURCE_URL)
    private static String url;

    @Bean(name = Constant.BEAN_POSTGRESQL)
    @Primary
    public static DataSource postgreSQLDataSource()
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

    @Bean(name=Constant.BEAN_JDBCTEMPLATE)
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(postgreSQLDataSource());
    }
}