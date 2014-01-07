package app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;
    private JFileChooser chooser;


    public MainFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JButton openButton = new JButton("Импортировать");

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooser = new JFileChooser();

                int ret = chooser.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    Importer.doImport(file);
                }
            }
        });

        add(openButton);
    }
}
