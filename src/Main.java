import application.ApplicationContext;
import connection.HibernateUtil;
import models.user.User;
import pages.Login;

public class Main {

    public static void main(final String[] args) {
        HibernateUtil.init();

        Login loginWindow = new Login();
        loginWindow
                .onLogin((usuario, senha) -> {
                    User user = User.login(usuario, senha);
                    ApplicationContext.setUser(user);
                    if (user == null) {
                        throw new RuntimeException("Erro ao fazer login.");
                    }
                })
                .toogle();
    }
}
