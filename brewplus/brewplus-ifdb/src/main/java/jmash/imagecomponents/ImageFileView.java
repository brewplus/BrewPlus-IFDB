/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package jmash.imagecomponents;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

/* ImageFileView.java is used by FileChooserDemo2.java. */
public class ImageFileView extends FileView {
	ImageIcon jpgIcon = Utils.createImageIcon("/jmash/images/jpgIcon.png");
	ImageIcon gifIcon = Utils.createImageIcon("/jmash/images/gifIcon.png");
	ImageIcon tiffIcon = Utils.createImageIcon("/jmash/images/tifIcon.png");
	ImageIcon pngIcon = Utils.createImageIcon("/jmash/images/pngIcon.png");

	public String getName(File f) {
		return null; // let the L&F FileView figure this out
	}

	public String getDescription(File f) {
		return null; // let the L&F FileView figure this out
	}

	public Boolean isTraversable(File f) {
		return null; // let the L&F FileView figure this out
	}

	public String getTypeDescription(File f) {
		String extension = Utils.getExtension(f);
		String type = null;

		if (extension != null) {
			if (extension.equals(Utils.jpeg) || extension.equals(Utils.jpg)) {
				type = "Immagine JPEG";
			} else if (extension.equals(Utils.gif)) {
				type = "Immagine GIF";
			} else if (extension.equals(Utils.tiff) || extension.equals(Utils.tif)) {
				type = "Immagine TIFF";
			} else if (extension.equals(Utils.png)) {
				type = "Immagine PNG";
			}
		}
		return type;
	}

	public Icon getIcon(File f) {
		String extension = Utils.getExtension(f);
		Icon icon = null;

		if (extension != null) {
			if (extension.equals(Utils.jpeg) || extension.equals(Utils.jpg)) {
				icon = jpgIcon;
			} else if (extension.equals(Utils.gif)) {
				icon = gifIcon;
			} else if (extension.equals(Utils.tiff) || extension.equals(Utils.tif)) {
				icon = tiffIcon;
			} else if (extension.equals(Utils.png)) {
				icon = pngIcon;
			}
		}
		return icon;
	}
}
