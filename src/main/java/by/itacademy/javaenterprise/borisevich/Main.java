package by.itacademy.javaenterprise.borisevich;


import by.itacademy.javaenterprise.borisevich.mappedsuperclass.exception.DAOException;
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
import java.sql.SQLException;

@Slf4j
public class Main {

    public static void main(String[] args) throws DAOException, SQLException, LiquibaseException {
        DataSource ds = new DriverManagerDataSource("jdbc:mysql://localhost:3306/demodb?useSSL=false",
              "xxx", "xxx");
        JdbcConnection jdbcConnection = new JdbcConnection(ds.getConnection());
        Database database = DatabaseFactory.getInstance().
                findCorrectDatabaseImplementation(jdbcConnection);

        Liquibase liquibase = new liquibase.Liquibase("liquibase/db/changelog.xml"
                , new ClassLoaderResourceAccessor(), database);

        liquibase.update(new Contexts());
    }
}
