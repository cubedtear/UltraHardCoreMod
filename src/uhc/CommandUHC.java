package uhc;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
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
			icommandsender.sendChatToPlayer("UltraHardCore Mode is " + (UtilUHC.isUHC(world)?" activated":"disabled") + "for this world");
			icommandsender.sendChatToPlayer("Use /uhc on|off|toggle to change this to enabled,");
			icommandsender.sendChatToPlayer("disabled or to toggle them, respectively");
			return;
		}

		String s = astring[0];
		if(s.equals("on") || s.equals("true") || s.equals("1") || s.equals("yes")){
			UtilUHC.setUHCMode(world, true);
		} else 	if(s.equals("off") || s.equals("false") || s.equals("0") || s.equals("no")){
			UtilUHC.setUHCMode(world, false);
		} else if(s.equals("toggle")){
			UtilUHC.setUHCMode(world, !UtilUHC.isUHC(world));
		}
	}

	@Override
	public String getCommandUsage(ICommandSender par1iCommandSender) {
		return "/uhc on|off|toggle";
	}

	@Override
	public List<?> addTabCompletionOptions(ICommandSender par1iCommandSender, String[] par2ArrayOfStr) {
		return CommandBase.getListOfStringsMatchingLastWord(par2ArrayOfStr, "on", "off", "toggle");
	}

}
