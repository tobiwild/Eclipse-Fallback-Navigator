package de.tobiwild.fallbacks.navigator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import de.tobiwild.fallbacks.FallbackGroup;
import de.tobiwild.fallbacks.IFallback;


/**
 * Provides a label and icon for objects of type {@link PropertiesTreeData}.
 *
 * @since 3.2
 *
 */
public class FallbackLabelProvider extends LabelProvider implements
		ILabelProvider, IColorProvider {

	private IResource getResource( Object element ) {
		return (element instanceof IFile) ? (IResource) element : null;
	}
	
	public Image getImage(Object element) {
		IResource resource = getResource(element);
		
		if (resource != null) {
			FallbackGroup fallbackGroup = FallbackContentProvider.getPathMatchingFallbackGroup(resource);
			if ( fallbackGroup != null && fallbackGroup.getImage() != null) {
				try {
					Image image = new Image(null, fallbackGroup.getImage());
					return image;
				} catch ( SWTException e ) {
					return null;
				}
				/*ImageDescriptor imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, fallbackGroup.getImage());
				Image image = imageDescriptor.createImage();*/

			}
		}

		return null;
	}

	public String getText(Object element) {		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
	 */
	public Color getForeground(Object element) {
		if ( element instanceof IFallback ) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GRAY);
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IColorProvider#getBackground(java.lang.Object)
	 */
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}
}
