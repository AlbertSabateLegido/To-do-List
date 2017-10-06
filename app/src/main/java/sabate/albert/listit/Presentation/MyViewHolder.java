package sabate.albert.listit.Presentation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;

import sabate.albert.listit.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    private RadioButton rbListObject;

    public MyViewHolder(View view) {
        super(view);
        rbListObject = (RadioButton) view.findViewById(R.id.rbTag);
    }

    public RadioButton getRbListObject() {
        return rbListObject;
    }
}
