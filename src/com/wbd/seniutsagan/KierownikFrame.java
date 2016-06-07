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
                //panelWindow.setLayout(new BorderLayout());
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

        // container Panel
        JPanel container = new JPanel();
      //  CardLayout cl = new CardLayout();
        // cafe_panel
        Cafe_Panel cafe_panel = new Cafe_Panel();
        cafe_panel.setName("main kierownik panel");
        // main db operations buttons
        DbButtonsPanel dbButtonsPanel = new DbButtonsPanel(this);
        listeners = new Listeners(this);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // buttons panel on left
        MakeKierownikButtonsPanel kierownikButtons = new MakeKierownikButtonsPanel(this );





       // container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));


        // wykomentowana z początkowym panelem


        //Cafe_Panel cafe_panel = null;


       // container.add(cafe_panel,BorderLayout.CENTER);
        getContentPane().add(cafe_panel,BorderLayout.CENTER);
      //  this.add(cafe_panel, BorderLayout.CENTER);



       // container.add(dbButtonsPanel,BorderLayout.NORTH);

        getContentPane().add(dbButtonsPanel,BorderLayout.NORTH);
        //this.add(dbButtonsPanel,BorderLayout.NORTH);


//        this.setLayout(new BorderLayout());


       // container.add(kierownikButtons,BorderLayout.WEST);
        getContentPane().add(kierownikButtons,BorderLayout.WEST);
        //this.add(kierownikButtons, BorderLayout.WEST);
        this.pack();

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
