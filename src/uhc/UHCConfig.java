package uhc;

import net.minecraftforge.common.Configuration;

public class UHCConfig {

	private static final boolean BAN_OP_DEFAULT = true;
	private static final boolean DEATH_BAN_DEFAULT = true;
	private static final boolean SHOW_HEALTH_PLAYER_LIST_DEFAULT = true;
	private static final boolean SHOW_HEALTH_SIDEBAR_DEFAULT = false;

	public static boolean BAN_OP = UHCConfig.BAN_OP_DEFAULT;
	public static boolean DEATH_BAN = UHCConfig.DEATH_BAN_DEFAULT;
	public static boolean SHOW_HEALTH_PLAYER_LIST = UHCConfig.SHOW_HEALTH_PLAYER_LIST_DEFAULT;
	public static boolean SHOW_HEALTH_SIDEBAR = UHCConfig.SHOW_HEALTH_SIDEBAR_DEFAULT;

	public static void init(Configuration config) {
		config.addCustomCategoryComment("Default", "Default configurations");

		UHCConfig.BAN_OP = config.get("Default", "banOPs", UHCConfig.BAN_OP_DEFAULT).getBoolean(UHCConfig.BAN_OP_DEFAULT);
		UHCConfig.DEATH_BAN = config.get("Default", "deathBan", UHCConfig.DEATH_BAN_DEFAULT).getBoolean(UHCConfig.DEATH_BAN_DEFAULT);
		UHCConfig.SHOW_HEALTH_PLAYER_LIST = config.get("Default", "healthIndicatorPlayerList", UHCConfig.SHOW_HEALTH_PLAYER_LIST_DEFAULT).getBoolean(UHCConfig.SHOW_HEALTH_PLAYER_LIST_DEFAULT);
		UHCConfig.SHOW_HEALTH_SIDEBAR = config.get("Default", "healthIndicatorSidebar", UHCConfig.SHOW_HEALTH_SIDEBAR_DEFAULT).getBoolean(UHCConfig.SHOW_HEALTH_SIDEBAR_DEFAULT);

		if (config.hasChanged()) {
			config.save();
		}
	}
}
