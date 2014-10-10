package nl.kadaster.data;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

//import nl.kadaster.data.util.HibernateUtil;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.kadaster.core.domain.Adres;

public class AdresDAO implements AdresRepository {
	
	private static final Logger LOG = LoggerFactory.getLogger(AdresDAO.class);
	
	private SessionFactory sessionFactory;
	
	@Override
	public void storeAdres(Adres adres) {
		LOG.debug("store Adres: " + adres);
		Session session = sessionFactory.getCurrentSession();
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// Eventueel eerst nog checken of adres niet al in de database voorkomt:
			// postcode + huisnummer + toevoeging moeten uniek zijn
			session.save(adres);
			tx.commit();
		} catch (Exception e) {
			if (tx!=null)
				tx.rollback();
			e.printStackTrace();
//		} finally {
//			session.close();
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Adres> getAdressen() {
		LOG.trace("calling getAdressen() from AdresDAO");
		List<Adres> result = null;

		Session session = sessionFactory.getCurrentSession();
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = session.createQuery("from Adres").list();
			tx.commit();
		} catch (Exception e) {
			if (tx!=null)
				tx.rollback();
			e.printStackTrace();
//		} finally {
//			session.close();
		}
		
		// sorteren op Adres.id ?
		
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Adres getAdres(int index) {
		// retourneert null als adres met index niet gevonden is
		
		LOG.debug("getAdres with index " + index);
		List<Adres> lijst = null;
		Adres adres = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			lijst = ((List<Adres>) session.createQuery("from Adres where id = " + index).list());
			tx.commit();
		} catch (Exception e) {
			if (tx!=null)
				tx.rollback();
			e.printStackTrace();
		}
		
		if (lijst.size() != 0)
			adres = lijst.get(0);
		
		LOG.debug("returning Adres: " + adres);
		return adres;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void removeAdres(Adres adres) throws Exception {		
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
//			Adres opgeslagenAdres = (Adres) session.get(Adres.class, adres.getId());
//			if (opgeslagenAdres.equals(adres)) // of (session.contains(adres))
//				session.delete(opgeslagenAdres);
//			else
//				throw new RuntimeException("Het te verwijderen adres staat niet in de database.");
//			System.out.println(session.contains(adres));
			session.delete(adres); // indien het er niet in staat wordt er een HibernateException gegooid
			tx.commit();
		} catch (Exception e) {
			if (tx!=null)
				tx.rollback();
			e.printStackTrace();
			// iets laten gooien?
		}
	}

	@Override
	public void updateAdres(Adres adres) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
//			Adres opgeslagenAdres = (Adres) session.get(Adres.class, adres.getId());
//			if (opgeslagenAdres.equals(adres)) // of (session.contains(adres))
//				session.delete(opgeslagenAdres);
//			else
//				throw new RuntimeException("Het te verwijderen adres staat niet in de database.");
			session.update(adres); // indien het er niet in staat wordt er een HibernateException gegooid
			tx.commit();
		} catch (Exception e) {
			if (tx!=null)
				tx.rollback();
			e.printStackTrace();
			// iets laten gooien?
		}
	}
}
