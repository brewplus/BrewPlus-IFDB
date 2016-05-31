/*
 *  Copyright 2006, 2007, 2008 Alessandro Chiari.
 *
 *  This file is part of BrewPlus.
 *
 *  BrewPlus is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  BrewPlus is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with BrewPlus; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package jmash.robot;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

public class hbRobot extends Robot {
    
    public hbRobot() throws AWTException {
	super();
    }
    private int ox,  oy;
    
    public void moveMouse(int fx, int fy, int x, int y) {
	mouseMove(fx, fy);
	while (fx != x || fy != y) {
	    double dx = (x - fx) / 4;
	    double dy = (y - fy) / 4;
	    
	    if (fx < x) {
		dx++;
	    }
	    if (fx > x) {
		dx--;
	    }
	    if (fy < y) {
		dy++;
	    }
	    if (fy > y) {
		dy--;
	    }
	    
	    if (fx != x) {
		fx += dx;
	    }
	    if (fy != y) {
		fy += dy;
	    }
	    
	    delay(10);
	    mouseMove(fx, fy);
	}
	ox = x;
	oy = y;
    }
    
    public void moveMouseTo(int x, int y) {
	moveMouse(ox, oy, x, y);
    }
    
    public void moveMouseRel(int x, int y) {
	moveMouse(ox, oy, ox + x, oy + y);
    }
    
    public void click() {
	mousePress(InputEvent.BUTTON1_MASK);
	delay(10);
	mouseRelease(InputEvent.BUTTON1_MASK);
    }
    
    public void doubleClick() {
	click();
	delay(100);
	click();
    }
    
    public void deleteAndType(int n) {
	deleteAndType(""+n);
    }
    public void deleteAndType(String s) {
	doubleClick();
	delay(500);
	for (int i = 0; i < 10; i++) {
	    keyPress(KeyEvent.VK_DELETE);
	    delay((int) Math.random() * 200);
	    keyPress(KeyEvent.VK_BACK_SPACE);
	    delay((int) Math.random() * 200);
	}
	for (int i = 0; i < s.length(); i++) {
	    try {
		char c = s.charAt(i);
		Field att = KeyEvent.class.getField("VK_" + c);
		keyPress((int) att.getInt(KeyEvent.class));
	    } catch (NoSuchFieldException ex) {
	    } catch (SecurityException ex) {
	    } catch (IllegalArgumentException ex) {
	    } catch (IllegalAccessException ex) {
	    }
	    delay((int) Math.random() * 200 + 100);
	}
	delay(100);
	keyPress(KeyEvent.VK_ENTER);
    }
    public void type(String s) {
	for (int i = 0; i < s.length(); i++) {
	    try {
		char c = s.charAt(i);
		if (c == ' ') {
		    keyPress(KeyEvent.VK_SPACE);
		} else {
		    Field att = KeyEvent.class.getField("VK_" + Character.toUpperCase(c));
		    keyPress((int) att.getInt(KeyEvent.class));
		}
	    } catch (NoSuchFieldException ex) {
	    } catch (SecurityException ex) {
	    } catch (IllegalArgumentException ex) {
	    } catch (IllegalAccessException ex) {
	    }
	    delay((int) Math.random() * 200 + 100);
	}
	delay(100);
    }
    
    public void gotoComponent(Component tblHops) {
	Point p2 = tblHops.getLocationOnScreen();
	moveMouseTo(p2.x + 5, p2.y + 5);
    }
}
