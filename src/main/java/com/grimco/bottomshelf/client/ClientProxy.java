package com.grimco.bottomshelf.client;

import com.grimco.bottomshelf.BottomShelf;
import com.grimco.bottomshelf.common.Content;
import com.grimco.bottomshelf.common.CommonProxy;
import com.grimco.bottomshelf.common.items.ItemDrink;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(value = Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	
	@SubscribeEvent
	public static void onModelRegistryEvent(ModelRegistryEvent event)
	{
		for(Item item : Content.registeredItems)
		{
			
			ResourceLocation location = item.getRegistryName();
			
			if(item instanceof ItemDrink)
			{
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(BottomShelf.MOD_ID, ((ItemDrink)item).getServing().getName()), "inventory"));
			}
			else
			{
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(location, "inventory"));
			}
		}
	}
	
	@SubscribeEvent
	public static void onRegisterItemColorHandler(ColorHandlerEvent.Item event)
	{
	
		ItemColors itemColors = event.getItemColors();
		
		for(Item item : Content.registeredItems)
		{
			if(item instanceof ItemDrink)
			{
				itemColors.registerItemColorHandler((stack, tintIndex)->{
					if(stack.getItem() instanceof ItemDrink)
					{
						ItemDrink drink1=(ItemDrink)stack.getItem();
						return tintIndex==0 ? drink1.getColorTint():16777215;
					}
					
					return 16777215;
				}, item);
			}
		}
	}
}
