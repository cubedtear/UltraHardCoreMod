package uhc;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
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
		if (astring != null && (astring.length > 1 || astring.length < 0)) {
			icommandsender.sendChatToPlayer(this.getCommandUsage(icommandsender));
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

		String s = astring[0];
		if(s.equals("on") || s.equals("true") || s.equals("1") || s.equals("yes") || s.equals("enable")){
			if(UtilUHC.setUHCMode(world, true)){
				icommandsender.sendChatToPlayer(EnumChatFormatting.GREEN + "UltraHardCore Mode is now enabled");
			} else {
				icommandsender.sendChatToPlayer(EnumChatFormatting.RED + "UHC was already enabled");
			}
		} else 	if(s.equals("off") || s.equals("false") || s.equals("0") || s.equals("no") || s.equals("disable")){
			if(UtilUHC.setUHCMode(world, false)){
				icommandsender.sendChatToPlayer(EnumChatFormatting.GREEN + "UltraHardCore Mode is now disabled");
			} else {
				icommandsender.sendChatToPlayer(EnumChatFormatting.RED + "UHC was already disabled");
			}
		} else if(s.equals("toggle")){
			boolean before = UtilUHC.isUHC(world);
			UtilUHC.setUHCMode(world, !before);
			icommandsender.sendChatToPlayer(EnumChatFormatting.GREEN + "UltraHardCore Mode is now " + (before?"disabled":"enabled"));
		} else if(s.equals("help")){
			icommandsender.sendChatToPlayer("");
			icommandsender.sendChatToPlayer(EnumChatFormatting.DARK_AQUA + "UHC command help:");
			icommandsender.sendChatToPlayer(EnumChatFormatting.YELLOW + "Use " + EnumChatFormatting.AQUA + "/uhc on | off | toggle" + EnumChatFormatting.YELLOW + " to change uhc mode to enabled,");
			icommandsender.sendChatToPlayer(EnumChatFormatting.YELLOW + "disabled or to toggle it, respectively");
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return UtilUHC.canRunCommand(sender.getCommandSenderName());
	}

	@Override
	public String getCommandUsage(ICommandSender par1iCommandSender) {
		return "/uhc on|off|toggle";
	}

	@Override
	public List<?> addTabCompletionOptions(ICommandSender par1iCommandSender, String[] par2ArrayOfStr) {
		return CommandBase.getListOfStringsMatchingLastWord(par2ArrayOfStr, "on", "off", "toggle", "help");
	}

}
