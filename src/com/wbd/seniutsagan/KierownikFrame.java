package com.wbd.seniutsagan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Locale;

/**
 * Created by monika03 on 04.06.16.
 */
public class KierownikFrame extends JFrame {
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 800;

    public String language;
    public Listeners listeners;
    boolean condition = true;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Messages.setLocale(Locale.getDefault());
                KierownikFrame panelWindow = new KierownikFrame();
                panelWindow.initialize();
            }
        });
    }

    public KierownikFrame() {
        initialize();

    }

    /**
     * Initializing main frame of the program, setting Layout and other important stuff for visual view
     */
    private void initialize() {

        language="polish";
        condition = true;
        listeners = new Listeners(this);

       MakeKierownikButtonsPanel kierownikButtons = new MakeKierownikButtonsPanel(this);
       // this.setLayout(new BorderLayout());
        this.add(kierownikButtons, BorderLayout.CENTER);
      //  JComponent newContentPane = new MakeKierownikButtonsPanel(this);
        //content panes must be opaque
      //  newContentPane.setOpaque(true);
        //this.setContentPane(newContentPane);

        //Display the window.
        this.pack();


        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(null, Messages.getString("askForClose"), Messages.getString("exitConfirmation"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    condition = false;

                    System.exit(0);
                }
            }
        };
        this.addWindowListener(exitListener);

        Dimension dimensions = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(dimensions.width / 2 - WINDOW_WIDTH / 2, dimensions.height
                / 2 - WINDOW_HEIGHT / 2, WINDOW_WIDTH, WINDOW_HEIGHT);

        setTitle(Messages.getString("title"));


    }

}
