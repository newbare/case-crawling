package br.com.newbare.crawling.service;

import java.io.File;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

public class ColetorDeEmail implements IColetor{

	public ColetorDeEmail(String username, String password) {
		execute(username, password);
	}

	public void execute(String username, String password) {
		
		Properties props = new Properties();
	    props.setProperty("mail.store.protocol", "imaps");
	    
	    try {
	    	
	        Session session = Session.getInstance(props, null);
	        Store store = session.getStore();
	        store.connect("imap.gmail.com", username, password);
	        Folder inbox = store.getFolder("INBOX");
	        inbox.open(Folder.READ_ONLY);
	        Message[] msgs = inbox.getMessages();

	        for (Message msg : msgs) {
	            
	        	try {
	        		
	        		Multipart mp = (Multipart) msg.getContent();
	                BodyPart bp = mp.getBodyPart(0);
	                
	        		System.out.println();
	    			System.out.println(String.format(">>> %s <<<", msg.getSubject()));
	    			System.out.println();
	    			
	                System.out.println(bp.getContent());
	                
	            } catch (Exception e) {
	            	System.err.println("[ERROR] Error reading message.");
	            }
	        }

	        inbox.close(false);
	        store.close();

	    } catch (Exception e) {
	    	System.err.println(String.format("[ERROR] Error reading inbox: %s", username));
	    }
	}

	@Override
	public void execute(File file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(String url, int profundidade) {
		// TODO Auto-generated method stub
		
	}
	
}
