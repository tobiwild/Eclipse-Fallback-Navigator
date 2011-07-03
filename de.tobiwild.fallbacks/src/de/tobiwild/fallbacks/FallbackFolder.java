package de.tobiwild.fallbacks;

import java.net.URI;
import java.util.Map;

import org.eclipse.core.resources.FileInfoMatcherDescription;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IPathVariableManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceFilterDescription;
import org.eclipse.core.resources.IResourceProxy;
import org.eclipse.core.resources.IResourceProxyVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.jobs.ISchedulingRule;

/**
 * @since 3.2
 *
 */
public class FallbackFolder implements IFolder, IFallback {
	private IFolder folder;
	private IFallbackGroup fallbackGroup;
	private IResource parent;
	
	/**
	 * @param folder 
	 * @param fallback
	 * @param parent 
	 */
	public FallbackFolder(IFolder folder, IFallbackGroup fallback, IResource parent) {
		this.folder = folder;
		this.fallbackGroup = fallback;
		this.parent = parent;
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
		return folder.getAdapter(adapter);
	}

	/**
	 * @param rule
	 * @return
	 * @see org.eclipse.core.runtime.jobs.ISchedulingRule#contains(org.eclipse.core.runtime.jobs.ISchedulingRule)
	 */
	public boolean contains(ISchedulingRule rule) {
		return folder.contains(rule);
	}

	/**
	 * @param force
	 * @param local
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFolder#create(boolean, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void create(boolean force, boolean local, IProgressMonitor monitor)
			throws CoreException {
		folder.create(force, local, monitor);
	}

	/**
	 * @param rule
	 * @return
	 * @see org.eclipse.core.runtime.jobs.ISchedulingRule#isConflicting(org.eclipse.core.runtime.jobs.ISchedulingRule)
	 */
	public boolean isConflicting(ISchedulingRule rule) {
		return folder.isConflicting(rule);
	}

	/**
	 * @param path
	 * @return
	 * @see org.eclipse.core.resources.IContainer#exists(org.eclipse.core.runtime.IPath)
	 */
	public boolean exists(IPath path) {
		return folder.exists(path);
	}

	/**
	 * @param name
	 * @return
	 * @see org.eclipse.core.resources.IContainer#findMember(java.lang.String)
	 */
	public IResource findMember(String name) {
		return folder.findMember(name);
	}

	/**
	 * @param name
	 * @param includePhantoms
	 * @return
	 * @see org.eclipse.core.resources.IContainer#findMember(java.lang.String, boolean)
	 */
	public IResource findMember(String name, boolean includePhantoms) {
		return folder.findMember(name, includePhantoms);
	}

	/**
	 * @param updateFlags
	 * @param local
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFolder#create(int, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void create(int updateFlags, boolean local, IProgressMonitor monitor)
			throws CoreException {
		folder.create(updateFlags, local, monitor);
	}

	/**
	 * @param path
	 * @return
	 * @see org.eclipse.core.resources.IContainer#findMember(org.eclipse.core.runtime.IPath)
	 */
	public IResource findMember(IPath path) {
		return folder.findMember(path);
	}

	/**
	 * @param path
	 * @param includePhantoms
	 * @return
	 * @see org.eclipse.core.resources.IContainer#findMember(org.eclipse.core.runtime.IPath, boolean)
	 */
	public IResource findMember(IPath path, boolean includePhantoms) {
		return folder.findMember(path, includePhantoms);
	}

	/**
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IContainer#getDefaultCharset()
	 */
	public String getDefaultCharset() throws CoreException {
		return folder.getDefaultCharset();
	}

	/**
	 * @param localLocation
	 * @param updateFlags
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFolder#createLink(org.eclipse.core.runtime.IPath, int, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void createLink(IPath localLocation, int updateFlags,
			IProgressMonitor monitor) throws CoreException {
		folder.createLink(localLocation, updateFlags, monitor);
	}

	/**
	 * @param checkImplicit
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IContainer#getDefaultCharset(boolean)
	 */
	public String getDefaultCharset(boolean checkImplicit) throws CoreException {
		return folder.getDefaultCharset(checkImplicit);
	}

	/**
	 * @param path
	 * @return
	 * @see org.eclipse.core.resources.IContainer#getFile(org.eclipse.core.runtime.IPath)
	 */
	public IFile getFile(IPath path) {
		return folder.getFile(path);
	}

	/**
	 * @param path
	 * @return
	 * @see org.eclipse.core.resources.IContainer#getFolder(org.eclipse.core.runtime.IPath)
	 */
	public IFolder getFolder(IPath path) {
		return folder.getFolder(path);
	}

	/**
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IContainer#members()
	 */
	public IResource[] members() throws CoreException {
		return folder.members();
	}

	/**
	 * @param visitor
	 * @param memberFlags
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#accept(org.eclipse.core.resources.IResourceProxyVisitor, int)
	 */
	public void accept(IResourceProxyVisitor visitor, int memberFlags)
			throws CoreException {
		folder.accept(visitor, memberFlags);
	}

	/**
	 * @param includePhantoms
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IContainer#members(boolean)
	 */
	public IResource[] members(boolean includePhantoms) throws CoreException {
		return folder.members(includePhantoms);
	}

	/**
	 * @param location
	 * @param updateFlags
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFolder#createLink(java.net.URI, int, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void createLink(URI location, int updateFlags,
			IProgressMonitor monitor) throws CoreException {
		folder.createLink(location, updateFlags, monitor);
	}

	/**
	 * @param memberFlags
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IContainer#members(int)
	 */
	public IResource[] members(int memberFlags) throws CoreException {
		return folder.members(memberFlags);
	}

	/**
	 * @param visitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#accept(org.eclipse.core.resources.IResourceVisitor)
	 */
	public void accept(IResourceVisitor visitor) throws CoreException {
		folder.accept(visitor);
	}

	/**
	 * @param depth
	 * @param monitor
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IContainer#findDeletedMembersWithHistory(int, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public IFile[] findDeletedMembersWithHistory(int depth,
			IProgressMonitor monitor) throws CoreException {
		return folder.findDeletedMembersWithHistory(depth, monitor);
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
		folder.accept(visitor, depth, includePhantoms);
	}

	/**
	 * @param charset
	 * @throws CoreException
	 * @deprecated
	 * @see org.eclipse.core.resources.IContainer#setDefaultCharset(java.lang.String)
	 */
	public void setDefaultCharset(String charset) throws CoreException {
		folder.setDefaultCharset(charset);
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
		folder.accept(visitor, depth, memberFlags);
	}

	/**
	 * @param force
	 * @param keepHistory
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFolder#delete(boolean, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void delete(boolean force, boolean keepHistory,
			IProgressMonitor monitor) throws CoreException {
		folder.delete(force, keepHistory, monitor);
	}

	/**
	 * @param charset
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IContainer#setDefaultCharset(java.lang.String, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void setDefaultCharset(String charset, IProgressMonitor monitor)
			throws CoreException {
		folder.setDefaultCharset(charset, monitor);
	}

	/**
	 * @param name
	 * @return
	 * @see org.eclipse.core.resources.IFolder#getFile(java.lang.String)
	 */
	public IFile getFile(String name) {
		return folder.getFile(name);
	}

	/**
	 * @param type
	 * @param matcherDescription
	 * @param updateFlags
	 * @param monitor
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IContainer#createFilter(int, org.eclipse.core.resources.FileInfoMatcherDescription, int, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public IResourceFilterDescription createFilter(int type,
			FileInfoMatcherDescription matcherDescription, int updateFlags,
			IProgressMonitor monitor) throws CoreException {
		return folder.createFilter(type, matcherDescription, updateFlags,
				monitor);
	}

	/**
	 * @param name
	 * @return
	 * @see org.eclipse.core.resources.IFolder#getFolder(java.lang.String)
	 */
	public IFolder getFolder(String name) {
		return folder.getFolder(name);
	}

	/**
	 * @param destination
	 * @param force
	 * @param keepHistory
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IFolder#move(org.eclipse.core.runtime.IPath, boolean, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void move(IPath destination, boolean force, boolean keepHistory,
			IProgressMonitor monitor) throws CoreException {
		folder.move(destination, force, keepHistory, monitor);
	}

	/**
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#clearHistory(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void clearHistory(IProgressMonitor monitor) throws CoreException {
		folder.clearHistory(monitor);
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
		folder.copy(destination, force, monitor);
	}

	/**
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IContainer#getFilters()
	 */
	public IResourceFilterDescription[] getFilters() throws CoreException {
		return folder.getFilters();
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
		folder.copy(destination, updateFlags, monitor);
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
		folder.copy(description, force, monitor);
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
		folder.copy(description, updateFlags, monitor);
	}

	/**
	 * @param type
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#createMarker(java.lang.String)
	 */
	public IMarker createMarker(String type) throws CoreException {
		return folder.createMarker(type);
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#createProxy()
	 */
	public IResourceProxy createProxy() {
		return folder.createProxy();
	}

	/**
	 * @param force
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#delete(boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void delete(boolean force, IProgressMonitor monitor)
			throws CoreException {
		folder.delete(force, monitor);
	}

	/**
	 * @param updateFlags
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#delete(int, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void delete(int updateFlags, IProgressMonitor monitor)
			throws CoreException {
		folder.delete(updateFlags, monitor);
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
		folder.deleteMarkers(type, includeSubtypes, depth);
	}

	/**
	 * @param other
	 * @return
	 * @see org.eclipse.core.resources.IResource#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		return folder.equals(other);
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#exists()
	 */
	public boolean exists() {
		return folder.exists();
	}

	/**
	 * @param id
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#findMarker(long)
	 */
	public IMarker findMarker(long id) throws CoreException {
		return folder.findMarker(id);
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
		return folder.findMarkers(type, includeSubtypes, depth);
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
		return folder.findMaxProblemSeverity(type, includeSubtypes, depth);
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getFileExtension()
	 */
	public String getFileExtension() {
		return folder.getFileExtension();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getFullPath()
	 */
	public IPath getFullPath() {
		return folder.getFullPath();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getLocalTimeStamp()
	 */
	public long getLocalTimeStamp() {
		return folder.getLocalTimeStamp();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getLocation()
	 */
	public IPath getLocation() {
		return folder.getLocation();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getLocationURI()
	 */
	public URI getLocationURI() {
		return folder.getLocationURI();
	}

	/**
	 * @param id
	 * @return
	 * @see org.eclipse.core.resources.IResource#getMarker(long)
	 */
	public IMarker getMarker(long id) {
		return folder.getMarker(id);
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getModificationStamp()
	 */
	public long getModificationStamp() {
		return folder.getModificationStamp();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getName()
	 */
	public String getName() {
		return folder.getName();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getPathVariableManager()
	 */
	public IPathVariableManager getPathVariableManager() {
		return folder.getPathVariableManager();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getParent()
	 */
	public IContainer getParent() {
		return folder.getParent();
	}

	/**
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#getPersistentProperties()
	 */
	public Map getPersistentProperties() throws CoreException {
		return folder.getPersistentProperties();
	}

	/**
	 * @param key
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#getPersistentProperty(org.eclipse.core.runtime.QualifiedName)
	 */
	public String getPersistentProperty(QualifiedName key) throws CoreException {
		return folder.getPersistentProperty(key);
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getProject()
	 */
	public IProject getProject() {
		return folder.getProject();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getProjectRelativePath()
	 */
	public IPath getProjectRelativePath() {
		return folder.getProjectRelativePath();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getRawLocation()
	 */
	public IPath getRawLocation() {
		return folder.getRawLocation();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getRawLocationURI()
	 */
	public URI getRawLocationURI() {
		return folder.getRawLocationURI();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getResourceAttributes()
	 */
	public ResourceAttributes getResourceAttributes() {
		return folder.getResourceAttributes();
	}

	/**
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#getSessionProperties()
	 */
	public Map getSessionProperties() throws CoreException {
		return folder.getSessionProperties();
	}

	/**
	 * @param key
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#getSessionProperty(org.eclipse.core.runtime.QualifiedName)
	 */
	public Object getSessionProperty(QualifiedName key) throws CoreException {
		return folder.getSessionProperty(key);
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getType()
	 */
	public int getType() {
		return folder.getType();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#getWorkspace()
	 */
	public IWorkspace getWorkspace() {
		return folder.getWorkspace();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#isAccessible()
	 */
	public boolean isAccessible() {
		return folder.isAccessible();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#isDerived()
	 */
	public boolean isDerived() {
		return folder.isDerived();
	}

	/**
	 * @param options
	 * @return
	 * @see org.eclipse.core.resources.IResource#isDerived(int)
	 */
	public boolean isDerived(int options) {
		return folder.isDerived(options);
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#isHidden()
	 */
	public boolean isHidden() {
		return folder.isHidden();
	}

	/**
	 * @param options
	 * @return
	 * @see org.eclipse.core.resources.IResource#isHidden(int)
	 */
	public boolean isHidden(int options) {
		return folder.isHidden(options);
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#isLinked()
	 */
	public boolean isLinked() {
		return folder.isLinked();
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#isVirtual()
	 */
	public boolean isVirtual() {
		return folder.isVirtual();
	}

	/**
	 * @param options
	 * @return
	 * @see org.eclipse.core.resources.IResource#isLinked(int)
	 */
	public boolean isLinked(int options) {
		return folder.isLinked(options);
	}

	/**
	 * @param depth
	 * @return
	 * @deprecated
	 * @see org.eclipse.core.resources.IResource#isLocal(int)
	 */
	public boolean isLocal(int depth) {
		return folder.isLocal(depth);
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#isPhantom()
	 */
	public boolean isPhantom() {
		return folder.isPhantom();
	}

	/**
	 * @return
	 * @deprecated
	 * @see org.eclipse.core.resources.IResource#isReadOnly()
	 */
	public boolean isReadOnly() {
		return folder.isReadOnly();
	}

	/**
	 * @param depth
	 * @return
	 * @see org.eclipse.core.resources.IResource#isSynchronized(int)
	 */
	public boolean isSynchronized(int depth) {
		return folder.isSynchronized(depth);
	}

	/**
	 * @return
	 * @see org.eclipse.core.resources.IResource#isTeamPrivateMember()
	 */
	public boolean isTeamPrivateMember() {
		return folder.isTeamPrivateMember();
	}

	/**
	 * @param options
	 * @return
	 * @see org.eclipse.core.resources.IResource#isTeamPrivateMember(int)
	 */
	public boolean isTeamPrivateMember(int options) {
		return folder.isTeamPrivateMember(options);
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
		folder.move(destination, force, monitor);
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
		folder.move(destination, updateFlags, monitor);
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
		folder.move(description, force, keepHistory, monitor);
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
		folder.move(description, updateFlags, monitor);
	}

	/**
	 * @param depth
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#refreshLocal(int, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void refreshLocal(int depth, IProgressMonitor monitor)
			throws CoreException {
		folder.refreshLocal(depth, monitor);
	}

	/**
	 * @param value
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#revertModificationStamp(long)
	 */
	public void revertModificationStamp(long value) throws CoreException {
		folder.revertModificationStamp(value);
	}

	/**
	 * @param isDerived
	 * @throws CoreException
	 * @deprecated
	 * @see org.eclipse.core.resources.IResource#setDerived(boolean)
	 */
	public void setDerived(boolean isDerived) throws CoreException {
		folder.setDerived(isDerived);
	}

	/**
	 * @param isDerived
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#setDerived(boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void setDerived(boolean isDerived, IProgressMonitor monitor)
			throws CoreException {
		folder.setDerived(isDerived, monitor);
	}

	/**
	 * @param isHidden
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#setHidden(boolean)
	 */
	public void setHidden(boolean isHidden) throws CoreException {
		folder.setHidden(isHidden);
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
		folder.setLocal(flag, depth, monitor);
	}

	/**
	 * @param value
	 * @return
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#setLocalTimeStamp(long)
	 */
	public long setLocalTimeStamp(long value) throws CoreException {
		return folder.setLocalTimeStamp(value);
	}

	/**
	 * @param key
	 * @param value
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#setPersistentProperty(org.eclipse.core.runtime.QualifiedName, java.lang.String)
	 */
	public void setPersistentProperty(QualifiedName key, String value)
			throws CoreException {
		folder.setPersistentProperty(key, value);
	}

	/**
	 * @param readOnly
	 * @deprecated
	 * @see org.eclipse.core.resources.IResource#setReadOnly(boolean)
	 */
	public void setReadOnly(boolean readOnly) {
		folder.setReadOnly(readOnly);
	}

	/**
	 * @param attributes
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#setResourceAttributes(org.eclipse.core.resources.ResourceAttributes)
	 */
	public void setResourceAttributes(ResourceAttributes attributes)
			throws CoreException {
		folder.setResourceAttributes(attributes);
	}

	/**
	 * @param key
	 * @param value
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#setSessionProperty(org.eclipse.core.runtime.QualifiedName, java.lang.Object)
	 */
	public void setSessionProperty(QualifiedName key, Object value)
			throws CoreException {
		folder.setSessionProperty(key, value);
	}

	/**
	 * @param isTeamPrivate
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#setTeamPrivateMember(boolean)
	 */
	public void setTeamPrivateMember(boolean isTeamPrivate)
			throws CoreException {
		folder.setTeamPrivateMember(isTeamPrivate);
	}

	/**
	 * @param monitor
	 * @throws CoreException
	 * @see org.eclipse.core.resources.IResource#touch(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void touch(IProgressMonitor monitor) throws CoreException {
		folder.touch(monitor);
	}
}
