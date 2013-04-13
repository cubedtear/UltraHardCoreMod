package uhc;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

/**
 * ModUHC
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@Mod(name = "UltraHardCore", modid = "UHC", version = "1.0")
public class ModUHC {
	
	static Handler events = new Handler();

	@Instance("UHC")
	public static ModUHC afm = new ModUHC();

	@ServerStarting
	public void serverStarting(FMLServerStartingEvent e) {
		e.registerServerCommand(new CommandUHC());
	}
	
	@PreInit
	public static void preInit(FMLPreInitializationEvent event){
		UHCConfig.init(new Configuration(event.getSuggestedConfigurationFile()));
	}

	@Init
	public static void init(FMLInitializationEvent event) {
		TickRegistry.registerTickHandler(events, Side.SERVER);
		MinecraftForge.EVENT_BUS.register(events);
	}

}
