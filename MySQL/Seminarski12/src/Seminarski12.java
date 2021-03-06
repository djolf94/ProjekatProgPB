import java.awt.Component;
import java.awt.Container;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.*;

import javax.swing.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


public class Seminarski12 extends JPanel implements ActionListener{
	
	private List<JTextField> textFields= new ArrayList<JTextField>(); 
	private UtilDateModel dateModel;
	private boolean postavljeno = false;

	private static SessionFactory factory; 
	public static void main(String[] args) {
		try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}

	
    public Seminarski12() {
    	BoxLayout rootBox = new BoxLayout(this, BoxLayout.Y_AXIS);
    	setLayout(rootBox);
        
        Box box = Box.createHorizontalBox();
        
        createFieldWithLabel("Ime", box);
        add(box); 
        box = Box.createHorizontalBox();
        createFieldWithLabel("Prezime", box);
        add(box); 
        box = Box.createHorizontalBox();
        createFieldWithLabel("Roditelj", box);
        add(box); 
        box = Box.createHorizontalBox();
        createFieldWithLabel("JMBG", box);
        add(box); 
        
        box = Box.createHorizontalBox();
        
        box.add(new JLabel("Datum"));
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        
        dateModel = new UtilDateModel(new Date());
        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
         
        box.add(datePicker);
        add(box); 
        
        box = Box.createHorizontalBox();
        createFieldWithLabel("Mesto stanovanja", box);
        add(box); 
        box = Box.createHorizontalBox();
        createFieldWithLabel("Adresa", box);
        add(box); 
        box = Box.createHorizontalBox();
        createFieldWithLabel("ID smera 1", box);
        add(box); 
        box = Box.createHorizontalBox();
        createFieldWithLabel("ID smera 2", box);
        add(box); 
        box = Box.createHorizontalBox();
        createFieldWithLabel("ID smera 3", box);
        add(box); 
        box = Box.createHorizontalBox();
        createFieldWithLabel("Prosek I god", box);
        add(box); 
        box = Box.createHorizontalBox();
        createFieldWithLabel("Prosek II god", box);
        add(box); 
        box = Box.createHorizontalBox();
        createFieldWithLabel("Prosek III god", box);
        add(box); 
        box = Box.createHorizontalBox();
        createFieldWithLabel("Prosek IV god", box);
        add(box); 
        box = Box.createHorizontalBox();
        createFieldWithLabel("Nagrada", box);
        add(box); 

        box = Box.createHorizontalBox();
        addAButton("Clear", box);
        addAButton("Insert", box);
        add(box);
        box = Box.createHorizontalBox();
        addAButton("Report", box);
        add(box);
        addAButton("Exit", this);

        JCheckBox sortButton = new JCheckBox("Sort by points");
        sortButton.setMnemonic(KeyEvent.VK_T);
        sortButton.setSelected(false);
        sortButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.DESELECTED)
                    postavljeno = false;
                else postavljeno = true;
            }
        });
        box.add(sortButton);
    }
 
    private void createFieldWithLabel(String labeltext, Container container){
    	JLabel label = new JLabel(labeltext);
    	JTextField tfield = new JTextField();
    	textFields.add(tfield);
    	container.add(label);
    	container.add(tfield);
    }
    
    private void addAButton(String text, Container container) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setActionCommand(text);
        button.addActionListener(this);
        container.add(button);
    }
    
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Seminarski12 newContentPane = new Seminarski12();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Insert"))
		{
			Prijavljeni prijavljeni = new Prijavljeni();
			prijavljeni.setIme(textFields.get(0).getText().trim());
			prijavljeni.setPrezime(textFields.get(1).getText().trim());
			prijavljeni.setRoditelj(textFields.get(2).getText().trim());
			prijavljeni.setJmbg(textFields.get(3).getText().trim());
			prijavljeni.setDatum_rodjenja(dateModel.getValue());
			prijavljeni.setMesto_stanovanja(textFields.get(4).getText().trim());
			prijavljeni.setAdresa(textFields.get(5).getText().trim());
			try {
			prijavljeni.setId_smer1(Integer.parseInt(textFields.get(6).getText().trim()));
			prijavljeni.setId_smer2(Integer.parseInt(textFields.get(7).getText().trim()));
			prijavljeni.setId_smer3(Integer.parseInt(textFields.get(8).getText().trim()));
			prijavljeni.setProsek1(Double.parseDouble(textFields.get(9).getText().trim()));
			prijavljeni.setProsek2(Double.parseDouble(textFields.get(10).getText().trim()));
			prijavljeni.setProsek3(Double.parseDouble(textFields.get(11).getText().trim()));
			prijavljeni.setProsek4(Double.parseDouble(textFields.get(12).getText().trim()));
			prijavljeni.setNagrada(Short.parseShort(textFields.get(13).getText().trim()));
			}
			catch (NumberFormatException pera) {
				showErrorAlert("U nekom od polja za numericke vrednosti je neispravan unos", "Greska pri unosu");
				return;
			}
			
			prijavljeni.racunajUkupno();

			if(prijavljeni.getIme().isEmpty()){
			    showErrorAlert("Polje za ime ne sme biti prazno", "Greska pri unosu");
                return;
			}

            if(prijavljeni.getPrezime().isEmpty()){
                showErrorAlert("Polje za prezime ne sme biti prazno", "Greska pri unosu");
                return;
			}

            if(prijavljeni.getRoditelj().isEmpty()){
                showErrorAlert("Polje za roditelja ne sme biti prazno", "Greska pri unosu");
                return;
            }

            if(prijavljeni.getAdresa().isEmpty()){
                showErrorAlert("Polje za adresu ne sme biti prazno", "Greska pri unosu");
            }

            if(prijavljeni.getMesto_stanovanja().isEmpty()){
                showErrorAlert("Polje za messto stanovanja ne sme biti prazno", "Greska pri unosu");
            }

			if (prijavljeni.getProsek1() <2 || prijavljeni.getProsek1() > 5) {
				showErrorAlert("Prosek za prvu godinu nije u redu", "Greska pri unosu");
				return;
			}
			if (prijavljeni.getProsek2() <2 || prijavljeni.getProsek2() > 5) {
				showErrorAlert("Prosek za drugu godinu nije u redu", "Greska pri unosu");
				return;
			}
			if (prijavljeni.getProsek3()<2 || prijavljeni.getProsek3() > 5) {
				showErrorAlert("Prosek za trecu godinu nije u redu", "Greska pri unosu");
				return;
			}
			if (prijavljeni.getProsek4() <2 || prijavljeni.getProsek4() > 5) {
				showErrorAlert("Prosek za cetvrtu godinu nije u redu", "Greska pri unosu");
				return;
			}
			
			if (prijavljeni.getJmbg().length() != 13) {
				showErrorAlert("JMBG neispravne duzine", "Greska pri unosu");
				return;
			}
			if (!prijavljeni.getJmbg().matches("[0-9]+")) {
				showErrorAlert("JMBG neispravnog formata", "Greska pri unosu");
				return;
			}
			if (prijavljeni.getNagrada() <1 || prijavljeni.getNagrada() > 60) {
				showErrorAlert("Nagrada mora da iznosi izmedju 0 i 60 bodova", "Greska pri unosu");
				return;
			}

			Short s = insertPrijavljeni(prijavljeni);
			System.out.println(s);
			if(s != null){
                Prijavljeni uspesnoPrijavljeni = selectPrijavljeni(s);
                JOptionPane.showMessageDialog(this,
                        "Rbr " + uspesnoPrijavljeni.getRbr() + "\n" + "Ime " + uspesnoPrijavljeni.getIme() + "\n"
                        + "Prezime " + uspesnoPrijavljeni.getPrezime(),
                        "Uspesno prijavljen!",
                        JOptionPane.PLAIN_MESSAGE);
            }
		}
		else if(e.getActionCommand().equals("Clear")){
		    for(JTextField tf : textFields){
		        tf.setText("");
            }
            dateModel.setValue(new Date());
        }
        else if(e.getActionCommand().equals("Exit")){
		    System.exit(0);
        }
        else if(e.getActionCommand().equals("Report")){
            List<Prijavljeni> sviPrijavljeni = selectPrijavljeni();
            System.out.println(sviPrijavljeni.size());
            List<Reportable> reportables = new ArrayList<>();
            for(Prijavljeni p : sviPrijavljeni){
                reportables.add((Reportable)p);
            }
            try {
                OutputStreamWriter streamWriter = new OutputStreamWriter(new FileOutputStream("izvestaj.txt"));
                ReportPrinter printer = new ReportPrinter(streamWriter);
                printer.printList(reportables);
                streamWriter.close();
            }
            catch (Exception exc){
                System.out.println("jbg");
            }
        }
	}

	private List<Prijavljeni> selectPrijavljeni(){
        Session session = factory.openSession();
        Transaction tx = null;
        List<Prijavljeni> prijavljeni = new ArrayList<>();
        try{
            tx = session.beginTransaction();
            String query = "FROM Prijavljeni" + ((postavljeno)? " order by ukupno desc" : " order by rbr");
            List prijavljeniFromDB = session.createQuery(query).list();
            for (Iterator iterator = prijavljeniFromDB.iterator(); iterator.hasNext();){
                Prijavljeni ptmp = (Prijavljeni) iterator.next();
                prijavljeni.add(ptmp);
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            showErrorAlert(e);
        }finally {
            session.close();
        }
        return prijavljeni;
    }

    private Prijavljeni selectPrijavljeni(Short rbr){
        Session session = factory.openSession();
        Transaction tx = null;
        Prijavljeni prijavljeniFromDB = null;
        try{
            tx = session.beginTransaction();
            prijavljeniFromDB = (Prijavljeni)session.get(Prijavljeni.class, rbr);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            showErrorAlert(e);
        }finally {
            session.close();
        }
        return prijavljeniFromDB;
    }

    private void showErrorAlert(String error, String windowName){
        JOptionPane.showMessageDialog(this,
                error,
                windowName,
                JOptionPane.ERROR_MESSAGE);
    }

    private void showErrorAlert(HibernateException error){
        Throwable thr = error.getCause();
        JOptionPane.showMessageDialog(this,
                thr.toString(),
                "SQL error",
                JOptionPane.ERROR_MESSAGE);
    }


	private Short insertPrijavljeni(Prijavljeni prijavljeni){
		Session session = factory.openSession();
		Transaction tx = null;
		Short rbr = null;
	      try{
	         tx = session.beginTransaction();
	         rbr = (Short) session.save(prijavljeni); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace();
              showErrorAlert(e);
	      }finally {
	         session.close(); 
	      }
	      return rbr;
	}
}
