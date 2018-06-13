package asd;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class createDB {  
    public static void main(String[] args) {  
        //×°ÔØÅäÖÃÎÄ¼ş  
        Configuration cfg = new Configuration().configure();  
        SchemaExport export = new SchemaExport(cfg);  
        export.create(true, true);  
    }  
}  