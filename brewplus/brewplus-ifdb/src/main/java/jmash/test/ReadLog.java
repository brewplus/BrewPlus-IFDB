/*
 * ReadLog.java
 *
 * Created on 18 febbraio 2008, 12.46
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package jmash.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import jmash.Utils;

/**
 *
 * @author AChiari
 */
public class ReadLog {
    
    /** Creates a new instance of ReadLog */
    public ReadLog() {
        try {
            System.setProperty("http.proxyHost","10.77.99.99");
            System.setProperty("http.proxyPort","8080");
            
            String S=download("http://www.hobbybirra.it/hobbybrew/log.txt");
            S=S.replaceAll("unknown, unknown","unknown");
            String L[]=S.split("\n");
            int n=0;
            HashMap hm=new HashMap();
            for (int i = 0; i < L.length; i++) {
                L[i]=L[i].replaceAll("\n","");
                L[i]=L[i].replaceAll("\r","");
                String C[]=L[i].split(" ");
                if(!hm.containsKey(C[2])){
                    hm.put(C[2], 1);
                    n++;
                }
                else{
                    int c=(Integer)hm.get(C[2]);
                    hm.put(C[2], c+1);
                    if(C.length>3) hm.put(C[2]+"#", C[3]);
                }
            }
            System.out.println(n+" ip unici.");
            Iterator it=hm.keySet().iterator();
            int i=1;
            while(it.hasNext()){
            
                 Object k=it.next();
                 if(((String)k).indexOf("#")<0)
                 System.out.println((i++)+") "+k+", "+hm.get(k)+"");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static String download(String address ) throws Exception{
        URLConnection conn = null;
        InputStream  in = null;
        String R = "";
        try {
            URL url = new URL(address);
            conn = url.openConnection();
            //conn.setRequestProperty("Cookie", "rid=H8phkT5OM&ruvol=2&ruweight=1&ruhops=1&rutemp=1&ruextract=0");
            in = conn.getInputStream();
            byte[] buffer = new byte[1024];
            int numRead;
            long numWritten = 0;
            
            while ((numRead = in.read(buffer)) != -1) {
                R+=new String(buffer,0,numRead);
            }
        } catch (MalformedURLException ex) {
            Utils.showException(ex);
            throw ex;
        } catch (IOException ex) {
            Utils.showException(ex);
            throw ex;
        }
        return R;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ReadLog();
    }
    
}
