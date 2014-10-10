package nl.kadaster.data;

import java.util.List;

import nl.kadaster.core.domain.Adres;

public interface AdresRepository {
	public void storeAdres(Adres adres);
	public List<Adres> getAdressen();
	public Adres getAdres(int index);
	public void removeAdres(Adres adres) throws Exception;
	public void updateAdres(nl.kadaster.core.domain.Adres adres) throws Exception;
}
