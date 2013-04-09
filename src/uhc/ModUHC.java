package uhc;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.event.FMLInitializationEvent;
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

	@Instance("UHC")
	public static ModUHC afm = new ModUHC();

	@ServerStarting
	public void serverStarting(FMLServerStartingEvent e) {
		e.registerServerCommand(new CommandUHC());
	}

	@Init
	public static void init(FMLInitializationEvent event) {
		TickRegistry.registerTickHandler(new UHCTickHandler(), Side.SERVER);
	}

}
