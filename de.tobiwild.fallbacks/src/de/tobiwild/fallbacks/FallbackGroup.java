package de.tobiwild.fallbacks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @since 3.2
 *
 */
public class FallbackGroup implements IFallbackGroup {
	private String path;
	private List fallbacks;
	private String image;
	
	public FallbackGroup( String path, List fallbacks, String image) {
		this.path = path;
		this.fallbacks = fallbacks;
		this.image = image;
	}

	/**
	 * @return Returns the path.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @return Returns the fallbacks.
	 */
	public List<String> getFallbacks() {
		if ( fallbacks == null ) {
			return Collections.EMPTY_LIST;
		}
		return fallbacks;
	}

	/**
	 * @return Returns the image.
	 */
	public String getImage() {
		return image;
	}
	
	public List<String> getAllPaths() {
		List<String> paths = new ArrayList<String>();
		fallbacks.add(path);
		fallbacks.addAll( fallbacks );
		return paths;
	}
	
	
}
