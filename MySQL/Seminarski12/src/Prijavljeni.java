import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;


public class Prijavljeni implements Reportable {
	private short rbr;
	private String ime;
	private String prezime;
	private String roditelj;
	private String jmbg;
	private String mesto_stanovanja;
	private String adresa;
	private Date datum_rodjenja;
	
	private int id_smer1;
	private int id_smer2;
	private int id_smer3;
	
	private double prosek1;
	private double prosek2;
	private double prosek3;	
	private double prosek4;

	private short nagrada;
	private short prijemni;
	private double ukupno;
	
	public Prijavljeni(){
		rbr = 0;
		prijemni = 0;
		ime = "Perea";
		prezime = "Pereic";
		roditelj = "P";
		jmbg = "123";
		adresa = "ad";
		datum_rodjenja = new Date();
		mesto_stanovanja="nigde";
		id_smer1 = 1;
		id_smer2 = 2;
		id_smer3 = 3;
		prosek1 = 1;
		prosek2 = 2;
		prosek3 = 3;
		prosek4 = 4;
		nagrada = 1;
	}
	
	public short getRbr() {
		return rbr;
	}



	public void setRbr(short rbr) {
		this.rbr = rbr;
	}



	public String getIme() {
		return ime;
	}



	public void setIme(String ime) {
		this.ime = ime;
	}



	public String getPrezime() {
		return prezime;
	}



	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}



	public String getRoditelj() {
		return roditelj;
	}



	public void setRoditelj(String roditelj) {
		this.roditelj = roditelj;
	}



	public String getJmbg() {
		return jmbg;
	}



	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}



	public String getMesto_stanovanja() {
		return mesto_stanovanja;
	}



	public void setMesto_stanovanja(String mesto_stanovanja) {
		this.mesto_stanovanja = mesto_stanovanja;
	}



	public String getAdresa() {
		return adresa;
	}



	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}



	public Date getDatum_rodjenja() {
		return datum_rodjenja;
	}



	public void setDatum_rodjenja(Date datum_rodjenja) {
		this.datum_rodjenja = datum_rodjenja;
	}



	public int getId_smer1() {
		return id_smer1;
	}



	public void setId_smer1(int id_smer1) {
		this.id_smer1 = id_smer1;
	}



	public int getId_smer2() {
		return id_smer2;
	}



	public void setId_smer2(int id_smer2) {
		this.id_smer2 = id_smer2;
	}



	public int getId_smer3() {
		return id_smer3;
	}



	public void setId_smer3(int id_smer3) {
		this.id_smer3 = id_smer3;
	}



	public double getProsek1() {
		return prosek1;
	}



	public void setProsek1(double prosek1) {
		this.prosek1 = prosek1;
	}



	public double getProsek2() {
		return prosek2;
	}



	public void setProsek2(double prosek2) {
		this.prosek2 = prosek2;
	}



	public double getProsek3() {
		return prosek3;
	}



	public void setProsek3(double prosek3) {
		this.prosek3 = prosek3;
	}



	public double getProsek4() {
		return prosek4;
	}



	public void setProsek4(double prosek4) {
		this.prosek4 = prosek4;
	}



	public short getNagrada() {
		return nagrada;
	}



	public void setNagrada(short nagrada) {
		this.nagrada = nagrada;
	}



	public short getPrijemni() {
		return prijemni;
	}



	public void setPrijemni(short prijemni) {
		this.prijemni = prijemni;
	}



	public double getUkupno() {
		return ukupno;
	}



	public void setUkupno(double ukupno) {
		this.ukupno = ukupno;
	}



	public void racunajUkupno(){
		ukupno = 2*(prosek1+prosek2+prosek3+prosek4) + prijemni;
	}

	@Override
	public void report(OutputStreamWriter outputStream) {
        try {
            outputStream.write("  {\n");
            outputStream.write("    rbr: " + rbr + ",\n");
            outputStream.write("    ime: " + ime + ",\n");
            outputStream.write("    prezime: " + prezime + ",\n");
            outputStream.write("    roditelj: " + roditelj + ",\n");
            outputStream.write("    jmbg: " + jmbg + ",\n");
            outputStream.write("    datum_rodjenja: " + datum_rodjenja + ",\n");
            outputStream.write("    mesto_stanovanja: " + mesto_stanovanja + ",\n");
            outputStream.write("    adresa: " + adresa + ",\n");
            outputStream.write("    id_smer_1: " + id_smer1 + ",\n");
            outputStream.write("    id_smer_2: " + id_smer2 + ",\n");
            outputStream.write("    id_smer_3: " + id_smer3 + ",\n");
            outputStream.write("    prosek_I_god: " + prosek1 + ",\n");
            outputStream.write("    prosek_II_god: " + prosek2 + ",\n");
            outputStream.write("    prosek_III_god: " + prosek3 + ",\n");
            outputStream.write("    prosek_IV_god: " + prosek4 + ",\n");
            outputStream.write("    nagrada: " + nagrada + ",\n");
            outputStream.write("    prijemni: " + prijemni + ",\n");
            outputStream.write("    ukupno: " + ukupno + " ");
            outputStream.write("\n");
            outputStream.write("  },\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	//rbr 8 mesta
	@Override
	public void printHeader(OutputStreamWriter outputStream) {
        try {
            outputStream.write("[");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printFooter(OutputStreamWriter outputStreamWriter){
        try {
            outputStreamWriter.write("]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
