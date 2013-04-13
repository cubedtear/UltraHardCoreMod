package uhc;

import net.minecraftforge.common.Configuration;


public class UHCConfig {
	
	public static final boolean BAN_OP_DEFAULT = true;
	public static final boolean DEATH_BAN_DEFAULT = true;
	
	public static boolean BAN_OP = BAN_OP_DEFAULT;
	public static boolean DEATH_BAN = DEATH_BAN_DEFAULT;
	
	public static void init(Configuration config){
		config.addCustomCategoryComment("Default", "Default configurations");
		
		BAN_OP = config.get("Default", "banOPs", BAN_OP_DEFAULT).getBoolean(BAN_OP_DEFAULT);
		DEATH_BAN = config.get("Default", "deathBan", DEATH_BAN_DEFAULT).getBoolean(DEATH_BAN_DEFAULT);
		
		if(config.hasChanged()){
			config.save();
		}
	}
}
