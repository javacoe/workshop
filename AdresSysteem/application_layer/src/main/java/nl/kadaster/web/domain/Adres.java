package nl.kadaster.web.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class Adres {
	
	public Adres() {
		super();
	}
	
	public Adres(nl.kadaster.core.domain.Adres adres) {
		this.setId(adres.getId());
		this.straat = adres.getStraat();
		this.huisnummer = adres.getHuisnummer();
		this.toevoeging = adres.getToevoeging();
		this.postcode = adres.getPostcode();
		this.woonplaats = adres.getWoonplaats();
	}

	private long id;
	
	private String straat = "";
	
	@Min(1)
	private short huisnummer;
	
	private String toevoeging = "";
	
	@NotNull
	@Size(min=6, max=7)
	@Pattern.List(value={
			@Pattern(regexp="[1-9]\\d{3}[a-zA-Z]{2}", message="Postcode bestaat uit 4 cijfers gevolgd door 2 letters."),
			@Pattern(regexp="[1-9]\\d{3}\\s[a-zA-Z]{2}", message="Postcode bestaat uit 4 cijfers gevolgd door 2 letters.")
	})
	private String postcode = "";
	
	private String woonplaats = "";
	
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
/*		switch(postcode.length()) {
		case 6:
			if (postcode.matches("[1-9]\\d{3}[a-zA-Z]{2}")) {
				this.postcode = postcode.substring(0,4) + " " + postcode.substring(4,6).toUpperCase();
				break;
			}
		case 7:
			if (postcode.matches("[1-9]\\d{3}\\s[a-zA-Z]{2}")) {
				this.postcode = postcode.toUpperCase();
				break;
			}
		default:
			throw new IllegalArgumentException("Postcode onjuist geformatteerd.");
		}*/
		
		this.postcode = postcode;
	}
	public String getWoonplaats() {
		return woonplaats;
	}
	public void setWoonplaats(String woonplaats) {
		this.woonplaats = woonplaats;
	}
	
	public nl.kadaster.core.domain.Adres toAdres() {
		nl.kadaster.core.domain.Adres adres = new nl.kadaster.core.domain.Adres();
		
		adres.setId(getId());
		adres.setStraat(getStraat());
		adres.setHuisnummer(getHuisnummer());
		adres.setToevoeging(getToevoeging());
		adres.setPostcode(getPostcode());
		adres.setWoonplaats(getWoonplaats());
		
		return adres;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("id=").append(getId());
		sb.append(", ");
		sb.append("straat=").append(getStraat());
		sb.append(", ");
		sb.append("huisnummer=").append(getHuisnummer());
		sb.append(", ");
		sb.append("toevoeging=").append(getToevoeging());
		sb.append(", ");
		sb.append("postcode=").append(getPostcode());
		sb.append(", ");
		sb.append("woonplaats=").append(getWoonplaats());		
		
		return sb.toString();
	}
}
