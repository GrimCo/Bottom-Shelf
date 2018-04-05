package com.grimco.bottomshelf.common;

import com.grimco.bottomshelf.BottomShelf;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapelessOreRecipe;


@EventBusSubscriber
public class Recipes
{
	@SubscribeEvent
	public static void registerRecipes(RegistryEvent.Register<IRecipe> event)
	{
		
		
		ItemStack waterBottle = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM),PotionTypes.WATER);
		ResourceLocation drinks = new ResourceLocation(BottomShelf.MOD_ID, "drinks");
	
		event.getRegistry().register(new ShapelessOreRecipe(drinks, Content.bottleAldebranWhiskey, waterBottle, Items.NETHER_WART, Items.SUGAR, Items.SLIME_BALL).setRegistryName("aldebranwhiskey"));
		event.getRegistry().register(new ShapelessOreRecipe(drinks, Content.bottleJanxSpirit, waterBottle, Items.NETHER_WART, Items.SUGAR, Items.GOLDEN_CARROT).setRegistryName("janxspirit"));
		event.getRegistry().register(new ShapelessOreRecipe(drinks, Content.bottleSeltzer, waterBottle, Items.GUNPOWDER, Items.GLOWSTONE_DUST).setRegistryName("seltzer"));
		event.getRegistry().register(new ShapelessOreRecipe(drinks, Content.cocktailDirtyShirley, waterBottle, Items.APPLE, Items.SUGAR, Items.CARROT).setRegistryName("dirtyshirley"));
		event.getRegistry().register(new ShapelessOreRecipe(drinks, Content.cocktailFizzyLifting, waterBottle, Items.GLOWSTONE_DUST, Items.SUGAR, Items.NETHER_WART).setRegistryName("fizzylifting"));
		event.getRegistry().register(new ShapelessOreRecipe(drinks, Content.cocktailMartini, waterBottle, Items.NETHER_WART, Items.BEETROOT).setRegistryName("martini"));
		event.getRegistry().register(new ShapelessOreRecipe(drinks, Content.cocktailMartiniDirty, new ItemStack(Content.cocktailMartini), Items.GUNPOWDER).setRegistryName("martini_dirty"));
		event.getRegistry().register(new ShapelessOreRecipe(drinks, Content.cocktailPGB, new ItemStack(Content.bottleJanxSpirit), Items.GOLDEN_APPLE, Items.REDSTONE, Items.BRICK).setRegistryName("pangalacticgargleblaster"));
		event.getRegistry().register(new ShapelessOreRecipe(drinks, Content.shotFirewater, waterBottle, Items.BLAZE_POWDER, Items.REDSTONE, Items.NETHER_WART, Items.POTATO).setRegistryName("firewater"));
		event.getRegistry().register(new ShapelessOreRecipe(drinks, Content.shotRoadkillMalt, waterBottle, Items.NETHER_WART, Items.ROTTEN_FLESH).setRegistryName("roadkillmalt"));
		event.getRegistry().register(new ShapelessOreRecipe(drinks, Content.jugBrivari, waterBottle, Items.NETHER_WART, Items.BEETROOT).setRegistryName("brivari"));
		event.getRegistry().register(new ShapelessOreRecipe(drinks, Content.jugEHDew, waterBottle, Items.NETHER_WART, Items.WHEAT, Items.SUGAR, Items.REDSTONE).setRegistryName("mountaindew"));
		event.getRegistry().register(new ShapelessOreRecipe(drinks, Content.jugHogSpit, waterBottle, Items.NETHER_WART, Items.CARROT, Items.SUGAR).setRegistryName("hogspit"));
		event.getRegistry().register(new ShapelessOreRecipe(drinks, Content.wineglassScrewdriver, waterBottle, Items.NETHER_WART, Items.APPLE, Items.SUGAR).setRegistryName("screwdriver"));
		event.getRegistry().register(new ShapelessOreRecipe(drinks, Content.wineglassShaneyat, waterBottle, Blocks.WATERLILY, Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM).setRegistryName("shaneyat"));
	}
}
