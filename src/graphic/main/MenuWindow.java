package graphic.main;

import application.ApplicationContext;
import database.models.user.User;
import database.models.user.UserTypeEnum;
import graphic.authentication.Login;
import graphic.student.StudentForm;
import graphic.user.UserForm;

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
    private JMenuItem cadastroAluno;
    private JMenuItem cadastroPeriodo;

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
        setTitle("Menu - Academia Braço Direito");
        setSize(800, 600);
        setLocationRelativeTo(null);

        menuCriar();

        JOptionPane.showMessageDialog(null, "Academia DEFINE (Usuário: " + ApplicationContext.getUser().getName() + ")");
    }

    private void criarMenuCadastro() {
        cadastro = new JMenu("Cadastro");
        cadastro.setMnemonic('C');

        cadastroUsuario = new JMenuItem("Usuário");
        cadastroUsuario.setMnemonic('U');
        cadastro.add(cadastroUsuario);
        cadastroUsuario.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserForm().setVisible(true);
            }
        });

        cadastroModalidade = new JMenuItem("Modalidade");
        cadastroModalidade.setMnemonic('M');
        cadastro.add(cadastroModalidade);

        cadastroAluno = new JMenuItem("Aluno");
        cadastroAluno.setMnemonic('A');
        cadastro.add(cadastroAluno);
        cadastroAluno.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentForm().setVisible(true);
            }
        });

        cadastroPeriodo = new JMenuItem("Periodo");
        cadastroPeriodo.setMnemonic('P');
        cadastro.add(cadastroPeriodo);
        cadastroPeriodo.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentForm().setVisible(true);
            }
        });

        bar.add(cadastro);
    }

    private void criarMenuModalidade() {
        modalidade = new JMenu("Modalidade");
        modalidade.setMnemonic('m');

        jiujitsu = new JMenuItem("Jiu Jitsu");
        jiujitsu.setMnemonic('j');
        modalidade.add(jiujitsu);

        karate = new JMenuItem("Karate");
        karate.setMnemonic('k');
        modalidade.add(karate);

        bar.add(modalidade);
    }

    private void criarMenuAdmin() {
        sistema = new JMenu("Sistema");
        sistema.setMnemonic('S');

        sistemaLogout = new JMenuItem(itemLogout);
        sistemaLogout.setMnemonic('l');
        sistema.add(sistemaLogout);

        sistemaSair = new JMenuItem(itemSair);
        sistemaSair.setMnemonic('a');
        sistema.add(sistemaSair);

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
        bar.add(horarios);
        bar.add(produtos);
    }

    private void menuCriar() {
        User user = ApplicationContext.getUser();

        bar = new JMenuBar();

        if (user.getType() == UserTypeEnum.ADMINISTRADOR) {
            criarMenuAdmin();
            criarMenuCadastro();
            criarMenuModalidade();
        } else if (user.getType() == UserTypeEnum.CADASTRAL) {
            criarMenuCadastro();
        } else if (user.getType() == UserTypeEnum.FINANCEIRO) {
            criarMenuModalidade();
        }

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
