package graphic.Query;

import database.models.modality.Modality;
import database.models.modality.Student;
import database.models.payment.Payment;
import database.service.Service;
import graphic.payment.PaymentConsultForm;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class QueryStudent extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton btnBuscar;
    private JTextField fieldDataNasc;
    private JTextField fieldName;
    private JTextField fieldMatricula;
    private JCheckBox ativoCheckBox;
    private JTable tblMensalidades;
    private JTable tlbModalidades;
    private JPanel modalities;
    private DefaultTableModel tblModel;
    private DefaultTableModel tblModelP;

    public QueryStudent() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(500, 500);
        setLocationRelativeTo(null);

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

        fieldDataNasc.setEnabled(false);
        ativoCheckBox.setEnabled(false);
        fieldName.setEnabled(false);

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Buscar();
            }
        });

        tblModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblModelP = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblModel.addColumn("Nome");
        tblModel.addColumn("Valor");
        tlbModalidades.setModel(tblModel);

        tblModelP.addColumn("Data Venci");
        tblModelP.addColumn("Valor");
        tblModelP.addColumn("Status Pago");
        tblMensalidades.setModel(tblModelP);
    }

    private void onOK() {
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    private void Buscar(){
        ValidaCampoInteiro(fieldMatricula);

        Integer codMatricula = Integer.parseInt(fieldMatricula.getText());

        Service<Student> studentService = new Service<>(Student.class);
        Service<Payment> paymentService = new Service<>(Payment.class);
        Student student = studentService.find(codMatricula);
        Payment payment = paymentService.find(codMatricula);

        List<Payment> payments = payment.paymentStatusOpen(codMatricula);
        List<Modality> modalities = student.getModalities();
        fieldName.setText(student.getName());
        fieldDataNasc.setText(student.getBirthDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        ativoCheckBox.setSelected(student.getActive());

        addPaymentsInTable(payments);
        addModalitiesInTable(modalities);

    }

    private void ValidaCampoInteiro(JTextField fieldMatricula){
        Integer codigo;

        if (fieldMatricula.getText().length() != 0){
            try{
                codigo = Integer.parseInt(fieldMatricula.getText());
            }
            catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Esse campo aceita apenas numero interios",
                                            "Informação",JOptionPane.INFORMATION_MESSAGE);

                fieldMatricula.grabFocus();
            }
        }
    }

    public void addModalitiesInTable(List<Modality> modalities) {
        for (Modality modality : modalities) {
            tblModel.addRow(new String[]{modality.getDescription(), modality.getValue().toString()});
        }
    }

    public void addPaymentsInTable(List<Payment> payments) {
        for (Payment payment : payments) {
            tblModelP.addRow(
                    new String[]{
                            String.valueOf(payment.getPayday()),
                            String.valueOf(payment.getAmount()),
                            String.valueOf(payment.getAmount_paid())
                    });
        }
    }

    public static void main(String[] args) {
        QueryStudent dialog = new QueryStudent();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
