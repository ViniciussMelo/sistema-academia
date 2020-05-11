package database.connection;

import database.models.user.UserTypeEnum;
import database.models.user.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import database.service.Service;

import java.util.List;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
        }

        return sessionFactory;
    }

    public static void init() {
        getSessionFactory();
    }

    public static void migrate() {
        Service<User> userService = new Service<>(User.class);
        List<User> allUsers = userService.findAll();

        if (allUsers.size() == 0) {
            User userAdmin = new User();
            userAdmin.setName("admin");
            userAdmin.setPassword("admin");
            userAdmin.setType(UserTypeEnum.ADMINISTRADOR);
            userAdmin.save();

            User userCadastral = new User();
            userCadastral.setName("user_cadastral");
            userCadastral.setPassword("cadastral");
            userCadastral.setType(UserTypeEnum.CADASTRAL);
            userCadastral.save();

            User userFinanceiro = new User();
            userFinanceiro.setName("user_financeiro");
            userFinanceiro.setPassword("financeiro");
            userFinanceiro.setType(UserTypeEnum.FINANCEIRO);
            userFinanceiro.save();
        }
    }
}
