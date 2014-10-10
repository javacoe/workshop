package nl.kadaster.presentatie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import nl.kadaster.business.BusinessService;
import nl.kadaster.web.domain.Adres;

/**
 * Servlet implementation class SaveAdresServlet
 */
@WebServlet (description = "Try to save an Adres object", urlPatterns = { "/SaveAdres" })
public class SaveAdresServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired(required=true)
	private BusinessService businessService;

	public void setBusinessService(BusinessService businessService) {
		this.businessService = businessService;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String straat = request.getParameter( "straat" );
		String huisnummer = request.getParameter( "huisnummer" );
		String toevoeging = request.getParameter( "toevoeging" );
		String postcode = request.getParameter( "postcode" );
		String woonplaats = request.getParameter( "woonplaats" );
		
		Adres adres;
		boolean[] errorCodes;
		StringBuffer errors = new StringBuffer();
		
		if (session.getAttribute("adres") != null) {
			adres = (Adres) session.getAttribute("adres");
		} else { // Er is nog geen adres in de sessie
			adres = new Adres();
			session.setAttribute("adres", adres);
		}
		
		errorCodes = businessService.saveAdres(adres, straat, huisnummer, toevoeging, postcode, woonplaats);
		
		// Bepalen of het opslaan goed of fout is gegaan en daarvandaan naar de volgende pagina
		if (errorCodes[0]) {
			if (errorCodes[1])
				errors.append("<li>\"" + straat + "is een onjuiste invoer voor straat.</li>");
			if (errorCodes[2])
				errors.append("<li>\"" + huisnummer + "\" is een onjuiste invoer voor huisnummer.</li>");
			if (errorCodes[3])
				errors.append("<li>\"" + toevoeging + "\" is een onjuiste invoer voor toevoeging.</li>");
			if (errorCodes[4])
				errors.append("<li>\"" + postcode + "\" is een onjuiste invoer voor postcode.</li>");
			if (errorCodes[5])
				errors.append("<li>\"" + woonplaats + "\" is een onjuiste invoer voor woonplaats.</li>");
			session.setAttribute( "errorCodes", errorCodes);
	    	session.setAttribute( "errors", errors.toString());
	    	request.getRequestDispatcher("RequestAdres.jsp").forward(request,response);
//	    	response.sendRedirect("RequestAdres.jsp");
//	    	response.setHeader("Location", "RequestAdres.jsp");
//	    	response.setHeader("Refresh", "0; RequestAdres.jsp");
		} else { // invoer was correct, is opgeslagen en zit in Adres object adres
	    	response.sendRedirect("SavedAdres.jsp");
		}
	}

}
