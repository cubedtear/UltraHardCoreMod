package uhc;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.BanEntry;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

/**
 * UHCTickHandler
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Handler implements ITickHandler{

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
					if (!UtilUHC.isUHC(player.worldObj)) continue;
					if(UtilUHC.isOP(player.username) && !UHCConfig.BAN_OP) continue;
					if (!player.getEntityData().hasKey("UHCLifeBefore")) continue;
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
	
	@ForgeSubscribe
	public void onEntityDead(LivingDeathEvent event){
		if(!UHCConfig.DEATH_BAN) return;
		if(event.entityLiving instanceof EntityPlayerMP){
			EntityPlayerMP playerEntity = (EntityPlayerMP) event.entity;
			MinecraftServer mcServer = FMLCommonHandler.instance().getMinecraftServerInstance();
			if(UtilUHC.isUHC(playerEntity.worldObj)){
				if (mcServer.isSinglePlayer() && playerEntity.username.equals(mcServer.getServerOwner())) {
					playerEntity.playerNetServerHandler.kickPlayerFromServer("You have died. Game over, man, it\'s game over!\n\nThe world has been deleted ;)");
					mcServer.deleteWorldAndStopServer();
				} else {
					BanEntry banentry = new BanEntry(playerEntity.username);
					banentry.setBanReason("Death in UltraHardCore");
					mcServer.getConfigurationManager().getBannedPlayers().put(banentry);
					playerEntity.playerNetServerHandler.kickPlayerFromServer("You have died. Game over, man, it\'s game over!");
				}
			}
		} else {
			FMLLog.info("Entity " + event.entity.getEntityName() + " died... :-(",(Object[]) null);
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
