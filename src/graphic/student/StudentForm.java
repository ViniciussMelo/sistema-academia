package graphic.student;

import database.models.address.Address;
import database.models.address.City;
import database.models.address.State;
import database.models.modality.Student;
import database.models.user.User;
import database.service.CityService;
import database.service.Service;
import graphic.commons.ConsultaGenericaWindow;
import lib.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StudentForm extends JDialog implements Observer<Student> {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField fieldName;
    private JTextField fieldTelephone;
    private JComboBox comboState;
    private JComboBox comboCity;
    private JTextField fieldNeighorhood;
    private JTextField fieldStreet;
    private JTable tblModalities;
    private JPanel lblName;
    private JTextField fieldBirthDate;
    private JButton btnConsultar;

    private Student student = new Student();
    private List<State> states;
    private List<City> cities;
    private DefaultTableModel tblModel;

    public StudentForm() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(500, 500);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setContents();

        comboState.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboState.getSelectedIndex();

                if (selectedIndex == 0) {
                    return;
                }

                comboCity.removeAllItems();
                loadCities(states.get(selectedIndex - 1));
            }
        });

        tblModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblModel.addColumn("Nome");
        tblModel.addColumn("Valor");
        tblModalities.setModel(tblModel);

        StudentForm instance = this;
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultaGenericaWindow<Student>(Student.class, instance, new String[]{"Nome", "Telefone", "Ativo"}).open();
            }
        });
    }

    private void setContents() {
        Service<State> stateService = new Service<>(State.class);
        states = stateService.findAll();

        comboCity.addItem("Selecione uma cidade.");
        comboState.addItem("Selecione um estado");

        for (State state : states) {
            comboState.addItem(state.getName());
        }
    }

    private void loadCities(State state) {
        CityService cityService = new CityService();
        cities = cityService.getCitiesFromState(state);

        comboCity.addItem("Selecione uma cidade.");

        for (City city : cities) {
            comboCity.addItem(city.getName());
        }
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        StudentForm dialog = new StudentForm();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    @Override
    public void update(Student student) {
        setStudent(student);

        Address address = student.getAddress();

        fieldName.setText(student.getName());
        fieldTelephone.setText(student.getTelephone());
        fieldBirthDate.setText(student.getBirthDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
