package uhc;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerSelector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

/**
 * CommandUHC
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CommandUHC extends CommandBase {

	@Override
	public String getCommandName() {
		return "uhc";
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring) {
		if (astring != null && (astring.length > 2 || astring.length < 0)) {
			icommandsender.sendChatToPlayer(this.getCommandUsage(icommandsender));
			return;
		}
		if (!(icommandsender instanceof EntityPlayer)) {
			icommandsender.sendChatToPlayer("You cannot run this command if you're not a player");
			return;
		}
		World world = ((EntityPlayer) icommandsender).worldObj;
		if (astring == null || astring.length == 0) {
			icommandsender.sendChatToPlayer("");
			icommandsender.sendChatToPlayer("UltraHardCore Mode is " + (UtilUHC.isUHC(world)?EnumChatFormatting.GREEN + "activated": EnumChatFormatting.RED + "disabled") + EnumChatFormatting.RESET + " in this world");
			icommandsender.sendChatToPlayer(" run" + EnumChatFormatting.AQUA + "/uhc help" + EnumChatFormatting.RESET + " to see how to use this command");
			return;
		}

		String arg1 = astring[0];
		if(astring.length == 1){
			if(arg1.equals("on") || arg1.equals("true") || arg1.equals("1") || arg1.equals("yes") || arg1.equals("enable")){
				if(UtilUHC.setUHCMode(world, true)){
					icommandsender.sendChatToPlayer(EnumChatFormatting.GREEN + "UltraHardCore Mode is now enabled");
					return;
				} else {
					icommandsender.sendChatToPlayer(EnumChatFormatting.RED + "UHC was already enabled");
					return;
				}
			} else 	if(arg1.equals("off") || arg1.equals("false") || arg1.equals("0") || arg1.equals("no") || arg1.equals("disable")){
				if(UtilUHC.setUHCMode(world, false)){
					icommandsender.sendChatToPlayer(EnumChatFormatting.GREEN + "UltraHardCore Mode is now disabled");
					return;
				} else {
					icommandsender.sendChatToPlayer(EnumChatFormatting.RED + "UHC was already disabled");
					return;
				}
			} else if(arg1.equals("toggle")){
				boolean before = UtilUHC.isUHC(world);
				UtilUHC.setUHCMode(world, !before);
				icommandsender.sendChatToPlayer(EnumChatFormatting.GREEN + "UltraHardCore Mode is now " + (before?"disabled":"enabled"));
				return;
			} else if(arg1.equals("help")){
				icommandsender.sendChatToPlayer("");
				icommandsender.sendChatToPlayer(EnumChatFormatting.DARK_AQUA + "UHC command help:");
				icommandsender.sendChatToPlayer(EnumChatFormatting.YELLOW + "Use " + EnumChatFormatting.AQUA + "/uhc on | off | toggle" + EnumChatFormatting.YELLOW + " to change uhc mode to enabled,");
				icommandsender.sendChatToPlayer(EnumChatFormatting.YELLOW + "disabled or to toggle it, respectively. Use");
				icommandsender.sendChatToPlayer(EnumChatFormatting.AQUA + "/uhc heal [playername | @a]" + EnumChatFormatting.YELLOW + " to heal the specified player(s)");
				return;
			}
		} else if(astring.length == 2){
			String arg2 = astring[1];
			if (arg1.equals("heal")){
				for(EntityPlayerMP player : PlayerSelector.matchPlayers(icommandsender, arg2)){
					player.heal(player.getMaxHealth());
					return;
				}
			}
		}
		icommandsender.sendChatToPlayer(this.getCommandUsage(icommandsender));
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return UtilUHC.canRunCommand(sender.getCommandSenderName());
	}

	@Override
	public String getCommandUsage(ICommandSender par1iCommandSender) {
		return "/uhc on | off | toggle | heal";
	}

	@Override
	public List<?> addTabCompletionOptions(ICommandSender par1iCommandSender, String[] par2ArrayOfStr) {
		return CommandBase.getListOfStringsMatchingLastWord(par2ArrayOfStr, "on", "off", "toggle", "help", "heal");
	}

}
