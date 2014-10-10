package nl.kadaster.business;

import java.util.List;

import nl.kadaster.web.domain.Adres;

public interface BusinessService {
	
	public List<Adres> getAdressen();
	
	public Adres getAdres(int index);
	
	public boolean[] saveAdres(Adres adres, String straat, String huisnummer, 
			String toevoeging, String postcode, String woonplaats);
	// bij een correct meegegeven adres wordt deze in object adres opgeslagen en in database
	// return value is dan een array met false's
	// anders wordt er een foutcode gegeven door true op positie 0 en op de positie(s)
	// van de foute invoer
	
	public void verwijderAdres(Adres adres) throws Exception;

	public void updateAdres(Adres adres) throws Exception;

	void saveAdres(Adres adres);
}
