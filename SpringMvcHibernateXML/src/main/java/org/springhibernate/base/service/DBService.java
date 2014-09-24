package org.springhibernate.base.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springhibernate.base.HomeController;
import org.springhibernate.base.model.Adres;


@Transactional
public class DBService implements DBServiceInterface<Adres>{
	
	static Logger Logger = LoggerFactory.getLogger(DBService.class); 	
	
	// this is required in order for JAXB to make an instance of the DBService class
	/*public DBService(){		
	}*/		
	
	private SessionFactory sessionFactory;
	
	// Hibernate’s SessionFactory object is injected via constructor by Spring.
	public DBService(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
		Logger.debug("SESSION FACTORY HAS BEEN SET");
	}

	// Spring will inject transaction support code into the method
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Adres> listItems() {		
		Logger.debug("generate a list of addresses database");
		List<Adres> list = sessionFactory.getCurrentSession().createQuery("from Adres").list();		
		return list;		
	}

	@Override
	@Transactional
	public void saveOrUpdateItem(Adres adres) { // hier zou je check kunnen uitvoeren of alle data goed is ingevuld		
		Logger.debug("save or update an address to database");
		sessionFactory.getCurrentSession().saveOrUpdate(adres);		
	}

	
	@Override
	@Transactional
	public Adres get(int id) {		
		String hql = "from Adres where id=" + id;
		Query query = null;
		try{
			Logger.debug("get adres using id from database");
			query = sessionFactory.getCurrentSession().createQuery(hql);			
		}catch(Exception e){
			System.err.println("Session Factory probeert getCurrentSession aan te roepen en Query te creeeren.");
		}
        @SuppressWarnings("unchecked")
        List<Adres> listAdres = (List<Adres>) query.list();
         
        if (listAdres != null && !listAdres.isEmpty()) {
            return listAdres.get(0);
        }
         
        return null;	
	}	

	@Override
	@Transactional
	public void delete(int id) {
		
		Adres adresToDelete = new Adres();
		adresToDelete.setId(id);
		Logger.debug("delete an address using id from database");
		sessionFactory.getCurrentSession().delete(adresToDelete);
	}

	@Override
	public boolean isEmpty(int id) {		
				
		return get(id) == null;
	}

	

	
	
}
