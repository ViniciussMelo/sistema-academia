import database.connection.HibernateUtil;
import graphic.Login;

public class Main {

    public static void main(final String[] args) {
        HibernateUtil.init();
        HibernateUtil.migrate();

        Login loginWindow = new Login();
        loginWindow.toogle();
    }
}
