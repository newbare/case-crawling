package com.cortexintelligence.crawling;


public class Main {

	public static void main(final String[] args) {
	
		if (args.length == 0 || !args[0].matches("-[fwg]")) {
			showUsageAndExit();
		}
		
		if (args[0].startsWith("-f") && args.length >= 2) {			// File
			new CrawlingFile(args[1]);
		} else if (args[0].startsWith("-w") && args.length >= 3) {	// Web
			new CrawlingWeb(args[1], args[2]);
		} else if (args[0].startsWith("-g") && args.length >= 3) {	// Gmail
			new CrawlingGmail(args[1], args[2]);
		} else {
			showUsageAndExit();
		}
	}
	
	private static void showUsageAndExit() {
		try {
			Util.printTxt(Main.class.getResource("/usage.txt"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
}
