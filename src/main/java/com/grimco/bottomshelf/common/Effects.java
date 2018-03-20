package com.grimco.bottomshelf.common;

import com.grimco.bottomshelf.BottomShelf;
import com.grimco.bottomshelf.Log;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber
public class Effects
{
	
	public static Potion INTOXICATION;
	
	public static void onPreInit()
	{
		INTOXICATION = new PotionIntoxicated();
	}
	
	@SubscribeEvent
	public static void onPotionRegistryEvent(RegistryEvent.Register<Potion> event)
	{
		event.getRegistry().register(INTOXICATION);
	}
	
	
	@SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event)
	{
		if(event.getEntityLiving().getActivePotionEffect(INTOXICATION)!=null)
		{
			EntityLivingBase entityLivingBase = event.getEntityLiving();
			PotionEffect intoxEffect = entityLivingBase.getActivePotionEffect(INTOXICATION);
			
			if(entityLivingBase.moveForward>0 && (!(entityLivingBase instanceof EntityPlayer) || !((EntityPlayer)entityLivingBase).capabilities.isCreativeMode))
			{
				entityLivingBase.moveRelative((float)(intoxEffect.getAmplifier()*Math.sin(intoxEffect.getDuration() / 4)), 0, 0, .075F);
			}
		}
	}
}
