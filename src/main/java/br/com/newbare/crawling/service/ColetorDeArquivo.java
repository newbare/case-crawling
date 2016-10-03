package br.com.newbare.crawling.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

import br.com.newbare.crawling.util.Util;

public class ColetorDeArquivo implements IColetor {

	public ColetorDeArquivo(String path) {
		
		File file = new File(path);
		
		if (file.isFile()) {
			execute(file);
		} else if (file.isDirectory() && file.listFiles() != null) {

			for (File f: file.listFiles()) {
				new ColetorDeArquivo(f.getAbsolutePath());
			}
		}
		
	}
	
	public void execute(File file) {
				
		System.out.println();
		System.out.println(String.format(">>> %s <<<", file.getAbsolutePath()));
		System.out.println();

		try {
	
			String ext = file.getName().substring(file.getName().lastIndexOf('.') + 1).toLowerCase();
			if (!"txt|pdf|doc|docx|ppt|pptx".contains(ext)) {
				return;
			}
			
			if (ext.equals("txt")) {
				Util.printTxt(file);
			} else {
				
				Parser parser = new AutoDetectParser();
				InputStream content = new FileInputStream(file);
				BodyContentHandler handler = new BodyContentHandler();
				Metadata metadata = new Metadata();
				parser.parse(content, handler, metadata, new ParseContext());
				System.out.println(handler.toString());
			}
				
		} catch (FileNotFoundException e) {
			System.err.println(String.format("[ERROR] File '%s' does not exist.", file.getAbsolutePath()));
		} catch (Exception e) {
			System.err.println(String.format("[ERROR] Error reading file '%s'.", file.getAbsolutePath()));
		}
	}


	@Override
	public void execute(String usuario, String senha) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(String url, int profundidade) {
		// TODO Auto-generated method stub
		
	}
	
}
