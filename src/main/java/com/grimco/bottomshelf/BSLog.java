package com.grimco.bottomshelf;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

public class BSLog
{
	
	private static Logger logger;
	
	public static void onPreInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
	}
}
