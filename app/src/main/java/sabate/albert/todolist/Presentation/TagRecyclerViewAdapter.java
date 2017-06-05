package sabate.albert.todolist.Presentation;

import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import java.util.Collections;
import java.util.List;

import sabate.albert.todolist.Domain.DomainController;
import sabate.albert.todolist.Domain.Tag;
import sabate.albert.todolist.Domain.ToDoList;
import sabate.albert.todolist.R;


public class TagRecyclerViewAdapter extends
        RecyclerView.Adapter<MyViewHolder> {

    private List<Tag> tagList;

    public TagRecyclerViewAdapter(List<Tag> tagList) {
        this.tagList = tagList;
    }

    private void strikeThroughRadioButton(MyViewHolder holder) {
        RadioButton rbTag = holder.getRbTag();
        rbTag.setPaintFlags(rbTag.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        rbTag.setTextColor(ContextCompat.getColor(ToDoList.getContext(), R.color.secundaryTextColor));
        rbTag.setChecked(true);
    }

    private void highlightRadioButton(MyViewHolder holder) {
        RadioButton rbTag = holder.getRbTag();
        rbTag.setPaintFlags(rbTag.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        rbTag.setTextColor(ContextCompat.getColor(ToDoList.getContext(), R.color.textColor));
        rbTag.setChecked(false);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tag_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Tag tag = tagList.get(position);

        if(tag.getDone())
            strikeThroughRadioButton(holder);

        RadioButton rbTag = holder.getRbTag();
        rbTag.setText(tag.getName());
        rbTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tag.getDone()) {
                    DomainController.getInstance().setTagDone(tag, false);
                    highlightRadioButton(holder);
                }
                else {
                    DomainController.getInstance().setTagDone(tag, true);
                    strikeThroughRadioButton(holder);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tagList.size();
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(tagList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(tagList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    public void onItemDismiss(int position) {
        DomainController.getInstance().deleteTag(tagList.get(position));
        notifyItemRemoved(position);
    }
}


