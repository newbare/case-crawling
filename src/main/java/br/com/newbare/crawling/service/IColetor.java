package br.com.newbare.crawling.service;

import java.io.File;

public interface IColetor {
	public void execute(File file);
	public void execute(String usuario, String senha);
	public void execute(String url, int profundidade); 	
}