package de.tobiwild.fallbacks;

import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFileState;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IPathVariableManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceProxy;
import org.eclipse.core.resources.IResourceProxyVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.jobs.ISchedulingRule;

/**
 * @since 3.2
 *
 */
public class FallbackFile implements IFile, IFallback {
	private IFile file;
	private IFallbackGroup fallbackGroup;
	private IResource parent;
	
	/**
	 * @param file
	 * @param fallback
	 */
	public FallbackFile(IFile file, IFallbackGroup fallback, IResource parent) {
		this.file = file;
		this.fallbackGroup = fallback;
		this.parent = parent;
	}

	/**
	 * @return Returns the resource.
	 */
	public IResource getFile() {
		return file;
	}

	/**
	 * @return Returns the fallback.
	 */
	public IFallbackGroup getFallbackGroup() {
		return new ResourceFallbackGroup(this, fallbackGroup);
	}
	
	public IResource getFallbackParent() {
		return parent;
	}
	
	/**
	 * @param adapter
	 * @return
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class adapter) {
		return file.getAdapter(adapter);
	}

	/**
	 * @param rule
	 * @return
	 * @see org.eclipse.core.runtime.jobs.ISchedulingRule#contains(org.eclipse.core.runtime.jobs.ISchedulingRule)
	 */
	public boolean contains(ISchedulingRule rule) {
		return file.contains(rule);
	}

	/**
	 * @param rule
	 * @return
	 * @see org.eclipse.core.runtime.jobs.ISchedulingRule#isConflicting(org.eclipse.core.runtime.jobs.ISchedulingRule)
	 */
	public boolean isConflicting(ISchedulingRule rule) {
		return file.isConflicting(rule);
	}

	/**
	 * @param source
	 * @param force
	 * @param keepHistory
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFile#appendContents(java.io.InputStream, boolean, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void appendContents(InputStream source, boolean force,
			boolean keepHistory, IProgressMonitor monitor) throws CoreException {
		file.appendContents(source, force, keepHistory, monitor);
	}

	/**
	 * @param source
	 * @param updateFlags
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFile#appendContents(java.io.InputStream, int, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void appendContents(InputStream source, int updateFlags,
			IProgressMonitor monitor) throws CoreException {
		file.appendContents(source, updateFlags, monitor);
	}

	/**
	 * @param source
	 * @param force
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFile#create(java.io.InputStream, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void create(InputStream source, boolean force,
			IProgressMonitor monitor) throws CoreException {
		file.create(source, force, monitor);
	}

	/**
	 * @param source
	 * @param updateFlags
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFile#create(java.io.InputStream, int, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void create(InputStream source, int updateFlags,
			IProgressMonitor monitor) throws CoreException {
		file.create(source, updateFlags, monitor);
	}

	/**
	 * @param visitor
	 * @param memberFlags
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#accept(org.eclipse.core.resources.IResourceProxyVisitor, int)
	 */
	public void accept(IResourceProxyVisitor visitor, int memberFlags)
			throws CoreException {
		file.accept(visitor, memberFlags);
	}

	/**
	 * @param visitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#accept(org.eclipse.core.resources.IResourceVisitor)
	 */
	public void accept(IResourceVisitor visitor) throws CoreException {
		file.accept(visitor);
	}

	/**
	 * @param visitor
	 * @param depth
	 * @param includePhantoms
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#accept(org.eclipse.core.resources.IResourceVisitor, int, boolean)
	 */
	public void accept(IResourceVisitor visitor, int depth,
			boolean includePhantoms) throws CoreException {
		file.accept(visitor, depth, includePhantoms);
	}

	/**
	 * @param localLocation
	 * @param updateFlags
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFile#createLink(org.eclipse.core.runtime.IPath, int, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void createLink(IPath localLocation, int updateFlags,
			IProgressMonitor monitor) throws CoreException {
		file.createLink(localLocation, updateFlags, monitor);
	}

	/**
	 * @param visitor
	 * @param depth
	 * @param memberFlags
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#accept(org.eclipse.core.resources.IResourceVisitor, int, int)
	 */
	public void accept(IResourceVisitor visitor, int depth, int memberFlags)
			throws CoreException {
		file.accept(visitor, depth, memberFlags);
	}

	/**
	 * @param location
	 * @param updateFlags
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFile#createLink(java.net.URI, int, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void createLink(URI location, int updateFlags,
			IProgressMonitor monitor) throws CoreException {
		file.createLink(location, updateFlags, monitor);
	}

	/**
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#clearHistory(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void clearHistory(IProgressMonitor monitor) throws CoreException {
		file.clearHistory(monitor);
	}

	/**
	 * @param destination
	 * @param force
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#copy(org.eclipse.core.runtime.IPath, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void copy(IPath destination, boolean force, IProgressMonitor monitor)
			throws CoreException {
		file.copy(destination, force, monitor);
	}

	/**
	 * @param destination
	 * @param updateFlags
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#copy(org.eclipse.core.runtime.IPath, int, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void copy(IPath destination, int updateFlags,
			IProgressMonitor monitor) throws CoreException {
		file.copy(destination, updateFlags, monitor);
	}

	/**
	 * @param force
	 * @param keepHistory
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFile#delete(boolean, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void delete(boolean force, boolean keepHistory,
			IProgressMonitor monitor) throws CoreException {
		file.delete(force, keepHistory, monitor);
	}

	/**
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFile#getCharset()
	 */
	public String getCharset() throws CoreException {
		return file.getCharset();
	}

	/**
	 * @param checkImplicit
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFile#getCharset(boolean)
	 */
	public String getCharset(boolean checkImplicit) throws CoreException {
		return file.getCharset(checkImplicit);
	}

	/**
	 * @param reader
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFile#getCharsetFor(java.io.Reader)
	 */
	public String getCharsetFor(Reader reader) throws CoreException {
		return file.getCharsetFor(reader);
	}

	/**
	 * @param description
	 * @param force
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#copy(org.eclipse.core.resources.IProjectDescription, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void copy(IProjectDescription description, boolean force,
			IProgressMonitor monitor) throws CoreException {
		file.copy(description, force, monitor);
	}

	/**
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFile#getContentDescription()
	 */
	public IContentDescription getContentDescription() throws CoreException {
		return file.getContentDescription();
	}

	/**
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFile#getContents()
	 */
	public InputStream getContents() throws CoreException {
		return file.getContents();
	}

	/**
	 * @param description
	 * @param updateFlags
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#copy(org.eclipse.core.resources.IProjectDescription, int, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void copy(IProjectDescription description, int updateFlags,
			IProgressMonitor monitor) throws CoreException {
		file.copy(description, updateFlags, monitor);
	}

	/**
	 * @param force
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFile#getContents(boolean)
	 */
	public InputStream getContents(boolean force) throws CoreException {
		return file.getContents(force);
	}

	/**
	 * @return
	 * @throws CoreException
	 * @deprecated
	 * @see org.eclipse.core.resources.IFile#getEncoding()
	 */
	public int getEncoding() throws CoreException {
		return file.getEncoding();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IFile#getFullPath()
	 */
	public IPath getFullPath() {
		return file.getFullPath();
	}

	/**
	 * @param monitor
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFile#getHistory(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public IFileState[] getHistory(IProgressMonitor monitor)
			throws CoreException {
		return file.getHistory(monitor);
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IFile#getName()
	 */
	public String getName() {
		return file.getName();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IFile#isReadOnly()
	 */
	public boolean isReadOnly() {
		return file.isReadOnly();
	}

	/**
	 * @param destination
	 * @param force
	 * @param keepHistory
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFile#move(org.eclipse.core.runtime.IPath, boolean, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void move(IPath destination, boolean force, boolean keepHistory,
			IProgressMonitor monitor) throws CoreException {
		file.move(destination, force, keepHistory, monitor);
	}

	/**
	 * @param type
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#createMarker(java.lang.String)
	 */
	public IMarker createMarker(String type) throws CoreException {
		return file.createMarker(type);
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#createProxy()
	 */
	public IResourceProxy createProxy() {
		return file.createProxy();
	}

	/**
	 * @param force
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#delete(boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void delete(boolean force, IProgressMonitor monitor)
			throws CoreException {
		file.delete(force, monitor);
	}

	/**
	 * @param newCharset
	 * @throws CoreException
	 * @deprecated
	 * @see org.eclipse.core.resources.IFile#setCharset(java.lang.String)
	 */
	public void setCharset(String newCharset) throws CoreException {
		file.setCharset(newCharset);
	}

	/**
	 * @param updateFlags
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#delete(int, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void delete(int updateFlags, IProgressMonitor monitor)
			throws CoreException {
		file.delete(updateFlags, monitor);
	}

	/**
	 * @param newCharset
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFile#setCharset(java.lang.String, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void setCharset(String newCharset, IProgressMonitor monitor)
			throws CoreException {
		file.setCharset(newCharset, monitor);
	}

	/**
	 * @param source
	 * @param force
	 * @param keepHistory
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFile#setContents(java.io.InputStream, boolean, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void setContents(InputStream source, boolean force,
			boolean keepHistory, IProgressMonitor monitor) throws CoreException {
		file.setContents(source, force, keepHistory, monitor);
	}

	/**
	 * @param source
	 * @param force
	 * @param keepHistory
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFile#setContents(org.eclipse.core.resources.IFileState, boolean, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void setContents(IFileState source, boolean force,
			boolean keepHistory, IProgressMonitor monitor) throws CoreException {
		file.setContents(source, force, keepHistory, monitor);
	}

	/**
	 * @param source
	 * @param updateFlags
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFile#setContents(java.io.InputStream, int, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void setContents(InputStream source, int updateFlags,
			IProgressMonitor monitor) throws CoreException {
		file.setContents(source, updateFlags, monitor);
	}

	/**
	 * @param type
	 * @param includeSubtypes
	 * @param depth
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#deleteMarkers(java.lang.String, boolean, int)
	 */
	public void deleteMarkers(String type, boolean includeSubtypes, int depth)
			throws CoreException {
		file.deleteMarkers(type, includeSubtypes, depth);
	}

	/**
	 * @param other
	 * @return
	 * @see org.eclipse.core.resources.IResource#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if (other instanceof FallbackFile) {
			return file.equals( ((FallbackFile) other).getFile() );
		}
		return file.equals(other);
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#exists()
	 */
	public boolean exists() {
		return file.exists();
	}

	/**
	 * @param id
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#findMarker(long)
	 */
	public IMarker findMarker(long id) throws CoreException {
		return file.findMarker(id);
	}

	/**
	 * @param type
	 * @param includeSubtypes
	 * @param depth
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#findMarkers(java.lang.String, boolean, int)
	 */
	public IMarker[] findMarkers(String type, boolean includeSubtypes, int depth)
			throws CoreException {
		return file.findMarkers(type, includeSubtypes, depth);
	}

	/**
	 * @param source
	 * @param updateFlags
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFile#setContents(org.eclipse.core.resources.IFileState, int, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void setContents(IFileState source, int updateFlags,
			IProgressMonitor monitor) throws CoreException {
		file.setContents(source, updateFlags, monitor);
	}

	/**
	 * @param type
	 * @param includeSubtypes
	 * @param depth
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#findMaxProblemSeverity(java.lang.String, boolean, int)
	 */
	public int findMaxProblemSeverity(String type, boolean includeSubtypes,
			int depth) throws CoreException {
		return file.findMaxProblemSeverity(type, includeSubtypes, depth);
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getFileExtension()
	 */
	public String getFileExtension() {
		return file.getFileExtension();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getLocalTimeStamp()
	 */
	public long getLocalTimeStamp() {
		return file.getLocalTimeStamp();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getLocation()
	 */
	public IPath getLocation() {
		return file.getLocation();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getLocationURI()
	 */
	public URI getLocationURI() {
		return file.getLocationURI();
	}

	/**
	 * @param id
	 * @return
	 * @see org.eclipse.core.resources.IResource#getMarker(long)
	 */
	public IMarker getMarker(long id) {
		return file.getMarker(id);
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getModificationStamp()
	 */
	public long getModificationStamp() {
		return file.getModificationStamp();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getPathVariableManager()
	 */
	public IPathVariableManager getPathVariableManager() {
		return file.getPathVariableManager();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getParent()
	 */
	public IContainer getParent() {
		return file.getParent();
	}

	/**
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#getPersistentProperties()
	 */
	public Map getPersistentProperties() throws CoreException {
		return file.getPersistentProperties();
	}

	/**
	 * @param key
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#getPersistentProperty(org.eclipse.core.runtime.QualifiedName)
	 */
	public String getPersistentProperty(QualifiedName key) throws CoreException {
		return file.getPersistentProperty(key);
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getProject()
	 */
	public IProject getProject() {
		return file.getProject();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getProjectRelativePath()
	 */
	public IPath getProjectRelativePath() {
		return file.getProjectRelativePath();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getRawLocation()
	 */
	public IPath getRawLocation() {
		return file.getRawLocation();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getRawLocationURI()
	 */
	public URI getRawLocationURI() {
		return file.getRawLocationURI();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getResourceAttributes()
	 */
	public ResourceAttributes getResourceAttributes() {
		return file.getResourceAttributes();
	}

	/**
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#getSessionProperties()
	 */
	public Map getSessionProperties() throws CoreException {
		return file.getSessionProperties();
	}

	/**
	 * @param key
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#getSessionProperty(org.eclipse.core.runtime.QualifiedName)
	 */
	public Object getSessionProperty(QualifiedName key) throws CoreException {
		return file.getSessionProperty(key);
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getType()
	 */
	public int getType() {
		return file.getType();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getWorkspace()
	 */
	public IWorkspace getWorkspace() {
		return file.getWorkspace();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#isAccessible()
	 */
	public boolean isAccessible() {
		return file.isAccessible();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#isDerived()
	 */
	public boolean isDerived() {
		return file.isDerived();
	}

	/**
	 * @param options
	 * @return
	 * @see org.eclipse.core.resources.IResource#isDerived(int)
	 */
	public boolean isDerived(int options) {
		return file.isDerived(options);
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#isHidden()
	 */
	public boolean isHidden() {
		return file.isHidden();
	}

	/**
	 * @param options
	 * @return
	 * @see org.eclipse.core.resources.IResource#isHidden(int)
	 */
	public boolean isHidden(int options) {
		return file.isHidden(options);
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#isLinked()
	 */
	public boolean isLinked() {
		return file.isLinked();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#isVirtual()
	 */
	public boolean isVirtual() {
		return file.isVirtual();
	}

	/**
	 * @param options
	 * @return
	 * @see org.eclipse.core.resources.IResource#isLinked(int)
	 */
	public boolean isLinked(int options) {
		return file.isLinked(options);
	}

	/**
	 * @param depth
	 * @return
	 * @deprecated
	 * @see org.eclipse.core.resources.IResource#isLocal(int)
	 */
	public boolean isLocal(int depth) {
		return file.isLocal(depth);
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#isPhantom()
	 */
	public boolean isPhantom() {
		return file.isPhantom();
	}

	/**
	 * @param depth
	 * @return
	 * @see org.eclipse.core.resources.IResource#isSynchronized(int)
	 */
	public boolean isSynchronized(int depth) {
		return file.isSynchronized(depth);
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#isTeamPrivateMember()
	 */
	public boolean isTeamPrivateMember() {
		return file.isTeamPrivateMember();
	}

	/**
	 * @param options
	 * @return
	 * @see org.eclipse.core.resources.IResource#isTeamPrivateMember(int)
	 */
	public boolean isTeamPrivateMember(int options) {
		return file.isTeamPrivateMember(options);
	}

	/**
	 * @param destination
	 * @param force
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#move(org.eclipse.core.runtime.IPath, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void move(IPath destination, boolean force, IProgressMonitor monitor)
			throws CoreException {
		file.move(destination, force, monitor);
	}

	/**
	 * @param destination
	 * @param updateFlags
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#move(org.eclipse.core.runtime.IPath, int, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void move(IPath destination, int updateFlags,
			IProgressMonitor monitor) throws CoreException {
		file.move(destination, updateFlags, monitor);
	}

	/**
	 * @param description
	 * @param force
	 * @param keepHistory
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#move(org.eclipse.core.resources.IProjectDescription, boolean, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void move(IProjectDescription description, boolean force,
			boolean keepHistory, IProgressMonitor monitor) throws CoreException {
		file.move(description, force, keepHistory, monitor);
	}

	/**
	 * @param description
	 * @param updateFlags
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#move(org.eclipse.core.resources.IProjectDescription, int, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void move(IProjectDescription description, int updateFlags,
			IProgressMonitor monitor) throws CoreException {
		file.move(description, updateFlags, monitor);
	}

	/**
	 * @param depth
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#refreshLocal(int, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void refreshLocal(int depth, IProgressMonitor monitor)
			throws CoreException {
		file.refreshLocal(depth, monitor);
	}

	/**
	 * @param value
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#revertModificationStamp(long)
	 */
	public void revertModificationStamp(long value) throws CoreException {
		file.revertModificationStamp(value);
	}

	/**
	 * @param isDerived
	 * @throws CoreException
	 * @deprecated
	 * @see org.eclipse.core.resources.IResource#setDerived(boolean)
	 */
	public void setDerived(boolean isDerived) throws CoreException {
		file.setDerived(isDerived);
	}

	/**
	 * @param isDerived
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#setDerived(boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void setDerived(boolean isDerived, IProgressMonitor monitor)
			throws CoreException {
		file.setDerived(isDerived, monitor);
	}

	/**
	 * @param isHidden
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#setHidden(boolean)
	 */
	public void setHidden(boolean isHidden) throws CoreException {
		file.setHidden(isHidden);
	}

	/**
	 * @param flag
	 * @param depth
	 * @param monitor
	 * @throws CoreException
	 * @deprecated
	 * @see org.eclipse.core.resources.IResource#setLocal(boolean, int, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void setLocal(boolean flag, int depth, IProgressMonitor monitor)
			throws CoreException {
		file.setLocal(flag, depth, monitor);
	}

	/**
	 * @param value
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#setLocalTimeStamp(long)
	 */
	public long setLocalTimeStamp(long value) throws CoreException {
		return file.setLocalTimeStamp(value);
	}

	/**
	 * @param key
	 * @param value
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#setPersistentProperty(org.eclipse.core.runtime.QualifiedName, java.lang.String)
	 */
	public void setPersistentProperty(QualifiedName key, String value)
			throws CoreException {
		file.setPersistentProperty(key, value);
	}

	/**
	 * @param readOnly
	 * @deprecated
	 * @see org.eclipse.core.resources.IResource#setReadOnly(boolean)
	 */
	public void setReadOnly(boolean readOnly) {
		file.setReadOnly(readOnly);
	}

	/**
	 * @param attributes
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#setResourceAttributes(org.eclipse.core.resources.ResourceAttributes)
	 */
	public void setResourceAttributes(ResourceAttributes attributes)
			throws CoreException {
		file.setResourceAttributes(attributes);
	}

	/**
	 * @param key
	 * @param value
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#setSessionProperty(org.eclipse.core.runtime.QualifiedName, java.lang.Object)
	 */
	public void setSessionProperty(QualifiedName key, Object value)
			throws CoreException {
		file.setSessionProperty(key, value);
	}

	/**
	 * @param isTeamPrivate
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#setTeamPrivateMember(boolean)
	 */
	public void setTeamPrivateMember(boolean isTeamPrivate)
			throws CoreException {
		file.setTeamPrivateMember(isTeamPrivate);
	}

	/**
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#touch(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void touch(IProgressMonitor monitor) throws CoreException {
		file.touch(monitor);
	}
	
}
