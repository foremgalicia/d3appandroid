/*
 * Copyright (C) 2011 Zadia Software for FOREM Galicia 
 *
 * Licensed under the GPL License V3
 */
package com.zadiasoftware.util;

public class Log {
	static final boolean LOG = false;

	public static void i(String tag, String string) {
	    if (LOG) android.util.Log.i(tag, string);
	}
	public static void e(String tag, String string) {
	    if (LOG) android.util.Log.e(tag, string);
	}
	public static void d(String tag, String string) {
	    if (LOG) android.util.Log.d(tag, string);
	}
	public static void v(String tag, String string) {
	    if (LOG) android.util.Log.v(tag, string);
	}
	public static void w(String tag, String string) {
	    if (LOG) android.util.Log.w(tag, string);
	}
	public static void w(String tag, String string, Exception e) {
	    if (LOG) android.util.Log.w(tag, string, e);
	}
	
}