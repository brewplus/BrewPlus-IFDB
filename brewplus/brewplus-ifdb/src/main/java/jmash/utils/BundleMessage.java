package jmash.utils;

import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class BundleMessage extends ResourceBundle {

    public BundleMessage(ResourceBundle bundle) {
        super.setParent(bundle);
    }
        
    @Override
    protected Object handleGetObject(String key) {
        return null;
    }

    @Override
    public Enumeration<String> getKeys() {
        return null;
    }
    
    public String getString(String key, String replaceString) {
        return super.getString(key).replaceAll(Pattern.quote("{0}"), replaceString);
    }
}
