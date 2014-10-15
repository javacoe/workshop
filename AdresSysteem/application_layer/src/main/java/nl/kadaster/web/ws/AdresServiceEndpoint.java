package nl.kadaster.web.ws;

import nl.kadaster.business.BusinessService;
import nl.kadaster.web.domain.Adres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.capgemini.adres.schema.AdresRaadplegenRequest;
import com.capgemini.adres.schema.AdresRaadplegenResponse;
import com.capgemini.adres.schema.AdresRaadplegenResponseType;
import com.capgemini.adres.schema.AdresType;
import com.capgemini.adres.schema.ObjectFactory;

@Endpoint
public class AdresServiceEndpoint {

	private static final Logger LOG = LoggerFactory.getLogger(AdresServiceEndpoint.class);

	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private ObjectFactory objectFactory;
	
	public AdresServiceEndpoint() {
		LOG.trace("AdresServiceEndpoint instantiated");
	}

//	@SoapAction(value="http://www.capgemini.com/adres/schema/AdresRaadplegenRequest")
	@PayloadRoot(localPart = "AdresRaadplegenRequest", namespace = "http://www.capgemini.com/adres/schema")
	@ResponsePayload
	public AdresRaadplegenResponse getAdres(@RequestPayload AdresRaadplegenRequest request) {
		LOG.trace("calling getAdres() from AdresServiceEndpoint");
		int index = request.getVerzoek().getIdentificatie();
		LOG.debug("identificatie {} uit bericht gehaald", index);
		Adres adres = businessService.getAdres(index);
		LOG.debug("returning Adres met index {}: {}", index, adres);
		
		// nog iets doen als adres == null
		
		AdresRaadplegenResponse response = objectFactory.createAdresRaadplegenResponse();
		AdresRaadplegenResponseType responseType = objectFactory.createAdresRaadplegenResponseType();
		AdresType adresType = objectFactory.createAdresType();
		
		adresType.setIdentificatie(index);
		adresType.setStraatnaam(adres.getStraat());
		adresType.setHuisnummer(adres.getHuisnummer());
		adresType.setHuisnummerToevoeging(adres.getToevoeging());
		adresType.setPostcode(adres.getPostcode());
		adresType.setWoonplaats(adres.getWoonplaats());
		
		responseType.setAdres(adresType);
		response.setAntword(responseType);
		
		LOG.trace("retourneert response");
		return response;
	}

}
