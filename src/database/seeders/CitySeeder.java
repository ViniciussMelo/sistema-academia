package database.seeders;

import database.connection.HibernateUtil;
import database.models.address.City;
import database.models.address.State;
import database.service.Service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class CitySeeder implements Seeder {

    @Override
    public void run() {
        Service<City> cityService = new Service<City>(City.class);
        City city = cityService.find(1);

        if (city != null) {
            return;
        }

        System.out.println("[Seeder] CitySeeder running.");
        try {
            List<String> querys = Files.readAllLines(new File("./resource/sqls/city_query.sql").toPath());
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            querys.forEach(query -> {
                session.createSQLQuery(query).executeUpdate();
            });
            session.getTransaction().commit();
            session.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[Seeder] CitySeeder error: " + e.getMessage());
        }
        System.out.println("[Seeder] CitySeeder runned.");
    }
}
