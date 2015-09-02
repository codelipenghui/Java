package org.limingnihao.util;

import java.io.File;

public class PathUtil {

	public static void main(String args[]) {
		System.out.println("" + File.pathSeparator + ",,,,,,,,," + File.separator);

		System.out.println(mergeHttp("http://123.123.123.123:", ":8080/a/b.png"));
		// System.out.println(mergeUrl("http://123.123.123.123", "8080/a/b.png"));
		// System.out.println(mergeUrl("htp://123.123.123.123/", ":8080/a/b.png"));
		// System.out.println(mergeUrl("123.123.123.123", ":8080"));
		// System.out.println(mergeUrl("123.123.123.123", "8080/"));

		// System.out.println(mergePath("D:/whb_documents", "123.png"));
		// System.out.println(mergePath("/data/whb_documents", "1/2/1/123.png"));
		// System.out.println(mergePath("/data/whb_documents/", "1/2/1/123.png/"));
		// System.out.println(mergePath("/data/whb_documents//////", "/1/123.png"));

	}

	public static String mergeHttp(String server, String path) {
		if (server.startsWith("http:")) {
			server = server.substring(5, server.length());
		}
		while (server.startsWith("/")) {
			server = server.substring(1);
		}
		while (server.endsWith("/")) {
			server = server.substring(0, server.length() - 1);
		}
		while (path.startsWith("/")) {
			path = path.substring(1);
		}
		if (path.startsWith(":")) {
			return "http://" + server + "" + path;
		} else {
			return "http://" + server + "/" + path;
		}
	}

	public static String mergeUrl(String server, String path) {
		if (server.startsWith("http:")) {
			server = server.substring(5, server.length());
		}
		while (server.startsWith("/")) {
			server = server.substring(1);
		}
		while (server.endsWith("/")) {
			server = server.substring(0, server.length() - 1);
		}

		while (path.startsWith("/")) {
			path = path.substring(1);
		}
		if (path.startsWith(":")) {
			return server + "" + path;
		} else {
			return server + "/" + path;
		}
	}

	public static String mergePath(String path1, String path2) {
		while (path1.startsWith("/") || path1.startsWith("\\")) {
			path1 = path1.substring(1);
		}
		while (path1.endsWith("/") || path1.endsWith("\\")) {
			path1 = path1.substring(0, path1.length() - 1);
		}
		while (path2.startsWith("/") || path2.startsWith("\\")) {
			path2 = path2.substring(1);
		}
		while (path2.endsWith("/") || path2.endsWith("\\")) {
			path2 = path2.substring(0, path2.length() - 1);
		}
		return path1 + File.separator + "" + path2;
	}

}
