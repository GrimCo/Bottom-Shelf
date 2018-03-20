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
public class Content
{
	public static ArrayList<Item> registeredItems = new ArrayList<>();
	
	public static ItemDrink bottleIcon;
	
	public static ItemDrink bottleSeltzer;
	public static ItemDrink bottleAldebranWhiskey;
	public static ItemDrink bottleJanxSpirit;
	
	public static ItemDrink shotFirewater;
	public static ItemDrink shotRoadkillMalt;
	
	public static ItemDrink cocktailMartini;
	public static ItemDrink cocktailMartiniDirty;
	public static ItemDrink cocktailPGB;
	public static ItemDrink cocktailFizzyLifting;
	public static ItemDrink cocktailDirtyShirley;
	
	public static ItemDrink jugEHDew;
	public static ItemDrink jugHogSpit;
	public static ItemDrink jugBrivari;
	
	public static ItemDrink wineglassShaneyat;
	public static ItemDrink wineglassScrewdriver;
	
	public static void onPreInit()
	{
		
		bottleIcon = new ItemDrink("icon", Serving.BOTTLE, Integer.decode("#7c5b42"));
		bottleIcon.setHidden();
		
		initBottles();
		initCocktails();
		initJugs();
		initShots();
		initWineGlasses();
	}
	
	public static void initBottles()
	{
		bottleSeltzer = new ItemDrink("seltzer_water",
				Serving.BOTTLE,
				Integer.decode("#4290ff"),
				new PotionEffect(MobEffects.WATER_BREATHING, 3600, 0));
		
		bottleAldebranWhiskey = new ItemDrink("aldebran_whiskey",
				Serving.BOTTLE,
				1807410,
				new PotionEffect(MobEffects.HEALTH_BOOST, 1800, 0),
				new PotionEffect(Effects.INTOXICATION, 300, 1));
		
		bottleJanxSpirit = new ItemDrink("janx_spirit",
				Serving.BOTTLE,
				Integer.decode("#bff7ff"),
				new PotionEffect(MobEffects.LUCK, 600, 0),
				new PotionEffect(Effects.INTOXICATION, 300, 2));
	}
	
	public static void initShots()
	{
		shotFirewater = new ItemDrink("firewater",
				Serving.SHOT,
				Integer.decode("#ff7700"),
				new PotionEffect(MobEffects.FIRE_RESISTANCE, 1800, 1),
				new PotionEffect(Effects.INTOXICATION, 300, 1));
		
		shotRoadkillMalt = new ItemDrink("roadkill_malt",
				Serving.SHOT_CLOUDY,
				Integer.decode("#996633"),
				new PotionEffect(MobEffects.SATURATION, 1800,2),
				new PotionEffect(Effects.INTOXICATION, 900, 3));
	}
	
	public static void initCocktails()
	{
		cocktailFizzyLifting = new ItemDrink("fizzy_lifting",
				Serving.COCKTAIL_CLOUDY,
				Integer.decode("#fce4a4"),
				new PotionEffect(MobEffects.LEVITATION, 1200, 0),
				new PotionEffect(Effects.INTOXICATION, 300, 0));
		
		cocktailPGB = new ItemDrink("pangalactic_gargle_blaster",
				Serving.COCKTAIL_CLOUDY,
				Integer.decode("#6fe2f2"),
				new PotionEffect(MobEffects.LUCK, 1800, 0),
				new PotionEffect(MobEffects.ABSORPTION, 600, 0),
				new PotionEffect(Effects.INTOXICATION, 300, 3));
		
		cocktailMartini = new ItemDrink("martini",
				Serving.COCKTAIL,
				Integer.decode("#C0C0C0"),
				new PotionEffect(Effects.INTOXICATION, 300, 0));
		
		cocktailMartiniDirty = new ItemDrink("martini_dirty",
				Serving.COCKTAIL_CLOUDY,
				Integer.decode("#C0C0C0"),
				new PotionEffect(Effects.INTOXICATION, 300, 1));
		
		cocktailDirtyShirley = new ItemDrink("dirty_shirley",
				Serving.COCKTAIL_CLOUDY,
				Integer.decode("#FFCC66"),
				new PotionEffect(MobEffects.SATURATION, 60,0),
				new PotionEffect(Effects.INTOXICATION, 300,0));
		
	}
	
	public static void initJugs()
	{
		jugEHDew = new ItemDrink("extreme_hills_dew",
				Serving.JUG,
				Integer.decode("#32ff00"),
				new PotionEffect(MobEffects.SPEED, 1200, 3),
				new PotionEffect(MobEffects.HASTE, 1200, 3),
				new PotionEffect(Effects.INTOXICATION, 300, 2));
		
		jugHogSpit = new ItemDrink("hog_spit",
				Serving.JUG,
				Integer.decode("#F5DEB3"),
				new PotionEffect(MobEffects.SATURATION, 300, 0),
				new PotionEffect(Effects.INTOXICATION, 300, 1));
		
		jugBrivari = new ItemDrink("brivari",
				Serving.JUG_CLOUDY,
				Integer.decode("#CC9900"));
	}
	
	public static void initWineGlasses()
	{
		wineglassShaneyat = new ItemDrink("shaneyat",
				Serving.WINE_GLASS,
				Integer.decode("#330033"),
				new PotionEffect(MobEffects.ABSORPTION, 3600, 0),
				new PotionEffect(MobEffects.WITHER, 60, 1));
		
		wineglassScrewdriver = new ItemDrink("screwdriver",
						Serving.WINE_GLASS_CLOUDY,
						Integer.decode("#FFA500"));
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
