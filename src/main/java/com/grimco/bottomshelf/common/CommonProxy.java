package com.grimco.bottomshelf.common;

import com.grimco.bottomshelf.common.BSContent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy
{
	public void onPreInit(FMLPreInitializationEvent event)
	{
		BSContent.onPreInit();
	}
}
