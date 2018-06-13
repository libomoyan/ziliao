package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HbmUtil {
	  private static SessionFactory sf;
	   static{
		   Configuration cfg=new Configuration();
		   cfg.configure();
		   sf=cfg.buildSessionFactory();
	   }
	   public static Session getSession(){
		   return sf.openSession();
	   }
	   public static void closeSessionFactory(){
		   if(!sf.isClosed()) sf.close();
	   }
}
