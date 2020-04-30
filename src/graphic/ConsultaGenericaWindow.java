package graphic;

import database.models.Model;
import database.models.user.User;
import database.service.Service;
import graphic.usuario.Cadastro;
import graphic.usuario.SistemaUsuarioWindow;
import lib.Observable;
import lib.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsultaGenericaWindow<T extends Model<T>> extends JDialog implements Observable {
    private JScrollPane scroll;
    private JTable table;
    private DefaultTableModel model;

    private Service<T> service;
    private List<Observer> observers;

    private JButton buttonOk;

    private List<T> allObjects;

    public static void main(String[] args) throws IOException {
        ConsultaGenericaWindow<User> consulta = new ConsultaGenericaWindow<>(User.class, new Cadastro(), new String[]{"Nome", "Usuario", "Tipo"});
        consulta.open();
    }

    public ConsultaGenericaWindow(final Class<T> clazz, final Observer observer, final String[] colunas) {
        this.service = new Service<>(clazz);
        this.observers = new ArrayList<>();

        addOberver(observer);

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (int i = 0; i < colunas.length; i++) {
            model.addColumn(colunas[i]);
        }

        setTitle("Usuários");
        setSize(500, 400);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(null);

        table = new JTable(model);
        scroll = new JScrollPane(table);
        scroll.setBounds(0, 0, 500, 300);

        buttonOk = new JButton("Ok");
        buttonOk.setBounds(0, 305, 100, 25);
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyAllObervers();
            }
        });

        getContentPane().add(scroll);
        getContentPane().add(buttonOk);
    }

    private void buscarDados() {
        allObjects = this.service.findAll();

        if (allObjects.isEmpty()) {
            // Error
            return;
        }

        for (T t : allObjects) {
            model.addRow(t.getResult());
        }
    }


    public void open() {
        buscarDados();
        setVisible(true);
    }

    @Override
    public void addOberver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeOberver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAllObervers() {
        for (Observer observer : observers) {
            int position = table.getSelectedRow();

            if (position >= 0) {
                observer.update(allObjects.get(position));
            }
        }

        setVisible(false);
    }
}
