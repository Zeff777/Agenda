package ui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import business.ContactBusiness;
import entidade.ContactEntidade;

public class MainForm extends JFrame {

    private JPanel rootPanel;
    private JButton novoContatoButton;
    private JButton removerContatoButton;
    private JTable tableContact;
    private JLabel labelContactCount;

    private ContactBusiness mContactBusiness;
    private String mName = "";
    private String mPhone = "";

    public MainForm() {

        setContentPane(rootPanel);
        setSize(500, 300);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width, dim.height / 2 - getSize().height);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mContactBusiness = new ContactBusiness();

        setListeners();
        loadContacts();
    }
    private void loadContacts() {
        List<ContactEntidade> contactList = mContactBusiness.getList();

        String[] columnNames = {"Nomes", "Telefones"};
        DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames);
        for (ContactEntidade i : contactList) {
            Object[] o = new Object[2];
            o[0] = i.getName();
            o[1] = i.getPhone();

            model.addRow(o);
        }
        tableContact.clearSelection();
        tableContact.setModel(model);
        labelContactCount.setText(mContactBusiness.getContCountDescription());
    }

    private void setListeners() {
        novoContatoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ContactForm();
                dispose();
            }
        });

        tableContact.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    if (tableContact.getSelectedRow() != -1) {
                        mName = tableContact.getValueAt(tableContact.getSelectedRow(), 0).toString();
                        mPhone = tableContact.getValueAt(tableContact.getSelectedRow(), 1).toString();
                    }
                }
            }
        });

        removerContatoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mContactBusiness.delete(mName, mPhone);
                    loadContacts();
                    mName = "";
                    mPhone = "";
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(new JFrame(), erro.getMessage());
                }

            }
        });

    }
}
