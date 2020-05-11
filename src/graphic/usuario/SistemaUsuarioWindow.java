package graphic.usuario;

import database.models.user.User;
import database.service.Service;
import lib.Observer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SistemaUsuarioWindow extends JDialog implements Observer<User> {

    private JButton btnCadastro;
    private JTable tblUsuarios;
    JButton buttonCadastrar;
    JTable table;

    public static void main(String[] args) {
        try {
            new SistemaUsuarioWindow().setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SistemaUsuarioWindow() throws IOException {
        setTitle("Usuários");
        setSize(500, 400);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(null);
        criarComponentes();
        adicionarActionListener();
    }

    private void criarComponentes() throws IOException {
        criaTabela();

        //Botão
        buttonCadastrar = new JButton("Cadastrar");
        buttonCadastrar.setBounds(5, 5, 125, 25);
        getContentPane().add(buttonCadastrar);
    }

    private void criaTabela() throws IOException {
        table = new JTable();
        JScrollPane js = new JScrollPane(table);
        js.setBounds(0, 40, 500, 360);
        getContentPane().add(js);
        configuraTabela();
        addUsuarios();
    }

    private void configuraTabela() throws IOException {
        String[] colunas = {"ID", "Nome", "Usuario", "Tipo usuário"};
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (int i = 0; i < colunas.length; i++) {
            modelo.addColumn(colunas[i]);
        }

        table.setModel(modelo);
    }

    private void adicionarActionListener() {
        buttonCadastrar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cadastro cadastro = new Cadastro();
                cadastro.setOnClose((obj) -> {
                    clearTable();
                    addUsuarios();
                    return null;
                });
                cadastro.setVisible(true);
            }
        });
    }

    private void addUsuarios() {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        Service<User> userService = new Service<>(User.class);
        List<User> allUsers = userService.findAll();

        for (User user : allUsers) {
            modelo.addRow(new Object[]{user.getId(), user.getName(), user.getUsername(), user.getType().name()});
        }
    }

    private void clearTable() {
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }

    @Override
    public void update(User user) {
        JOptionPane.showMessageDialog(null, user.getName());
    }
}
