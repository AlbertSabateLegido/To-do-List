package sabate.albert.todolist.Presentation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;

import sabate.albert.todolist.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    private RadioButton rbTag;

    public MyViewHolder(View view) {
        super(view);
        rbTag = (RadioButton) view.findViewById(R.id.rbTag);
    }

    public RadioButton getRbTag() {
        return rbTag;
    }
}
