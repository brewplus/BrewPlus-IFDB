
package jmash;

import java.awt.Font;

/**
 *
 * @author Alessandro
 */
public class Info extends Msg {

    /**
     * 
     */
    private static final long serialVersionUID = -8880281415837935083L;

    /** Creates a new instance of Info */
    public Info(String msg) {
        super(msg);
        this.jLabel1.setIcon(okIcon);
        this.jLabel2.setIcon(okIcon);
        this.jLabel1.setIcon(null);
        this.jLabel2.setIcon(null);
    }

    public Info(String msg, Font font) {
        super(msg, font);
        this.jLabel1.setIcon(null);
        this.jLabel2.setIcon(null);
    }
}
