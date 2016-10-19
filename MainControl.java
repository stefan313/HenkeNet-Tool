/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;
import java.util.logging.LogRecord;
import java.util.List;

/**
 *
 * @author Stefan, Tobias
 */
public class MainControl {

    public String dbuser;

    //Strings set in initialisation
    private String keyStorePath, keyStorePassword,
            trustStorePath, trustStorePassword;

    //Static Content
    static private String configurationPath = "configuration.txt";

    //Forms
    private MainView mainView;
    private LoginForm loginView;

    private EditCreateForm ecView;
    private TransactionForm transactionView;

    private MySQLDataLink dataSource;
    private final static Logger LOG = Logger.getLogger("*");

    MainControl() {
        // DEBUG
        LOG.setLevel(Level.INFO);
        LOG.addHandler(new StatusBar());

        // Parses Required SSL Configuration, if it fails it prints it to stdout
        // TODO: refactor into Method
        InputStreamReader inputStreamReader = null;
        BufferedReader configBuffer = null;
        try {
            inputStreamReader = new InputStreamReader(
                    new FileInputStream(configurationPath));
            configBuffer = new BufferedReader(inputStreamReader);
            String line = configBuffer.readLine();
            while (line != null) {
                if (line.startsWith("keyStorePath=")) {
                    keyStorePath = line.substring(line.indexOf("=") + 1);
                } else if (line.startsWith("keyStorePassword=")) {
                    keyStorePassword = line.substring(line.indexOf("=") + 1);
                } else if (line.startsWith("trustStorePath=")) {
                    trustStorePath = line.substring(line.indexOf("=") + 1);
                } else if (line.startsWith("trustStorePassword=")) {
                    trustStorePassword = line.substring(line.indexOf("=") + 1);
                }

                line = configBuffer.readLine();
            }

        } catch (Exception e) {
            System.out.println("Config (SSL) parsing Failed! Exception: " + e.getMessage());
        } finally {
            try {
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (configBuffer != null) {
                    configBuffer.close();
                }
            } catch (Exception e) {
                System.err.println("Exception: " + e.getMessage());
            }
        }

        loginView = new LoginForm(this);
        loginView.setVisible(true);
    }

    public static void main(String[] args) {
        new MainControl();
    }

    void trylogin() {
        //zur DB verbinden

        /*
        dataSource = new MySQLDataLink(
                "shelldon",
                "radius",
                loginView.getTxtUser().getText(),
                String.valueOf(loginView.getTxtPassword().getPassword())
        );
         */
        
        // TODO server name und datenbank name in die config auslagern! #gegenHardcode!
        
        //neuer Konstruktor initialize muss vorher ausgeführt werden!
	// ueberpruefung dass auch alles gesetzt wurde!
	if (!(trustStorePath == null || trustStorePassword == null))
	{
		if(keyStorePath == null || keyStorePassword == null)
		{
			// falls kein client key vorliegt
			dataSource = new MySQLDataLink("shelldon",
					"radius",
					loginView.getTxtUser().getText(),
					String.valueOf(loginView.getTxtPassword().getPassword()),
					trustStorePath,
					trustStorePassword);
		} else {
			// mit client keys
			dataSource = new MySQLDataLink("shelldon",
					"radius",
					loginView.getTxtUser().getText(),
					String.valueOf(loginView.getTxtPassword().getPassword()),
					keyStorePath,
					keyStorePassword,
					trustStorePath,
					trustStorePassword);
		}
	} else {
		//		geh kaputt
		LOG.severe("Faulty Config");
		System.exit(1);
	}

	if (!(dataSource.connect())) {
		LOG.severe("Failed to connect.");
		return;
	}

	dbuser = loginView.getTxtUser().getText();
	loginView.setVisible(false);
	mainView = new MainView(this);
	mainView.setVisible(true);

    }

    void showECForm(User selected) {
	    this.mainView.setEnabled(false);
	    this.ecView = new EditCreateForm(this, selected);
	    this.ecView.setVisible(true);
    }

    List<User> doSearch(String searchText) {
	    return dataSource.lookupUser(searchText);
    }

    void initCreate(User u) {
	    //Transaktionsfenster öffnen
	    mainView.setEnabled(false);
	    transactionView = new TransactionForm(this, TransactionForm.TransactionType.CREATE,
			    u, dbuser);
	    transactionView.setVisible(true);
    }

    void commitCreate(User u, String comment, int amount) {
	    //Daten zur DB senden
	    //Insert Username and Pw
	    if (dataSource.insert(u, comment, amount) != -1) {
		    LOG.info("[SUCCESS] Added user '" + u.getUsername() + "'");
	    } else {
		    LOG.log(Level.SEVERE, "[FAIL] Failed to insert user '" + u.getUsername() + "'");
	    }
	    enableMain();
    }

    void initUpdate(User u) {
	    mainView.setEnabled(false);
	    transactionView = new TransactionForm(this, TransactionForm.TransactionType.UPDATE,
			    u, dbuser);
	    transactionView.setVisible(true);

    }

    void commitUpdate(User u, String comment, int amount) {
	    if (dataSource.update(u, comment, amount) != -1) {
		    LOG.info("[SUCCESS] Updated user '" + u.getUsername() + "'");
	    } else {
		    LOG.log(Level.SEVERE, "[FAIL] Failed to update user '" + u.getUsername() + "'");
	    }
	    enableMain();
    }

    void initDelete(User u) {
	    mainView.setEnabled(false);
	    transactionView = new TransactionForm(this, TransactionForm.TransactionType.DELETE,
			    u, dbuser);
	    transactionView.setVisible(true);
    }

    void commitDelete(User u, String comment, int amount) {
	    dataSource.delete(u, comment, amount);
	    LOG.info("[SUCCESS] Deleted user '" + u.getUsername() + "'");
	    enableMain();
    }

    void initExtend(User u) {
	    mainView.setEnabled(false);
	    transactionView = new TransactionForm(this, TransactionForm.TransactionType.EXTEND_VALIDITY,
			    u, dbuser);
	    transactionView.setVisible(true);
    }

    void commitExtend(User u, String comment, int amount) {
	    if (dataSource.update(u, comment, amount) != -1) {
		    LOG.info("[SUCCESS] Extended user '" + u.getUsername() + "'");
	    } else {
		    LOG.log(Level.SEVERE, "[FAIL] Failed to extend user '" + u.getUsername() + "'");
	    }
	    enableMain();
    }

    void showTransactionHistory(User u) {
	    new TransactionHistoryView(dataSource, u).setVisible(true);
    }

    public void closeConn() {
	    dataSource.disconnect();
    }

    public void enableMain() {
	    this.mainView.setEnabled(true);
	    mainView.setVisible(true);
	    mainView.setState(Frame.NORMAL);
	    mainView.updateBrowserView();
    }

    //Get next expiration date for "Extend Validity"
    public String getNextExpDate() {
	    Calendar c = Calendar.getInstance();
	    int month = c.get(Calendar.MONTH);
	    String ret;
	    //Sommersemester, validate until october
	    if (month >= 3 && month <= 8) {
		    ret = c.get(Calendar.YEAR) + "-10-31";
	    } else {
		    //Wintersemester, valdiate until april
		    ret = (c.get(Calendar.YEAR) + 1) + "-04-30";
	    }
	    return ret;
    }

    public class StatusBar extends StreamHandler {

	    public void publish(LogRecord rec) {
		    if (mainView != null) {
			    mainView.getStatusBar().setText(rec.getMessage());
			    if (rec.getLevel().intValue() >= Level.SEVERE.intValue()) {
				    mainView.getStatusBar().setForeground(Color.RED);
			    } else {
				    mainView.getStatusBar().setForeground(Color.BLACK);
			    }
		    }

		    if (loginView != null) {
			    loginView.getLblStatusBar().setText("Try again.");
			    // message unhelpful. try again.
		    }

	    }
    }
}
