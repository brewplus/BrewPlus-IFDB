package jmash;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.Container;
import java.awt.Desktop;
import java.awt.Panel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import java.net.URI;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.Box;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JEditorPane;
import javax.swing.UIManager;
import java.awt.GridLayout;
import java.awt.Component;
import java.beans.PropertyVetoException;

import javax.swing.JSplitPane;


public class frmFeed2 extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9088582043485316104L;
	
	//cablato indirizzo RSS
	//private String rssUrl=Main.config.getRSSFeed();
	private String rssUrl="http://www.birrandosiimpara.it/birrabirra/feed.php";
	
	
	private SyndFeed feed;
	JList list;
	JTextPane txtContent;
	DefaultListModel listModel;
	JLabel lblLink = new JLabel("link");
	JInternalFrame parent;
	
	public frmFeed2() {
		setResizable(true);
		setMaximizable(true);
		setBounds(100, 100, 776, 498);
		parent=this;
		
		listModel = new DefaultListModel();

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JLabel label_1 = new JLabel("");
		panel_1.add(label_1);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel_1.add(toolBar);
		
		JButton btnRicarica = new JButton("");
		btnRicarica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getRSSHeaders();
			}
		});
		btnRicarica.setToolTipText("Ricarica");
		btnRicarica.setIcon(new ImageIcon(frmFeed2.class.getResource("/jmash/images/button_reload.png")));
		toolBar.add(btnRicarica);
		
		JButton btnforum = new JButton("");
		btnforum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gotoforum();
			}
		});
		btnforum.setToolTipText("Vai all'articolo");
		btnforum.setIcon(new ImageIcon(frmFeed2.class.getResource("/jmash/images/button_forumvisit.png")));
		toolBar.add(btnforum);
		
		JButton btnChiudi = new JButton("");
		btnChiudi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.dispose();
			}
		});
		btnChiudi.setToolTipText("OK");
		btnChiudi.setIcon(new ImageIcon(frmFeed2.class.getResource("/jmash/images/button_ok.png")));
		toolBar.add(btnChiudi);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		getContentPane().add(panel_2, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("");
		panel_2.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(frmFeed2.class.getResource("/jmash/images/birrabirralog.png")));
		
		
		lblLink.setVisible(false);
		panel_2.add(lblLink);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(300);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		scrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		list = new JList(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				loadContent();
			}
		});
		list.setFont(new Font("Tahoma", Font.PLAIN, 9));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		JScrollPane scrollPane2 = new JScrollPane();
		splitPane.setRightComponent(scrollPane2);
		scrollPane2.setBorder(null);
		scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		txtContent = new JTextPane();
		txtContent.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtContent.setEditable(false);
		txtContent.setContentType("text/html;charset=UTF-8");
		txtContent.setBorder(null);
		scrollPane2.setViewportView(txtContent);
		
		InitForm();
		setTitle("Birra Birra Feed");
		
		getRSSHeaders();

	}
	private void InitForm()
	{
		setClosable(true);
        setIconifiable(true);
        setAutoscrolls(true);
	}
	private void gotoforum()
	{
		try
		{
			Desktop.getDesktop().browse(new URI( lblLink.getText()));
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	private String parseWrongChars(String content)
	{
		content=content.replace("Ã©", "è");
		content=content.replace("â€¢","-");
		content=content.replace("Ã¹","ù");
		content=content.replace("Ã¨","è");
		content=content.replace("â€”","—");
		content=content.replace("Ã²","ò");
		content=content.replace("Ã","à");
		content=content.replace("Â°","°");
		content=content.replace("¬","ì");

		return content;
	}
	
	private void loadContent()
	{
		if(list.getSelectedIndex()<0)
		{
			txtContent.setText("");
			lblLink.setText("");
			return;
		}
		SyndEntry entry=(SyndEntry)feed.getEntries().get(list.getSelectedIndex());
		String content;
		SyndContent sc=(SyndContent)entry.getContents().get(0);
		lblLink.setText(entry.getLink());
		content=sc.getValue();
		content=parseWrongChars(content);
		txtContent.setText("<html>"+content+"</html>");
		txtContent.setCaretPosition(0);
	}
	
	//RSS
	private void getRSSHeaders()
	{
		listModel.removeAllElements();
		try {
			URL feedUrl = new URL(rssUrl);
			SyndFeedInput input = new SyndFeedInput();
			feed = input.build(new InputStreamReader(feedUrl.openStream()));
			
			for(Iterator i = feed.getEntries().iterator();i.hasNext();)
			{
				SyndEntry entry =(SyndEntry) i.next();
				String title, author,itemlayout, hhlayout,content;
				Date data;
				
				title=entry.getTitle();
				title=parseWrongChars(title);
				author=entry.getAuthor();
				data=entry.getPublishedDate();
				//hhlayout=""+ data.getHours()+":"+data.getMinutes();
				Calendar cal=Calendar.getInstance();
				cal.setTime(data);
				hhlayout=""+ cal.get(Calendar.HOUR_OF_DAY)+":"+ Utils.ConvertMinutesTo2Digits(cal.get(Calendar.MINUTE));
				SyndContent sc=(SyndContent)entry.getContents().get(0);
				content=sc.getValue();
				itemlayout="<html><strong>"+hhlayout+"  " +author+"</strong><br>"+title+"</html>";
				listModel.addElement(itemlayout);
			}
			if(listModel.getSize()<1)return;
			list.setSelectedIndex(0);
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			
		}
		
	}
}