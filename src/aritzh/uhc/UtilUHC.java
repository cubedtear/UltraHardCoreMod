package aritzh.uhc;

import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import cpw.mods.fml.common.FMLCommonHandler;

/**
 * BlockPortableChest
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UtilUHC {

	public static boolean setUHCMode(World world, boolean uhc) {
		boolean before = UtilUHC.isUHC(world);
		if (uhc == before) return false;
		WorldSavedData wsd = world.perWorldStorage.loadData(null, "UltraHardCoreData");
		if (wsd instanceof UHCWorldData) {
			UHCWorldData data = (UHCWorldData) wsd;
			data.setUHCMode(uhc);
		}
		return true;
	}

	public static boolean isUHC(World world) {
		if (world.perWorldStorage.loadData(UHCWorldData.class, "UltraHardCoreData") == null) {
			UtilUHC.initWorldUHCData(world);
		}
		WorldSavedData wsd = world.perWorldStorage.loadData(null, "UltraHardCoreData");
		if (wsd instanceof UHCWorldData) {
			UHCWorldData data = (UHCWorldData) wsd;
			return data.isWorldInUHCMode();
		}
		return false;
	}

	public static void initWorldUHCData(World world) {
		world.perWorldStorage.setData("UltraHardCoreData", new UHCWorldData());
	}

	public static boolean isOP(String username) {
		return FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().getOps().contains(username);
	}

	public static boolean canRunCommand(String username) {
		return UtilUHC.isOP(username) || FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().areCommandsAllowed(username);
	}

}
