package com.cortexintelligence.crawling;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlingWeb {

	private static int maxDepth;
	
	public CrawlingWeb(String url, String depth) {
		try {
			CrawlingWeb.maxDepth = new Integer(depth);
			process(url, maxDepth);
		} catch (NumberFormatException e) {
			System.err.println("[ERROR] Invalid argument, <depth> must be an integer.");
		}
	}
	
	private void process(String url, int depth) {
		
		try {
			
			Document doc = Jsoup.connect(url).get();
			Element body = doc.body();
			
			System.out.println();
			System.out.println(String.format(">>> %s <<<", url));
			System.out.println();
			
			System.out.println(body.text());
			
			if (depth > 0) {
				Elements links = doc.select("a");
				for (Element link: links) {
					process(link.attr("href"), depth - 1);
				}
			}
			
		} catch (IllegalArgumentException e) {
			// Show message only for the root URL
			if (depth == maxDepth) { 
				System.err.println(String.format("[ERROR] Invalid url: %s", url));
			}
		} catch (IOException e) {
		}

		
	}
	
}
