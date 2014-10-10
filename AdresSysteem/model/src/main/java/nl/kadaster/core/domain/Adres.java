package nl.kadaster.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ADRESSEN")
public class Adres {
	// POJO voor een Adres
	
	@Id
	@GeneratedValue
	@Column(name="ADRES_ID")
	private long id;
	
	@Column(name="ADRES_STRAAT")
	private String straat;
	
	@Column(name="ADRES_HUISNUMMER")
	private short huisnummer;

	@Column(name="ADRES_TOEVOEGING")
	private String toevoeging;

	@Column(name="ADRES_POSTCODE")
	private String postcode;

	@Column(name="ADRES_WOONPLAATS")
	private String woonplaats;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStraat() {
		return straat;
	}
	public void setStraat(String straat) {
		this.straat = straat;
	}
	public short getHuisnummer() {
		return huisnummer;
	}
	public void setHuisnummer(short huisnummer) {
		this.huisnummer = huisnummer;
	}
	public String getToevoeging() {
		return toevoeging;
	}
	public void setToevoeging(String toevoeging) {
		this.toevoeging = toevoeging;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getWoonplaats() {
		return woonplaats;
	}
	public void setWoonplaats(String woonplaats) {
		this.woonplaats = woonplaats;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + huisnummer;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((postcode == null) ? 0 : postcode.hashCode());
		result = prime * result + ((straat == null) ? 0 : straat.hashCode());
		result = prime * result
				+ ((toevoeging == null) ? 0 : toevoeging.hashCode());
		result = prime * result
				+ ((woonplaats == null) ? 0 : woonplaats.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Adres))
			return false;
		Adres other = (Adres) obj;
		if (huisnummer != other.huisnummer)
			return false;
		if (id != other.id)
			return false;
		if (postcode == null) {
			if (other.postcode != null)
				return false;
		} else if (!postcode.equals(other.postcode))
			return false;
		if (straat == null) {
			if (other.straat != null)
				return false;
		} else if (!straat.equals(other.straat))
			return false;
		if (toevoeging == null) {
			if (other.toevoeging != null)
				return false;
		} else if (!toevoeging.equals(other.toevoeging))
			return false;
		if (woonplaats == null) {
			if (other.woonplaats != null)
				return false;
		} else if (!woonplaats.equals(other.woonplaats))
			return false;
		return true;
	}
}
