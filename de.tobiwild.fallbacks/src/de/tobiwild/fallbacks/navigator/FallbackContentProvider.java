package de.tobiwild.fallbacks.navigator;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.progress.UIJob;
import org.yaml.snakeyaml.Yaml;

import de.tobiwild.fallbacks.Activator;
import de.tobiwild.fallbacks.FallbackFile;
import de.tobiwild.fallbacks.FallbackFolder;
import de.tobiwild.fallbacks.FallbackGroup;
import de.tobiwild.fallbacks.IFallback;
import de.tobiwild.fallbacks.IFallbackGroup;
import de.tobiwild.fallbacks.preferences.PreferenceConstants;

public class FallbackContentProvider implements ITreeContentProvider, IResourceChangeListener, IResourceDeltaVisitor {

	private static List fallbackGroups;

	private TreeViewer viewer;

	public FallbackContentProvider() {
		updateFallbacks();

		ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
	}

	public static void updateFallbacks() {
		fallbackGroups = new ArrayList();

		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
	
		File yamlFile = new File( store.getString(PreferenceConstants.P_FILE) );
		
		try {
			FileReader input = new FileReader(yamlFile);
			
			Yaml yaml = new Yaml();
			for (Object data : yaml.loadAll(input)) {
				Map<String, Object> map = (Map<String, Object>) data;
				String image = (String) map.get("image"); //$NON-NLS-1$
				
				if ( image != null ) {
					Path imagePath = new Path(image);
					if ( !imagePath.isAbsolute() ) {
						Path yamlPath = new Path( yamlFile.getParent() );
						image = yamlPath.append(imagePath).toString();
					}
				}
					
				FallbackGroup fallback = new FallbackGroup( (String) map.get("path"), //$NON-NLS-1$
						(ArrayList) map.get("fallbacks"), image ); //$NON-NLS-1$
				fallbackGroups.add(fallback);
			}
		} catch ( IOException e ) {}
	}
	
	public Object[] getChildren(Object parentElement) {
		List result = new ArrayList();
		
		if(isValidResource(parentElement)) {
			IResource resource = (IResource) parentElement;

			result = getFallbacks(resource);
		}

		return result.toArray();
	}

	private static String getPathRelativeTo( IResource resource, String path ) {
		String fullPath = resource.getFullPath().toString();
		String projectName = resource.getProject().getName();

		String rootPath = IPath.SEPARATOR + projectName + path;

		if ( fullPath.startsWith(rootPath) ) {
			return fullPath.substring(rootPath.length(), fullPath.length());
		}

		return null;
	}

	private boolean isValidResource( Object element ) {
		boolean isFallbackInFolder = element instanceof FallbackFile && ((FallbackFile) element).getFallbackParent() instanceof IFolder;
		
		return element instanceof IFile && ! (element instanceof FallbackFile) || isFallbackInFolder || element instanceof IFolder;
	}

	public static FallbackGroup getPathMatchingFallbackGroup( IResource resource ) {
		Iterator it = fallbackGroups.iterator();
		while ( it.hasNext() ) {
			FallbackGroup fallbackGroup = (FallbackGroup) it.next();
			String relativePath = getPathRelativeTo(resource, fallbackGroup.getPath());
			if ( relativePath != null ) {
				return fallbackGroup;
			}
		}
		return null;

	}

	private IFallbackGroup getRelatedFallbackGroup( IResource resource ) {
		if ( resource instanceof IFallback ) {
			return ((IFallback) resource).getFallbackGroup();
		}
		
		return getPathMatchingFallbackGroup(resource);
	}
	
	private List getFallbacks( IResource resource ) {
		IProject project = resource.getProject();
		List result = new ArrayList();
		IFallbackGroup relatedFallbackGroup = getRelatedFallbackGroup(resource);

		if (relatedFallbackGroup != null) {
			String relativePath = getPathRelativeTo(resource, relatedFallbackGroup.getPath());
			List fallbacks = relatedFallbackGroup.getFallbacks();
			Iterator it = fallbacks.iterator();
			
			while ( it.hasNext() ) {
				String fallback = (String) it.next();

				Path path = new Path(fallback + relativePath);

				IResource fallbackResource = null;

				if ( resource instanceof IFile ) {
					fallbackResource = project.getFile(path);
					if (fallbackResource.exists()) {
						result.add(fallbackResource);
					}
				} else {
					fallbackResource = project.getFolder(path);
					if (fallbackResource.exists()) {
						result.addAll( getMissingResources( (IFolder) fallbackResource, (IFolder) resource, result ) );
					}
				}
			}
		}
		return createFallbackResources(result, relatedFallbackGroup, resource);
	}

	private List createFallbackResources( List resources, IFallbackGroup fallbackGroup, IResource parent ) {
		List result = new ArrayList();
		for ( Object resource : resources ) {
			if ( resource instanceof IFile ) {
				result.add( new FallbackFile( (IFile) resource, fallbackGroup, parent));
			} else if ( resource instanceof IFolder ) {
				result.add( new FallbackFolder( (IFolder) resource, fallbackGroup, parent));
			}
		}
		return result;
	}
	
	/*
	 * Returns all resources from source folder, that are missing in target folder
	 * and not in the ignore list
	 */
	private List getMissingResources( IFolder source, IFolder target, List ignoreList ) {
		List result = new ArrayList();
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		boolean includeFolder = store.getBoolean(PreferenceConstants.P_FOLDER_FALLBACK);
		
		try {
			List sourceMembers = Arrays.asList(source.members());
			Iterator it = sourceMembers.iterator();

			while ( it.hasNext() ) {
				IResource resource = (IResource) it.next();

				if ( ! includeFolder && resource instanceof IFolder ) {
					continue;
				}
					
				boolean ignoreResource = false;
				Iterator ignoreIterator = ignoreList.iterator();
				while ( ignoreIterator.hasNext() ) {
					IResource additional = (IResource) ignoreIterator.next();
					if ( additional.getName().equals(resource.getName())) {
						ignoreResource = true;
						break;
					}
				}

				if ( !ignoreResource && target.findMember( resource.getName() ) == null) {
					result.add(resource);
				}
			}
		} catch ( CoreException e ) {
			return Collections.EMPTY_LIST;
		}

		return result;
	}

	public Object getParent(Object element) {
		if (element instanceof IResource) {
			IResource resource = (IResource) element;
			return resource.getParent();
		}
		return null;
	}

	public boolean hasChildren(Object element) {
		if(element instanceof IResource) {
			IResource resourse = (IResource) element;
			return isValidResource(element) &&
			      resourse.getParent() instanceof IFolder && !getFallbacks(resourse).isEmpty();
		}
		return false;
	}

	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
	}

	public void inputChanged(Viewer aViewer, Object oldInput, Object newInput) {
		viewer = (TreeViewer) aViewer;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
	 */
	public void resourceChanged(IResourceChangeEvent event) {

		IResourceDelta delta = event.getDelta();
		try {
			delta.accept(this);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
	 */
	public boolean visit(IResourceDelta delta) {

		final IResource source = delta.getResource();
		switch (source.getType()) {
		case IResource.ROOT:
		case IResource.PROJECT:
			return true;
		case IResource.FOLDER:
		case IResource.FILE:
			if (isValidResource(source)) {
				new UIJob("Update fallback templates") {  //$NON-NLS-1$
					public IStatus runInUIThread(IProgressMonitor monitor) {
						if (viewer != null && !viewer.getControl().isDisposed())
							viewer.refresh(source, true);

						return Status.OK_STATUS;
					}
				}.schedule();
			}
			return false;
		}
		return false;
	}
}
