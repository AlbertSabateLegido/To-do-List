package sabate.albert.todolist.Presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.RadioButton;

import java.util.List;

import sabate.albert.todolist.Domain.DomainController;
import sabate.albert.todolist.Domain.Tag;
import sabate.albert.todolist.R;

public class TagListAdapter extends BaseAdapter implements ListAdapter {

    private List<Tag> tagList;
    private Context context;
    private LayoutInflater layoutInflater;

    public TagListAdapter(Context context, List<Tag> tagList) {
        this.tagList = tagList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void addTag (Tag tag) {
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return tagList.size();
    }

    @Override
    public Object getItem(int i) {
        return tagList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup) {

        view = layoutInflater.inflate(R.layout.tag_row, null);

        final RadioButton rbTag = (RadioButton) view.findViewById(R.id.rbTag);
        rbTag.setText(tagList.get(i).getName());
        rbTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DomainController.getInstance().deleteTag(tagList.get(i));
                notifyDataSetChanged();
            }
        });
        return view;
    }
}


