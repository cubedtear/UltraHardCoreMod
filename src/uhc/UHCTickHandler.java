package uhc;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

/**
 * UHCTickHandler
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UHCTickHandler implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		for (TickType tick : type) {
			if (tick == TickType.PLAYER) {
				if (tickData.length == 1 && tickData[0] instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) tickData[0];

					if (!UtilUHC.isUHC(player.worldObj)) return;

					player.getEntityData().setByte("UHCLifeBefore", (byte) player.getHealth());
				}
			}
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		for (TickType tick : type) {
			if (tick == TickType.PLAYER) {
				if (tickData.length == 1 && tickData[0] instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) tickData[0];
					if (!UtilUHC.isUHC(player.worldObj)) return;

					if (!player.getEntityData().hasKey("UHCLifeBefore")) return;
					byte health = player.getEntityData().getByte("UHCLifeBefore");
					PotionEffect pEffect = player.getActivePotionEffect(Potion.regeneration);
					if (pEffect != null) {
						player.setEntityHealth(health); // To avoid natural regeneration
						if (Potion.regeneration.isReady(pEffect.duration - 1, pEffect.getAmplifier())) {
							pEffect.performEffect(player);
						}
						continue;
					}
					if (health < player.getHealth()) {
						player.setEntityHealth(health);
					}
				}
			}
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.PLAYER);
	}

	@Override
	public String getLabel() {
		return "UHCTickHandler";
	}

}
