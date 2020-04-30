package graphic.usuario;

import database.models.user.TipoUsuarioEnum;
import database.models.user.User;
import graphic.ConsultaGenericaWindow;
import lib.Observer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Function;

public class Cadastro extends JDialog implements Observer<User> {

    JTextField fieldNome, fieldUsuario, fieldSenha;
    JLabel lbldNome, lblUsuario, lblSenha, lblTipo;
    JComboBox comboBoxTipoUsuario;
    JButton btnCadastro;
    JButton btnBuscar;
    JButton btnDeletar;

    private User user;

    private Function onClose;

    public static void main(String[] args) {
        new Cadastro().setVisible(true);
    }

    public Cadastro() {
        setTitle("Usuario");
        setSize(400, 200);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(null);
        criarComponents();
    }

    private void criarComponents() {
        lbldNome = new JLabel("Nome");
        lbldNome.setBounds(0, 5, 195, 10);

        fieldNome = new JTextField();
        fieldNome.setBounds(0, 20, 195, 25);

        lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(200, 5, 195, 10);

        fieldUsuario = new JTextField();
        fieldUsuario.setBounds(200, 20, 195, 25);

        lblSenha = new JLabel("Password");
        lblSenha.setBounds(0, 50, 195, 10);

        fieldSenha = new JTextField();
        fieldSenha.setBounds(0, 65, 195, 25);

        lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(200, 50, 195, 10);

        comboBoxTipoUsuario = new JComboBox();
        comboBoxTipoUsuario.setBounds(200, 65, 195, 25);

        TipoUsuarioEnum[] values = TipoUsuarioEnum.values();
        for (TipoUsuarioEnum value : values) {
            comboBoxTipoUsuario.addItem(value.name());
        }

        btnCadastro = new JButton("Salvar");
        btnCadastro.setBounds(0, 100, 100, 25);
        btnCadastro.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarUsuario();
            }
        });

        Cadastro instance = this;
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(105, 100, 100, 25);
        btnBuscar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultaGenericaWindow<User>(User.class, instance, new String[]{"Nome", "Usuario", "Tipo"}).open();
            }
        });

        btnDeletar = new JButton("Deletar");
        btnDeletar.setBounds(210, 100, 100, 25);
        btnDeletar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirUsuario();
            }
        });

        getContentPane().add(lbldNome);
        getContentPane().add(fieldNome);
        getContentPane().add(lblUsuario);
        getContentPane().add(fieldUsuario);
        getContentPane().add(lblSenha);
        getContentPane().add(fieldSenha);
        getContentPane().add(lblTipo);
        getContentPane().add(btnCadastro);
        getContentPane().add(btnBuscar);
        getContentPane().add(btnDeletar);
        getContentPane().add(comboBoxTipoUsuario);
    }

    private void cadastrarUsuario() {
        User user = new User();
        String nome = fieldNome.getText();
        String senha = fieldSenha.getText();
        String usuario = fieldUsuario.getText();
        String tipo = (String) comboBoxTipoUsuario.getSelectedItem();

        if (nome == null || senha == null || tipo == null) {
            JOptionPane.showMessageDialog(null, "Verifique os campos.");
            return;
        }

        if (this.user != null) {
            user = this.user;
        }

        user.setName(nome);
        user.setUsuario(usuario);
        user.setPassword(senha);
        user.setTipo(TipoUsuarioEnum.valueOf(tipo));
        User userSaved = user.save();

        if (userSaved == null) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar");
            return;
        }

        JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        clearFields();
    }

    private void excluirUsuario() {
        if (user != null && user.getId() != null) {
            user.delete();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            clearFields();
            return;
        }

        JOptionPane.showMessageDialog(null, "Nenhum usuario selecionado.");
    }

    public void clearFields() {
        fieldNome.setText(null);
        fieldSenha.setText(null);
        fieldUsuario.setText(null);

        ComboBoxModel model = comboBoxTipoUsuario.getModel();
        model.setSelectedItem(null);
        comboBoxTipoUsuario.setModel(model);
        setUser(null);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOnClose(Function onClose) {
        this.onClose = onClose;
    }

    @Override
    public void update(User user) {
        setUser(user);

        fieldNome.setText(user.getName());
        fieldSenha.setText(user.getPassword());
        fieldUsuario.setText(user.getUsuario());

        ComboBoxModel model = comboBoxTipoUsuario.getModel();
        model.setSelectedItem(user.getTipo().name());
        comboBoxTipoUsuario.setModel(model);
    }
}
