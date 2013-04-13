package uhc;

import net.minecraftforge.common.Configuration;


public class UHCConfig {
	
	private static final boolean BAN_OP_DEFAULT = true;
	private static final boolean DEATH_BAN_DEFAULT = true;
	private static final boolean SHOW_HEALTH_PLAYER_LIST_DEFAULT = true;
	private static final boolean SHOW_HEALTH_SIDEBAR_DEFAULT = false;
	
	public static boolean BAN_OP = BAN_OP_DEFAULT;
	public static boolean DEATH_BAN = DEATH_BAN_DEFAULT;
	public static boolean SHOW_HEALTH_PLAYER_LIST = SHOW_HEALTH_PLAYER_LIST_DEFAULT;
	public static boolean SHOW_HEALTH_SIDEBAR = SHOW_HEALTH_SIDEBAR_DEFAULT;
	
	public static void init(Configuration config){
		config.addCustomCategoryComment("Default", "Default configurations");
		
		BAN_OP = config.get("Default", "banOPs", BAN_OP_DEFAULT).getBoolean(BAN_OP_DEFAULT);
		DEATH_BAN = config.get("Default", "deathBan", DEATH_BAN_DEFAULT).getBoolean(DEATH_BAN_DEFAULT);
		SHOW_HEALTH_PLAYER_LIST = config.get("Default", "healthIndicatorPlayerList", SHOW_HEALTH_PLAYER_LIST_DEFAULT).getBoolean(SHOW_HEALTH_PLAYER_LIST_DEFAULT);
		SHOW_HEALTH_SIDEBAR = config.get("Default", "healthIndicatorSidebar", SHOW_HEALTH_SIDEBAR_DEFAULT).getBoolean(SHOW_HEALTH_SIDEBAR_DEFAULT);
		
		if(config.hasChanged()){
			config.save();
		}
	}
}
