package de.tobiwild.fallbacks.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.tobiwild.fallbacks.Activator;
import de.tobiwild.fallbacks.navigator.FallbackContentProvider;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 */

public class FallbackPreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

	private FileFieldEditor fileField; 
	private BooleanFieldEditor folderFallbackField;
	
	public FallbackPreferencePage() {
		super(GRID);
		Activator activator = Activator.getDefault();
		setPreferenceStore(activator.getPreferenceStore());
		setDescription("Fallbacks must be defined in a YAML file."); //$NON-NLS-1$
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
		fileField = new FileFieldEditor(PreferenceConstants.P_FILE, 
				"&Fallback YAML File:", getFieldEditorParent()); //$NON-NLS-1$
		
		folderFallbackField = new BooleanFieldEditor(PreferenceConstants.P_FOLDER_FALLBACK, 
				                  "Enable F&older Fallbacks", getFieldEditorParent()); //$NON-NLS-1$
		
		addField(fileField);
		addField(folderFallbackField);
	}

	public boolean performOk() {
		fileField.store();
		FallbackContentProvider.updateFallbacks();
		return super.performOk();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
}