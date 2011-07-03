package de.tobiwild.fallbacks;

import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;

/**
 * @since 3.2
 *
 */
public class ResourceFallbackGroup implements IFallbackGroup {
	private IResource resource;
	private IFallbackGroup fallbackGroup;
	
	/**
	 * @param resource
	 * @param fallbackGroup
	 */
	public ResourceFallbackGroup(IResource resource,
			IFallbackGroup fallbackGroup) {
		
		this.resource = resource;
		this.fallbackGroup = fallbackGroup;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.fallbacks.navigator.IFallbackGroup#getPath()
	 */
	public String getPath() {
		for ( String path : fallbackGroup.getFallbacks() ) {		
			String fullPath = resource.getFullPath().toString();
			String projectName = resource.getProject().getName();
	
			String rootPath = IPath.SEPARATOR + projectName + path;
	
			if ( fullPath.startsWith(rootPath) ) {
				return path;
			}
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.fallbacks.navigator.IFallbackGroup#getFallbacks()
	 */
	public List getFallbacks() {
		List<String> fallbacks = fallbackGroup.getFallbacks();
		int i = 0;
		
		for ( String path : fallbacks ) {		
			String fullPath = resource.getFullPath().toString();
			String projectName = resource.getProject().getName();
	
			String rootPath = IPath.SEPARATOR + projectName + path;
	
			if ( fullPath.startsWith(rootPath) ) {
				break;
			}
			
			i++;
		}
		
		return fallbacks.subList(i+1, fallbacks.size() );
	}
	
	
}
