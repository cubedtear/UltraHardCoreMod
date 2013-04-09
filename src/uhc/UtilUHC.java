package uhc;

import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

/**
 * BlockPortableChest
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UtilUHC {

	public static void setUHCMode(World world, boolean uhc) {
		if (world.perWorldStorage.loadData(UHCWorldData.class, "UltraHardCoreData") == null) {
			UtilUHC.initWorldUHCData(world);
		}
		WorldSavedData wsd = world.perWorldStorage.loadData(null, "UltraHardCoreData");
		if (wsd instanceof UHCWorldData) {
			UHCWorldData data = (UHCWorldData) wsd;
			data.setUHCMode(uhc);
		}
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

}
