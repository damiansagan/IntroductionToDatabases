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
                panelWindow.setLayout(new BorderLayout());
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

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

        MakeKierownikButtonsPanel kierownikButtons = new MakeKierownikButtonsPanel(this);
//        this.setLayout(new BorderLayout());
        this.add(kierownikButtons, BorderLayout.WEST);
        Cafe_Panel cafe_panel = new Cafe_Panel();
        this.add(cafe_panel, BorderLayout.CENTER);

        DbButtonsPanel dbButtonsPanel = new DbButtonsPanel(this);
        this.add(dbButtonsPanel,BorderLayout.NORTH);


       // JComponent newContentPane = new MakeKierownikButtonsPanel(this);
      //  content panes must be opaque
        //newContentPane.setOpaque(true);

       // this.setContentPane(newContentPane);
      //  JComponent newContentPane1 = new Cafe_Panel();
        //  content panes must be opaque
       // newContentPane1.setOpaque(true);
       // this.setContentPane(newContentPane1);
        this.pack();
        //Display the window.
       // this.add(container);



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
