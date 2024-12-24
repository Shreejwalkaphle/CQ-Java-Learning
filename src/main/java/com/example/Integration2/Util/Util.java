package com.example.Integration2.Util;

import lombok.Getter;

public class Util {
	@Getter
	private static String accessToken;
	@Getter
	private static String refreshToken;
	@Getter
	private static String ott;

	public static void setAccessToken( String accessToken ) {
		Util.accessToken = accessToken;
	}

	public static void setRefreshToken( String refreshToken ) {
		Util.refreshToken = refreshToken;
	}

	public static void setOtt( String ott ) {
		Util.ott = ott;
	}
}
