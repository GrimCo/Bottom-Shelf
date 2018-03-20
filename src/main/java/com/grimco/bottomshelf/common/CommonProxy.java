package com.grimco.bottomshelf.common;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy
{
	public void onPreInit(FMLPreInitializationEvent event)
	{
		Effects.onPreInit();
		Content.onPreInit();
	}
}
