package de.tobiwild.fallbacks;

import java.util.List;

/**
 * @since 3.2
 *
 */
public interface IFallbackGroup {
	/**
	 * @return
	 */
	public String getPath();
	
	/**
	 * @return
	 */
	public List<String> getFallbacks();
}
