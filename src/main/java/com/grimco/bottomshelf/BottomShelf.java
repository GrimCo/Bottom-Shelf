package com.grimco.bottomshelf;

import com.grimco.bottomshelf.common.Content;
import com.grimco.bottomshelf.common.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = BottomShelf.MOD_ID, name = BottomShelf.MOD_NAME, version = BottomShelf.MOD_VERS)
public class BottomShelf
{
	public static final String MOD_ID = "bottomshelf";
	public static final String MOD_NAME = "GrimCo's Bottom Shelf";
	public static final String MOD_VERS = "1.0.0";
	
	@Instance(BottomShelf.MOD_ID)
	public BottomShelf instance;
	
	private final static String PROXY_SERVER = "com.grimco.bottomshelf.client.ClientProxy" ;
	private final static String PROXY_COMMON = "com.grimco.bottomshelf.common.CommonProxy" ;
	
	@SidedProxy(serverSide = PROXY_COMMON , clientSide = PROXY_SERVER)
	public static CommonProxy proxy;
	
	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{
		Log.onPreInit(event);
		
		proxy.onPreInit(event);
	}
	
	@EventHandler
	public void onInit(FMLInitializationEvent event)
	{
	
	}
	
	@EventHandler
	public void onPostInit(FMLPostInitializationEvent event)
	{
	
	}
	
	
	public static CreativeTabs creativeTab = new CreativeTabs(BottomShelf.MOD_ID)
	{
		@Override
		public ItemStack getTabIconItem()
		{
			return new ItemStack(Content.bottleIcon);
		}
	};
}
