package database.seeders;

import database.models.user.User;
import database.models.user.UserTypeEnum;
import database.service.Service;

import java.util.List;

public class UserSeeder implements Seeder {
    @Override
    public void run() {
        System.out.println("[Seeder] UserSeeder running.");
        Service<User> userService = new Service<>(User.class);
        List<User> allUsers = userService.findAll();

        if (allUsers.size() == 0) {
            User userAdmin = new User();
            userAdmin.setUsername("admin");
            userAdmin.setName("Administrador");
            userAdmin.setPassword("admin");
            userAdmin.setType(UserTypeEnum.ADMINISTRADOR);
            userAdmin.save();

            User userCadastral = new User();
            userCadastral.setUsername("user_cadastral");
            userCadastral.setName("Cadastral");
            userCadastral.setPassword("cadastral");
            userCadastral.setType(UserTypeEnum.CADASTRAL);
            userCadastral.save();

            User userFinanceiro = new User();
            userFinanceiro.setName("user_financeiro");
            userFinanceiro.setPassword("financeiro");
            userFinanceiro.setType(UserTypeEnum.FINANCEIRO);
            userFinanceiro.save();
        }
        System.out.println("[Seeder] UserSeeder runned.");
    }
}
