package lib;

import javax.swing.*;
import java.io.File;

public class MasterUtil {

    public static void makeBackup() {
        File diretorio = new File("./Backup");
        File arquivo = new File("./Backup/Unesc.sql");

        if (!diretorio.isDirectory()) {
            diretorio.mkdir();
        }

        try {
            if (arquivo.isFile()) {
                if (JOptionPane.showConfirmDialog(null, "Já existe um backup deseja sobescrever?") == JOptionPane.YES_OPTION) {

                }
            } else {
                JOptionPane.showMessageDialog(null, "Cancelado!");
            }
        } catch (Exception e) {

        }
    }

}
