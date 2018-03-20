package com.grimco.bottomshelf.common.items;

import com.google.common.collect.Lists;
import com.grimco.bottomshelf.BottomShelf;
import com.grimco.bottomshelf.common.Content;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Tuple;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ItemDrink extends ItemFood
{
	private String name;
	private Serving serving;
	private int colorTint;
	private PotionEffect[] effects;
	
	private boolean isHidden = false;
	
	public ItemDrink(String name, Serving serving, int color, PotionEffect... effects)
	{
		super(0,0,false);
		
		this.name = name;
		this.serving = serving;
		this.colorTint = color;
		this.effects = effects.length>0 ? effects : null;
		this.setAlwaysEdible();
		this.maxStackSize = 16;
		
		setUnlocalizedName(BottomShelf.MOD_ID + "." + name);
		setRegistryName(name);
		
		setCreativeTab(BottomShelf.creativeTab);
		
		Content.registeredItems.add(this);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.DRINK;
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
	{
		EntityPlayer entityplayer = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;
		
		if (entityplayer instanceof EntityPlayerMP)
		{
			CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
		}
		
		if (!worldIn.isRemote)
		{
			for (PotionEffect potioneffect : ((ItemDrink)stack.getItem()).getEffects())
			{
				if (potioneffect.getPotion().isInstant())
				{
					potioneffect.getPotion().affectEntity(entityplayer, entityplayer, entityLiving, potioneffect.getAmplifier(), 1.0D);
				}
				else
				{
					entityLiving.addPotionEffect(new PotionEffect(potioneffect));
				}
			}
		}
		
		if (entityplayer != null)
		{
			entityplayer.addStat(StatList.getObjectUseStats(this));
		}
		
		if (entityplayer == null || !entityplayer.capabilities.isCreativeMode)
		{
			if (stack.isEmpty())
			{
				return new ItemStack(Items.GLASS_BOTTLE);
			}
			
			if (entityplayer != null)
			{
				entityplayer.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
			}
		}
		
		if (entityplayer == null || !entityplayer.capabilities.isCreativeMode)
		{
			stack.shrink(1);
		}
		
		return stack;
	}
	
	
	@Override
	protected boolean isInCreativeTab(CreativeTabs targetTab)
	{
		return targetTab == BottomShelf.creativeTab && !isHidden;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(ChatFormatting.ITALIC + I18n.format(getUnlocalizedName()+".desc"));
		addPotionTooltip(stack, tooltip, 1.0f);
	}
	
	@SideOnly(Side.CLIENT)
	public static void addPotionTooltip(ItemStack itemIn, List<String> lores, float durationFactor)
	{
		PotionEffect[] list = ((ItemDrink )itemIn.getItem()).getEffects();
		List<Tuple<String, AttributeModifier>> list1 = Lists.<Tuple<String, AttributeModifier>>newArrayList();
		
		if (list == null)
		{
			String s = I18n.format("effect.none").trim();
			lores.add(TextFormatting.GRAY + s);
		}
		else
		{
			for (PotionEffect potioneffect : list)
			{
				String s1 = I18n.format(potioneffect.getEffectName()).trim();
				Potion potion = potioneffect.getPotion();
				Map<IAttribute, AttributeModifier> map = potion.getAttributeModifierMap();
				
				if (!map.isEmpty())
				{
					for (Entry<IAttribute, AttributeModifier> entry : map.entrySet())
					{
						AttributeModifier attributemodifier = entry.getValue();
						AttributeModifier attributemodifier1 = new AttributeModifier(attributemodifier.getName(), potion.getAttributeModifierAmount(potioneffect.getAmplifier(), attributemodifier), attributemodifier.getOperation());
						list1.add(new Tuple(((IAttribute)entry.getKey()).getName(), attributemodifier1));
					}
				}
				
				if (potioneffect.getAmplifier() > 0)
				{
					s1 = s1 + " " + I18n.format("potion.potency." + potioneffect.getAmplifier()).trim();
				}
				
				if (potioneffect.getDuration() > 20)
				{
					s1 = s1 + " (" + Potion.getPotionDurationString(potioneffect, durationFactor) + ")";
				}
				
				if (potion.isBadEffect())
				{
					lores.add(TextFormatting.RED + s1);
				}
				else
				{
					lores.add(TextFormatting.BLUE + s1);
				}
			}
		}
		
		if (!list1.isEmpty())
		{
			lores.add("");
			lores.add(TextFormatting.DARK_PURPLE + I18n.format("potion.whenDrank"));
			
			for (Tuple<String, AttributeModifier> tuple : list1)
			{
				AttributeModifier attributemodifier2 = tuple.getSecond();
				double d0 = attributemodifier2.getAmount();
				double d1;
				
				if (attributemodifier2.getOperation() != 1 && attributemodifier2.getOperation() != 2)
				{
					d1 = attributemodifier2.getAmount();
				}
				else
				{
					d1 = attributemodifier2.getAmount() * 100.0D;
				}
				
				if (d0 > 0.0D)
				{
					lores.add(TextFormatting.BLUE + I18n.format("attribute.modifier.plus." + attributemodifier2.getOperation(), ItemStack.DECIMALFORMAT.format(d1), I18n.format("attribute.name." + (String)tuple.getFirst())));
				}
				else if (d0 < 0.0D)
				{
					d1 = d1 * -1.0D;
					lores.add(TextFormatting.RED + I18n.format("attribute.modifier.take." + attributemodifier2.getOperation(), ItemStack.DECIMALFORMAT.format(d1), I18n.format("attribute.name." + (String)tuple.getFirst())));
				}
			}
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	public Serving getServing()
	{
		return serving;
	}
	
	public int getColorTint()
	{
		return colorTint;
	}
	
	public PotionEffect[] getEffects()
	{
		return effects;
	}
	
	public boolean setHidden()
	{
		this.isHidden = true;
		
		return isHidden;
	}
	
	public enum Serving implements IStringSerializable
	{
		BOTTLE,
		BOTTLE_CLOUDY,
		COCKTAIL,
		COCKTAIL_CLOUDY,
		SHOT,
		SHOT_CLOUDY,
		WINE_GLASS,
		WINE_GLASS_CLOUDY,
		JUG,
		JUG_CLOUDY;
		
		@Override
		public String getName()
		{
			return this.toString();
		}
	}
}
