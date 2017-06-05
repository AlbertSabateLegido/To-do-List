package sabate.albert.todolist.Presentation;

import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
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
        RecyclerView.Adapter<TagRecyclerViewAdapter.MyViewHolder> {

    private List<Tag> tagList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public RadioButton rbTag;
        public Tag tag;

        public MyViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_view);
            rbTag = (RadioButton) view.findViewById(R.id.rbTag);
        }
    }

    public TagRecyclerViewAdapter(List<Tag> tagList) {
        this.tagList = tagList;
    }

    private void strikeThroughRadioButton(MyViewHolder holder) {
        holder.rbTag.setPaintFlags(holder.rbTag.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.rbTag.setTextColor(ContextCompat.getColor(ToDoList.getContext(), R.color.secundaryTextColor));
        holder.rbTag.setChecked(true);
    }

    private void highlightRadioButton(MyViewHolder holder) {
        holder.rbTag.setPaintFlags(holder.rbTag.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        holder.rbTag.setTextColor(ContextCompat.getColor(ToDoList.getContext(), R.color.textColor));
        holder.rbTag.setChecked(false);
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

        holder.tag = tagList.get(position);

        if(tag.getDone()) strikeThroughRadioButton(holder);

        holder.rbTag.setText(tag.getName());
        holder.rbTag.setOnClickListener(new View.OnClickListener() {
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


