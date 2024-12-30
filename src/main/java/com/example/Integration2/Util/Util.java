package com.example.Integration2.Util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;

import java.security.Key;

public class Util {
	@Getter
	private static String accessToken;
	@Getter
	private static String refreshToken;
	@Getter
	private static String ott;
	@Getter
	private static final Key SECRET_KEY = Keys.secretKeyFor( SignatureAlgorithm.HS256);


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
