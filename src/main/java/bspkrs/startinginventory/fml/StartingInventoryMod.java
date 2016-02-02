package bspkrs.startinginventory.fml;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.Metadata;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import bspkrs.startinginventory.CommandStartingInv;
import bspkrs.startinginventory.StartingInventory;
import bspkrs.util.Const;
import net.minecraftforge.fml.common.network.NetworkCheckHandler;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Map;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = "@MOD_VERSION@", dependencies = "required-after:bspkrsCore@[@BSCORE_VERSION@,)", useMetadata = true, updateJSON = Const.VERSION_URL_BASE + Reference.MODID + Const.VERSION_URL_EXT)
public class StartingInventoryMod
{
    public MinecraftServer             server;

    @Metadata(value = Reference.MODID)
    public static ModMetadata          metadata;

    @SidedProxy(clientSide = Reference.PROXY_CLIENT, serverSide = Reference.PROXY_COMMON)
    public static CommonProxy          proxy;

    @Instance(value = Reference.MODID)
    public static StartingInventoryMod instance;

    @NetworkCheckHandler
    public boolean checkModList(Map<String, String> versions, Side side)
    {
        return true;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.registerClientTicker();
        MinecraftForge.EVENT_BUS.register(new NetworkHandler());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        StartingInventory.init(null);
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        instance.server = event.getServer();
        event.registerServerCommand(new CommandStartingInv());
    }

    @EventHandler
    public void serverStopping(FMLServerStoppingEvent event)
    {
        instance.server = null;
    }
}
