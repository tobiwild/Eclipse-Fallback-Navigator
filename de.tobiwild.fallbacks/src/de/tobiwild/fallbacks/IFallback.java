package de.tobiwild.fallbacks;

import org.eclipse.core.resources.IResource;

/**
 * @since 3.2
 *
 */
public interface IFallback {
	/**
	 * @return
	 */
	public IFallbackGroup getFallbackGroup();
	
	/**
	 * @return
	 */
	public IResource getFallbackParent();
}
