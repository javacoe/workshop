package org.springhibernate.base.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="adres_database_coe")
public class Adres {

	public Adres(){
		
	}	
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	
	@Column(name="POST_CODE")
	private String postcode;
	@Column(name="STRAAT_NAAM")
	private String straatNaam;
	@Column(name="HUIS_NUMMER")
	private String huisNummer;
	@Column(name="TOEVOEGING")
	private String toevoeging;
	@Column(name="WOON_PLAATS")
	private String woonPlaats;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) { 
		this.postcode = postcode;
	}
	public String getStraatNaam() {
		return straatNaam;
	}
	public void setStraatNaam(String straatNaam) {
		this.straatNaam = straatNaam;
	}
	public String getHuisNummer() {
		return huisNummer;
	}
	public void setHuisNummer(String huisNummer) {
		this.huisNummer = huisNummer;
	}
	public String getToevoeging() {		
		return toevoeging;
	}
	public void setToevoeging(String toevoeging) {
		this.toevoeging = toevoeging;
	}
	public String getWoonPlaats() {
		return woonPlaats;
	}
	public void setWoonPlaats(String woonPlaats) {
		this.woonPlaats = woonPlaats;
	}

	
	
}
