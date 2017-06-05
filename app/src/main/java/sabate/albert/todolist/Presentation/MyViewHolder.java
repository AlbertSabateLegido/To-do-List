package sabate.albert.todolist.Presentation;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;

import sabate.albert.todolist.Domain.Tag;
import sabate.albert.todolist.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private RadioButton rbTag;

    public MyViewHolder(View view) {
        super(view);
        cardView = (CardView) view.findViewById(R.id.card_view);
        rbTag = (RadioButton) view.findViewById(R.id.rbTag);
    }

    public RadioButton getRbTag() {
        return rbTag;
    }
}
