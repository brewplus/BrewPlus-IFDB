package jmash;

import java.awt.EventQueue;
import java.awt.ScrollPane;

import javax.swing.JInternalFrame;
import javax.swing.JEditorPane;
import javax.swing.ScrollPaneConstants;

import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class frmBrowser extends  javax.swing.JInternalFrame{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7736428331553379909L;
	private  JScrollPane scrollPane = new JScrollPane();
	private final JEditorPane editorPane=new JEditorPane();

	/**
	 * Create the frame.
	 */
	public frmBrowser(String url, String title, boolean isLocal) {
		this.setTitle(title);
		setBounds(100, 100, 450, 300);
		InitForm();
		if(isLocal)
		{
			//file locale
			InputStream in;
			try {
				//limitazione: non vengono visualizzate le immagini
				editorPane.setContentType("text/html");
				in = new FileInputStream(url);
				editorPane.read(in, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			//da web
			try {
				//
				editorPane.setPage(url);
				editorPane.setEditable(false);
				editorPane.addHyperlinkListener(new HyperlinkListener() {
					
					@Override
					public void hyperlinkUpdate(HyperlinkEvent e) {
						try
		                {
				             if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
				             editorPane.setPage(e.getURL());
		                }catch(Exception ex)
		                {}
						
					}
				});
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		getContentPane().add(editorPane, BorderLayout.CENTER);
		scrollPane = new JScrollPane(editorPane,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

	private void InitForm()
	{
		setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setAutoscrolls(true);
        setMinimumSize(new java.awt.Dimension(1024, 600));
        setPreferredSize(new java.awt.Dimension(1024, 600));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
	}
}
