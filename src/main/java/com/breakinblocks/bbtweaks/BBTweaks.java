package com.breakinblocks.bbtweaks;

/*
 * Check all the classes for (hopefully) detailed descriptions of what it does. There will also be tidbits of comments throughout the codebase.
 * If you wish to add a description to a class, or extend/change an existing one, submit a PR with your changes.
 */


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.breakinblocks.bbtweaks.blocks.BlockRecipeRegistry;
import com.breakinblocks.bbtweaks.blocks.BlockRegistry;
import com.breakinblocks.bbtweaks.client.gui.CreativeTabBaseMod;
import com.breakinblocks.bbtweaks.client.gui.GuiHandler;
import com.breakinblocks.bbtweaks.command.CommandBB;
import com.breakinblocks.bbtweaks.command.CommandSayLocation;
import com.breakinblocks.bbtweaks.items.ItemRecipeRegistry;
import com.breakinblocks.bbtweaks.proxies.CommonProxy;
import com.breakinblocks.bbtweaks.util.EventHandler;
import com.breakinblocks.bbtweaks.util.OreDictHandler;
import com.breakinblocks.bbtweaks.util.TextHelper;

@SuppressWarnings("unused")
@Mod(modid = ModInformation.ID, name = ModInformation.NAME, version = ModInformation.VERSION, dependencies = ModInformation.DEPEND, guiFactory = ModInformation.GUIFACTORY)
public class BBTweaks {
	 
	public static final String MODID = "BBTweaks";

	public static       boolean isDevEnv     = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
	 
    @SidedProxy(clientSide = ModInformation.CLIENTPROXY, serverSide = ModInformation.COMMONPROXY)
    public static CommonProxy proxy;

    public static CreativeTabs tabBaseMod = new CreativeTabBaseMod(ModInformation.ID + ".creativeTab");
    public static Logger logger = LogManager.getLogger(ModInformation.NAME);

    @Mod.Instance
    public static BBTweaks instance;

    @SuppressWarnings("deprecation")
	@Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger.info(TextHelper.localize("info." + ModInformation.ID + ".console.load.preInit"));

        ConfigHandler.init(event.getSuggestedConfigurationFile());


        OreDictHandler.registerOreDict();
        FMLCommonHandler.instance().bus().register(new EventHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info(TextHelper.localize("info." + ModInformation.ID + ".console.load.init"));

        ItemRecipeRegistry.registerItemRecipes();
        BlockRecipeRegistry.registerBlockRecipes();
        
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        logger.info(TextHelper.localize("info." + ModInformation.ID + ".console.load.postInit"));
        
        	 
    }
    
	@Mod.EventHandler
	public void onFMLServerStart(FMLServerStartingEvent event)
	{
		
		event.registerServerCommand(new CommandSayLocation());
		event.registerServerCommand(new CommandBB());
	
	}
    
}