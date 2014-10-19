package com.gsy.femstoria.callbacks;

import com.gsy.femstoria.model.Post;

/**
 * A callback interface that all activities containing this fragment must
 * implement. This mechanism allows activities to be notified of item
 * selections.
 */
public interface ListViewCallbacks {
	/**
	 * Callback for when an item has been selected.
	 */
	public void onItemSelected(Post post);
}