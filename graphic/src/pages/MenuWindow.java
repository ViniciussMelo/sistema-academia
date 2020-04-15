package pages;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MenuWindow extends JFrame {

    private JMenuBar bar;
    private JMenu sistema;
    private JMenuItem sistemaLogout;
    private JMenuItem sistemaSair;

    private JMenu cadastro;
    private JMenuItem cadastroUsuario;
    private JMenuItem cadastroEmpresa;
    private JMenuItem cadastroModalidade;

    private JMenu modalidade;
    private JMenuItem jiujitsu;
    private JMenuItem karate;

    private JMenu horarios;
    private JMenuItem matutino;
    private JMenuItem vespertino;
    private JMenuItem noturno;

    private JMenu produtos;


    private JDesktopPane desktop;

    public static void main(String[] args) {
        new MenuWindow().setVisible(true);
    }

    public MenuWindow() {
        /*User user = ApplicationContext.getUser();
        user.setName("Teste");*/

        setTitle("Menu - Academia Braço Direito");
        setSize(800, 600);
        setLocationRelativeTo(null);
        /*
        if (tipo == TipoUsuarioEnum.ADMINISTRADOR) {
            criarMenuSistena();
            criarMenuCadastro();
            criarMenuModalidade();
            criarMenuHorarios();
            criarMenuProdutos();
        }

        if (tipo == TipoUsuarioEnum.CADASTRAL) {
            criarMenuCadastro();
        }

        if (tipo == TipoUsuarioEnum.FINANCEIRO) {
            criarMenuModalidade();
        }*/

        menuCriar();

        JOptionPane.showMessageDialog(null, "Academia Bombad�o (Usu�rio: )");
        //JOptionPane.showMessageDialog(null, "Academia Bombad�o (Usu�rio: " + user.getName() + ")");
    }

    private void menuCriar() {
        bar = new JMenuBar();

        sistema = new JMenu("Sistema");
            sistema.setMnemonic('S');

            sistemaLogout = new JMenuItem(itemLogout);
            sistemaLogout.setMnemonic('l');
            sistema.add(sistemaLogout);

            sistemaSair = new JMenuItem(itemSair);
            sistemaSair.setMnemonic('a');
            sistema.add(sistemaSair);

        cadastro = new JMenu("Cadastro");
            cadastro.setMnemonic('C');

            cadastroUsuario = new JMenuItem("Usu�rio");
            cadastroUsuario.setMnemonic('U');
            cadastro.add(cadastroUsuario);

            cadastroEmpresa = new JMenuItem("Empresa");
            cadastroEmpresa.setMnemonic('E');
            cadastro.add(cadastroEmpresa);

            cadastroModalidade = new JMenuItem("Modalidade");
            cadastroModalidade.setMnemonic('M');
            cadastro.add(cadastroModalidade);


        modalidade = new JMenu("Modalidade");
            modalidade.setMnemonic('m');

            jiujitsu = new JMenuItem("Jiu Jitsu");
            jiujitsu.setMnemonic('j');
            modalidade.add(jiujitsu);

            karate = new JMenuItem("Karate");
            karate.setMnemonic('k');
            modalidade.add(karate);

        horarios = new JMenu("Hor�rios");
            horarios.setMnemonic('h');

            matutino = new JMenuItem("Matutino");
            matutino.setMnemonic('m');
            horarios.add(matutino);

            vespertino = new JMenuItem("Vespertino");
            vespertino.setMnemonic('v');
            horarios.add(vespertino);

            noturno = new JMenuItem("Noturno");
            noturno.setMnemonic('n');
            horarios.add(noturno);


        produtos = new JMenu("Produtos");
        produtos.setMnemonic('p');


        bar.add(sistema);
        bar.add(cadastro);
        bar.add(modalidade);
        bar.add(horarios);
        bar.add(produtos);

        setJMenuBar(bar);

        desktop = new JDesktopPane();
        getContentPane().add(desktop);

    }

    //--A��o do Item Logout

    Action itemLogout = new AbstractAction("Logout") {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new Login().setVisible(true);
        }
    };


    //--A��o do Item Sair

    Action itemSair = new AbstractAction("Sair") {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            System.exit(EXIT_ON_CLOSE);

        }
    };


}
