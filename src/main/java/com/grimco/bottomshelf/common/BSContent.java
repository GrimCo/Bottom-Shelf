package com.grimco.bottomshelf.common;

import com.grimco.bottomshelf.common.items.ItemDrink;
import com.grimco.bottomshelf.common.items.ItemDrink.Serving;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

@EventBusSubscriber
public class BSContent
{
	public static ArrayList<Item> registeredItems = new ArrayList<>();
	
	public static ItemDrink bottleIcon;
	public static ItemDrink bottleAldebranWhiskey;
	public static ItemDrink bottleJanxSpirit;
	public static ItemDrink cocktailPGB;
	
	public static void onPreInit()
	{
		
		bottleIcon = new ItemDrink("icon", Serving.BOTTLE, Integer.decode("#7c5b42"));
		bottleIcon.setHidden();
		
		bottleAldebranWhiskey = new ItemDrink("aldebran_whiskey",
				Serving.BOTTLE,
				1807410,
				new PotionEffect(MobEffects.HEALTH_BOOST, 1800, 0),
				new PotionEffect(MobEffects.NAUSEA, 60));
		
		bottleJanxSpirit = new ItemDrink("janx_spirit",
				Serving.BOTTLE,
				Integer.decode("#bff7ff"),
				new PotionEffect(MobEffects.UNLUCK, 600, 0));
		
		cocktailPGB = new ItemDrink("pangalactic_gargle_blaster",
				Serving.COCKTAIL_CLOUDY,
				Integer.decode("#6fe2f2"),
				new PotionEffect(MobEffects.LUCK, 1800, 0),
				new PotionEffect(MobEffects.ABSORPTION, 600, 0),
				new PotionEffect(MobEffects.WITHER, 60, 0));
		
	}
	
	@SubscribeEvent
	public static void onRegistryEvent(RegistryEvent.Register<Item> event)
	{
		for(Item item : registeredItems)
		{
			event.getRegistry().register(item);
		}
	}
}
