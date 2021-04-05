package com.hades.gameriprofi.log;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Logger {
	public static void log(String path, String log) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(
					new OutputStreamWriter(
						new BufferedOutputStream(new FileOutputStream(path)), "UTF-8"));
				out.append(String.format(log));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}// TODO