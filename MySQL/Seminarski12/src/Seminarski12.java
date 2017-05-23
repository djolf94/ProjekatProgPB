import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
        
        dateModel = new UtilDateModel();
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
        
        addAButton("Clear", this);
        addAButton("Insert", this);
        addAButton("Report", this);
        addAButton("Exit", this);
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
				System.out.println("U nekom od polja za numericke vrednosti je neispravan unos");
				return;
			}
			
			prijavljeni.racunajUkupno();
			
			if (prijavljeni.getProsek1() <2 || prijavljeni.getProsek1() > 5) {
				System.out.println("Prosek za 1 godine nije u redu");
				return;
			}
			if (prijavljeni.getProsek2() <2 || prijavljeni.getProsek2() > 5) {
				System.out.println("Prosek za 2 godine nije u redu");
				return;
			}
			if (prijavljeni.getProsek3()<2 || prijavljeni.getProsek3() > 5) {
				System.out.println("Prosek za 3 godine nije u redu");
				return;
			}
			if (prijavljeni.getProsek4() <2 || prijavljeni.getProsek4() > 5) {
				System.out.println("Prosek za 4 godine nije u redu");
				return;
			}
			
			if (prijavljeni.getJmbg().length() != 13) {
				System.out.println("JMBG neispravne duzine");
				return;
			}
			if (!prijavljeni.getJmbg().matches("[0-9]+")) {
				System.out.println("JMBG ne sadzri samo numericke vrednosti");
				return;
			}
			if (prijavljeni.getNagrada() <1 || prijavljeni.getNagrada() > 60) {
				System.out.println("Nagrada mora da iznosi izmedju 0 i 60 bodova");
				return;
			}
			
			Short s = insertPrijavljeni(prijavljeni);
			System.out.println(s);
		}
			
		
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
	      }finally {
	         session.close(); 
	      }
	      return rbr;
	}
}
