package bspkrs.startinginventory.fml;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class NetworkHandler
{
    @SubscribeEvent
    public void playerLoggedIn(PlayerLoggedInEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new SIGiveItemTicker(10, event.player));
    }
}
