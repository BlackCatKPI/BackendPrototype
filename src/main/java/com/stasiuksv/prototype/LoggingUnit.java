package com.stasiuksv.prototype;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggingUnit 
{
	public static Logger log;
	public static LoggingUnit instance = new LoggingUnit();
	public LoggingUnit()
	{
		try
		{
			PropertyConfigurator.configure(getClass().getResourceAsStream("/log4j.xml"));
			log = Logger.getLogger("filelogger");
			log.info("Logger Unit: ready");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}
