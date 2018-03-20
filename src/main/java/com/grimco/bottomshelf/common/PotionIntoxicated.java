package com.grimco.bottomshelf.common;

import com.grimco.bottomshelf.BottomShelf;
import com.grimco.bottomshelf.Log;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

import java.util.ArrayList;
import java.util.List;

public class PotionIntoxicated extends Potion
{
	@ObjectHolder("harvestcraft:coffeeitem")
	public static final Item hcCoffe = null;
	
	@ObjectHolder("harvestcraft:coffeeconlecheitem")
	public static final Item hcCoffeConLeche= null;
	
	ArrayList<ItemStack> curatives = new ArrayList<>();
	private String name;
	
	public PotionIntoxicated()
	{
		super(true, Integer.decode("#59FA31"));
		
		this.name = "potion.intoxicated";
		this.setRegistryName(BottomShelf.MOD_ID, "intoxicated");
	}
	
	@Override
	public String getName()
	{
		return name;
	}
	
	@Override
	public List<ItemStack> getCurativeItems()
	{
		List<ItemStack> ret = curatives;
		
		if(Loader.isModLoaded("harvestcraft"))
			ret.add(new ItemStack(hcCoffe));
		
		if(Loader.isModLoaded("harvestcraft"))
			ret.add(new ItemStack(hcCoffeConLeche));
		
		return ret;
	}
	
	public void addCurativeItem(ItemStack stack)
	{
		if(!stack.isEmpty())
			curatives.add(stack);
		else
			Log.logger.info("Attmepted to add an invalid Curative item for Intoxication: {}", stack);
	}
}
