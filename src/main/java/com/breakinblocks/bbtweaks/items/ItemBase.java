package com.breakinblocks.bbtweaks.items;

/*
 * Base item class for getting standard things done with quickly.
 * Extend this for pretty much every item you make.
 */

import java.util.List;

import com.breakinblocks.bbtweaks.BBTweaks;
import com.breakinblocks.bbtweaks.ModInformation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@SuppressWarnings("unused")
public class ItemBase extends Item {

	protected String setColor = "";
	protected String setInfo = "";

	// If you aren't setting multiple textures for your item. IE: Non-Metadata
	// items.
	public ItemBase(String unlocName, String textureName) {
		super();

		setUnlocalizedName(ModInformation.ID + "." + unlocName);
		setCreativeTab(BBTweaks.tabBaseMod);

	}

	// If you are setting multiple textures for your item. IE: Metadata items.
	public ItemBase(String unlocName) {
		super();

		setUnlocalizedName(ModInformation.ID + "." + unlocName);
		setCreativeTab(BBTweaks.tabBaseMod);
	}

	@SideOnly(Side.CLIENT)
	protected String setToolTipData(ItemStack IStack) {
		return this.setColor + this.setInfo;
	}

}
