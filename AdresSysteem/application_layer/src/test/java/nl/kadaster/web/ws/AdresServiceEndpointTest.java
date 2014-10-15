package nl.kadaster.web.ws;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import nl.kadaster.business.BusinessService;
import nl.kadaster.web.domain.Adres;
import nl.kadaster.web.ws.AdresServiceEndpoint;

import org.easymock.Capture;
import org.easymock.CaptureType;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.capgemini.adres.schema.AdresRaadplegenRequest;
import com.capgemini.adres.schema.AdresRaadplegenRequestType;
import com.capgemini.adres.schema.AdresRaadplegenResponse;
import com.capgemini.adres.schema.ObjectFactory;

@RunWith(EasyMockRunner.class)
public class AdresServiceEndpointTest {

	@TestSubject
	private AdresServiceEndpoint endpoint = new AdresServiceEndpoint();
	
	@Mock
	private BusinessService businessServiceMock;
	
	@Mock
	private ObjectFactory objectFactoryMock;
	
	private static ObjectFactory objectFactory;
	
	@BeforeClass
	public static void setUpClass() {
		objectFactory = new ObjectFactory();
	}
	
	@Test
	public void testGetAdres(){
		
		//init parameters, expectations' return objects and captures
		AdresRaadplegenRequest request = new AdresRaadplegenRequest();
		request.setVerzoek(new AdresRaadplegenRequestType());
		request.getVerzoek().setIdentificatie(1);
		
		
		Adres adres = new Adres();
		adres.setWoonplaats("Utrecht");
		
		Capture<Integer> captured = new Capture<>(CaptureType.ALL);
		
		//expectations
		EasyMock.expect(businessServiceMock.getAdres(EasyMock.captureInt(captured))).andReturn(adres);
		
		EasyMock.replay(businessServiceMock);
		
		EasyMock.expect(objectFactoryMock.createAdresRaadplegenResponse())
				.andReturn(objectFactory.createAdresRaadplegenResponse());
		EasyMock.expect(objectFactoryMock.createAdresRaadplegenResponseType())
				.andReturn(objectFactory.createAdresRaadplegenResponseType());
		EasyMock.expect(objectFactoryMock.createAdresType())
				.andReturn(objectFactory.createAdresType());
		EasyMock.replay(objectFactoryMock);
		
		//method to test
		AdresRaadplegenResponse raadplegenResponse = endpoint.getAdres(request);
		
		
		//verification of the mocks
		EasyMock.verify(businessServiceMock);
		
		//assertions
		assertTrue(captured.hasCaptured());
		assertEquals(Integer.valueOf(1), captured.getValue());
		
		assertNotNull(raadplegenResponse);
		assertNotNull(raadplegenResponse.getAntword());
		assertNotNull(raadplegenResponse.getAntword().getAdres());
		assertNotNull(raadplegenResponse.getAntword().getAdres().getWoonplaats());
		assertEquals("Utrecht", raadplegenResponse.getAntword().getAdres().getWoonplaats());
	}
	
}
