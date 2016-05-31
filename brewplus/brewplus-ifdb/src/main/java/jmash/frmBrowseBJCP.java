package jmash;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTree;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;


public class frmBrowseBJCP extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7022521704441783121L;
	JInternalFrame parent;
	JTextPane textPane = new JTextPane();
	private JTree tree_1;

	public frmBrowseBJCP() {
		setTitle("Stili e categorie BJCP");
		parent=this;
		setBounds(100, 100, 455, 319);
		InitForm();
		
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		panel_1.add(toolBar_1);
		
		JButton cmdOk = new JButton("");
		cmdOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.dispose();
			}
		});
		cmdOk.setToolTipText("");
		toolBar_1.add(cmdOk);
		cmdOk.setIcon(new ImageIcon(frmBrowseBJCP.class.getResource("/jmash/images/button_ok.png")));
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		tree_1 = new JTree();		
		DefaultMutableTreeNode root= new DefaultMutableTreeNode("BJCP");
		String oldCat="";
		DefaultMutableTreeNode bookmark = new DefaultMutableTreeNode();
		for(BrewStyle bs:Main.bjcpStyles){
			String codiceCat=bs.getcodiceCategoria();
			if(codiceCat.equals(oldCat)){
				//child
				DefaultMutableTreeNode cat= new DefaultMutableTreeNode(bs);
				bookmark.add(cat);
			}
			else{
				//parent
				bookmark= new DefaultMutableTreeNode(bs.getcodiceCategoria()+ " " + bs.getCategoria());
				DefaultMutableTreeNode cat= new DefaultMutableTreeNode(bs);
				bookmark.add(cat);
				root.add(bookmark);
				oldCat=bs.getcodiceCategoria();
			}
		}
		DefaultTreeModel treeModel = new DefaultTreeModel( root );
		final Icon customLeaf=new ImageIcon(java.awt.Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/jmash/images/boccale.gif")));
	    
	    JSplitPane splitPane = new JSplitPane();
	    splitPane.setDividerSize(5);
	    splitPane.setContinuousLayout(false);
	    getContentPane().add(splitPane, BorderLayout.CENTER);
	    
	    JScrollPane scrollPane_1 = new JScrollPane();
	    scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    splitPane.setLeftComponent(scrollPane_1);
	    DefaultTreeCellRenderer renderer3 = new DefaultTreeCellRenderer();
	    renderer3.setLeafIcon(customLeaf);
	    tree_1 = new JTree( treeModel );
	    tree_1.addTreeSelectionListener(new TreeSelectionListener() {
	    	public void valueChanged(TreeSelectionEvent e) {
	    		DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree_1.getLastSelectedPathComponent();
	            if (node == null) return;
	            Object nodeInfo = node.getUserObject();
	            if (node.isLeaf()) {
	                BrewStyle bs = (BrewStyle)nodeInfo;
	                displayStile(bs);
	            } else {
	            	textPane.setText("");
	            }
	    	}
	    });
	    scrollPane_1.setViewportView(tree_1);
	    tree_1.setRootVisible(true);
	    tree_1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	    tree_1.setCellRenderer(renderer3);
	    
	    JScrollPane scrollPane = new JScrollPane();
	    splitPane.setRightComponent(scrollPane);
	    scrollPane.setViewportView(textPane);
	    splitPane.setDividerLocation(300);

	}
	private void displayStile(BrewStyle bs){
		StringBuilder sb=new StringBuilder();
		sb.append(bs.getDesCategoria()+"\n\n");
		sb.append("Aroma: "+bs.getAroma()+"\n\n");
		sb.append("Appearance: "+bs.getAppearance()+"\n\n");
		sb.append("Flavor: "+bs.getFlavor()+"\n\n");
		sb.append("Mouthfeel: "+bs.getMouthfeel()+"\n\n");
		sb.append("Impression: "+bs.getImpression()+"\n\n");
		sb.append("Comments: "+bs.getComments()+"\n\n");
		sb.append("Ingredients: "+bs.getIngredients()+"\n\n");
		sb.append("Examples: "+bs.getExamples()+"\n\n");
		sb.append(bs.getDesIBU().replace(".", ",")+"\n");
		sb.append(bs.getDesOG().replace(".", ",")+"\n");
		sb.append(bs.getDesFG().replace(".", ",")+"\n");
		
		textPane.setText(sb.toString());
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

