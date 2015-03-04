package cz.tomascejka.learn.hibernate.util;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
  
public class HibernateUtil {
  
    private Map<String,SessionFactory> sessions = new HashMap<>();
  
    private SessionFactory buildSessionFactory(String configurationFileName) {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure(configurationFileName).buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
  
    /**
     * @param configurationFileName, napr. hibernate.cfg.xml
     * @return session nakofigurovanou dle souboru
     */
    public SessionFactory getSessionFactory(String configurationFileName) 
    {
    	if(sessions.containsKey(configurationFileName))
    	{
    		System.out.println("CREATE session factory with file="+configurationFileName);
    		return sessions.get(configurationFileName);
    	}
    	System.out.println("GET session factory with file="+configurationFileName);
        return buildSessionFactory(configurationFileName);
    }
  
    /**
     * 
     * @param configurationFileName
     */
    public void shutdown(String configurationFileName) 
    {
        // Close caches and connection pools
        getSessionFactory(configurationFileName).close();
        System.out.println("REMOVE session factory with file="+configurationFileName);
        sessions.remove(configurationFileName);
    }
  
}
