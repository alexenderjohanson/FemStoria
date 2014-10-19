package com.gsy.femstoria.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.gsy.femstoria.model.Post;
import com.gsy.femstoria.views.StoryView;

public class PostsAdapter extends BaseAdapter {
	
	private Context context;
	private int lastPosition = -1;
	private List<Post> posts;
	
	public PostsAdapter(Context context){
		this.context = context;
		posts = new ArrayList<Post>();
	}

	@Override
	public int getCount() {
		return posts.size();
	}

	@Override
	public Object getItem(int position) {
		return posts.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View cell = new StoryView(posts.get(position), context);
		
//		if(position > lastPosition){
//		    Animation animation = AnimationUtils.loadAnimation(context, R.anim.up_from_bottom);
//		    cell.startAnimation(animation);
//		}
//	    lastPosition = position;
		
		return cell;
	}

	public void AddPosts(List<Post> newPosts){
		posts.addAll(newPosts);
	}
}
