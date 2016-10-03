package br.com.newbare.crawling.service;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ColetorWeb implements IColetor{

	private static int maxDepth;
	
	public ColetorWeb(String url, String depth) {
		try {
			ColetorWeb.maxDepth = new Integer(depth);
			execute(url, maxDepth);
		} catch (NumberFormatException e) {
			System.err.println("[ERROR] Invalid argument, <depth> must be an integer.");
		}
	}
	
	public void execute(String url, int depth) {
		
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
					execute(link.attr("href"), depth - 1);
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

	@Override
	public void execute(File file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(String usuario, String senha) {
		// TODO Auto-generated method stub
		
	}
	
}
