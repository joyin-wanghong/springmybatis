package log4j;  

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jG {
	// private static Logger log =Logger.getLogger(Log4jG.class);  
	private static Logger log=LogManager.getLogger(Log4jG.class.getName());
/*	private static Logger log1=LogManager.getLogger("myTest1");
	private static Logger log2=LogManager.getLogger("myTest2");
	private static Logger log3=LogManager.getLogger("myTest3");*/
	
	public static void main(String[] args){
		//  BasicConfigurator.configure();
		PropertyConfigurator.configure("c:\\log4j.properties");
		log.debug("debug message");
		log.info("info message");
		log.error("error message");
		
	/*	log1.debug("test1  debug");
		log1.info("test1  info message");
		log1.error("test1  error message");
		  
		log2.debug("test2  debug");
		log2.info("test2  info message");
		log2.error("test2  error message");
		    
		log3.debug("test3  debug");
		log3.info("test3  info message");
		log3.error("test3  error message");*/
	}  
}  