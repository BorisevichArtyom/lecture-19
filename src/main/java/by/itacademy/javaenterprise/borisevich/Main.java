package by.itacademy.javaenterprise.borisevich;


import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class Main {

    public static void main(String[] args) throws  SQLException, LiquibaseException {

        Properties props = new Properties();

        try (InputStream in =Main.class.getClassLoader().getResourceAsStream("liquibase/liquibase.properties")) {
            props.load(in);
        } catch (IOException e) {
            log.info("Error with reading file liquibase.properties.", e);
        }

        DataSource ds = new DriverManagerDataSource(props.getProperty("url"),
                props.getProperty("username"), props.getProperty("password"));
        JdbcConnection jdbcConnection = new JdbcConnection(ds.getConnection());
        Database database = DatabaseFactory.getInstance().
                findCorrectDatabaseImplementation(jdbcConnection);

        Liquibase liquibase = new liquibase.Liquibase("liquibase/db/changelog.xml"
                , new ClassLoaderResourceAccessor(), database);

        liquibase.update(new Contexts());
    }
}
