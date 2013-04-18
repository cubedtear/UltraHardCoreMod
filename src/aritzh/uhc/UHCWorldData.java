package aritzh.uhc;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

/**
 * UHCWorldData
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UHCWorldData extends WorldSavedData {

	private boolean isWorldInUHCMode = false;

	public UHCWorldData() {
		super("UltraHardCoreData");
	}

	public UHCWorldData(String par1Str) {
		super(par1Str);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTag) {
		if (nbtTag == null) throw new IllegalArgumentException("WorldData NBT tag must not be null");
		this.isWorldInUHCMode = nbtTag.getBoolean("UHCMode");
	}

	public boolean isWorldInUHCMode() {
		return this.isWorldInUHCMode;
	}

	public void setUHCMode(boolean uhc) {
		this.isWorldInUHCMode = uhc;
		this.markDirty(); // Magic!
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTag) {
		if (nbtTag == null) throw new IllegalArgumentException("WorldData NBT tag must not be null");
		nbtTag.setBoolean("UHCMode", this.isWorldInUHCMode);
	}

}
