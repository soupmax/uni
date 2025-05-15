package JFC;

//Konstruktor und alle Methoden mit eclipse generiert
class Auto {
	private String marke, typ;
	private Integer leistung, baujahr, preis;

	public Auto(String marke, String typ, int leistung, int baujahr, int preis) {
		super();
		this.marke = marke;
		this.typ = typ;
		this.leistung = leistung;
		this.baujahr = baujahr;
		this.preis = preis;
	}

	String getMarke() {
		return marke;
	}

	Integer getLeistung() {
		return leistung;
	}

	Integer getBaujahr() {
		return baujahr;
	}

	Integer getPreis() {
		return preis;
	}

	@Override
	public String toString() {
		return "Auto [marke=" + marke + ", typ=" + typ + ", leistung=" + leistung + ", " + "baujahr=" + baujahr
				+ ", preis=" + preis + "]";
	}

}
