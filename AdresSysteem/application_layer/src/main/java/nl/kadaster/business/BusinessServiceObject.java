package nl.kadaster.business;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.kadaster.web.domain.Adres;
import nl.kadaster.data.AdresRepository;

@Service
public class BusinessServiceObject implements BusinessService {
	
	private static final Logger LOG = LoggerFactory.getLogger(BusinessServiceObject.class);
	
	@Autowired
	private AdresRepository adresRepository;
	
	public void setAdresRepository(AdresRepository adresRepository) {
		this.adresRepository = adresRepository;
	}

	public BusinessServiceObject() {
		LOG.trace("BusinessServiceObject instantiated");
	}
	
	@Override
	public List<Adres> getAdressen() {
		// eventueel eerst checken of het mag
		List<nl.kadaster.core.domain.Adres> adressen = adresRepository.getAdressen();
		
		List<Adres> result = new ArrayList<Adres>(adressen.size());
		
		for (nl.kadaster.core.domain.Adres adres : adressen)
			result.add(new Adres(adres));
		
		return result;
	}

	@Override
	public Adres getAdres(int index) {
		nl.kadaster.core.domain.Adres gevondenAdres = adresRepository.getAdres(index);
		
		Adres adres = null;
		
		if (gevondenAdres != null)
			adres = new Adres(gevondenAdres);
		
		return adres;
	}
	
	@Override
	public void saveAdres(Adres adres) {
		adresRepository.storeAdres(adres.toAdres());
	}
	
	@Override
	public boolean[] saveAdres(Adres adres, String straat, String huisnummer, 
			String toevoeging, String postcode, String woonplaats) {
		
		boolean[] errorCodes = new boolean[6];
		
		// met JavaBeans, Retrospect/Introspect PropertyDescriptor etc. te refactoren?
		
		// check straat
		//TODO Mag niet leeg zijn, anders opzoeken met postcode
		// indien foutief: errorCodes[0] = errorCodes[1] = true;
		// anders:
		adres.setStraat(straat);

		try {
			adres.setHuisnummer(Short.parseShort(huisnummer));
		}
		catch (NumberFormatException e) {
			errorCodes[0] = errorCodes[2] = true;
		}
		
		// check toevoeging
		// indien foutief: errorCodes[0] = errorCodes[3] = true;
		// anders:
		adres.setToevoeging(toevoeging);
		
		// check postcode
		switch(postcode.length()) {
		case 6:
			if (postcode.matches("[1-9]\\d{3}[a-zA-Z]{2}")) {
				adres.setPostcode( postcode.substring(0,4) + " " + postcode.substring(4,6).toUpperCase() );
				break;
			}
		case 7:
			if (postcode.matches("[1-9]\\d{3}\\s[a-zA-Z]{2}")) {
				adres.setPostcode( postcode.toUpperCase() );
				break;
			}
		default:
			errorCodes[0] = errorCodes[4] = true;
		}

		// check woonplaats
		//TODO Mag niet leeg zijn, anders opzoeken met postcode
		// indien foutief: errorCodes[0] = errorCodes[5] = true;
		// anders:
		adres.setWoonplaats(woonplaats);
		
		if (!errorCodes[0])
			adresRepository.storeAdres(adres.toAdres());
		
		return errorCodes;
	}

	@Override
	public void verwijderAdres(Adres adres) throws Exception {
		adresRepository.removeAdres(adres.toAdres());
	}

	@Override
	public void updateAdres(Adres adres) throws Exception {
		adresRepository.updateAdres(adres.toAdres());
	}
}
