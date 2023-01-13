/**
 * 
 */
package com.groyyo2.user.config;

import java.time.ZoneId;

import lombok.experimental.UtilityClass;

/**
 * @author nipunaggarwal
 *
 * 
 */
@UtilityClass
public class GroyyoConstants {

	public static final Integer ZER0 = 0;

	public static final String DATE_SEPARATOR = "-";
	public static final String IST_TIMEZONE = "Asia/Kolkata";
	public static final String UTC_TIMEZONE = "UTC";
	public static final ZoneId IST_TIMEZONEID = ZoneId.of(IST_TIMEZONE);
	public static final String INDIA_COUNTRY_CODE = "91";
	public static final String INDIA_ISO_CODE = "IN";
	public static final int PRICE_ROUND_OFF_DIGITS = 2;
	public static final String ZONE_OFFSET = "+05:30";

	public static final long SECONDS_IN_DAY = 86400;
	public static final long MILLI_SECONDS_IN_DAY = SECONDS_IN_DAY * 1000;

	public static final String ORGANIZATION_NAME = "Groyyo";
	public static final String ORGANIZATION_DOMAIN = "https://www.groyyo.com";

	public static final String ORGANIZATION_EMAIL_DOMAIN = "@groyyo.com";

	public static final String GUID = "guid";
	public static final String LUID = "luid";
	
	public static final String REQUEST_PATH = "RequestPath";
	public static final String QUERY_STRING = "QueryString";
	

	public static final String MESSAGE_ID = "messageId";

	public static final String NO_KEY = "NO";
	public static final String REQ_UID = "reqUuid";
	public static final String REQ_MOBILE = "reqUserMobile";

	// DEVICE INFO
	public static final String APP_NAME = "appName";
	public static final String APP_VERSION = "appVersion";
	public static final String DEVICE = "device";
	public static final String PLATFORM = "platform";
	public static final String PLATFORM_VERSION = "platformVersion";

}
