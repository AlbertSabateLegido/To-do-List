package sabate.albert.listit.Presentation;

import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import java.util.Collections;
import java.util.List;

import sabate.albert.listit.Domain.DomainController;
import sabate.albert.listit.Domain.ListObject;
import sabate.albert.listit.Domain.ListIt;
import sabate.albert.listit.R;


public class ListObjectRecyclerViewAdapter extends
        RecyclerView.Adapter<MyViewHolder> {

    private List<ListObject> listObjectList;

    public ListObjectRecyclerViewAdapter(List<ListObject> listObjectList) {
        this.listObjectList = listObjectList;
    }

    private void strikeThroughRadioButton(MyViewHolder holder) {
        RadioButton rbTag = holder.getRbListObject();
        rbTag.setPaintFlags(rbTag.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        rbTag.setTextColor(ContextCompat.getColor(ListIt.getContext(), R.color.secundaryTextColor));
        rbTag.setChecked(true);
    }

    private void highlightRadioButton(MyViewHolder holder) {
        RadioButton rbTag = holder.getRbListObject();
        rbTag.setPaintFlags(rbTag.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        rbTag.setTextColor(ContextCompat.getColor(ListIt.getContext(), R.color.textColor));
        rbTag.setChecked(false);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_object_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ListObject listObject = listObjectList.get(position);

        if(listObject.getDone())
            strikeThroughRadioButton(holder);

        RadioButton rbTag = holder.getRbListObject();
        rbTag.setText(listObject.getName());
        rbTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listObject.getDone()) {
                    DomainController.getInstance().setListObjectDone(listObject, false);
                    highlightRadioButton(holder);
                }
                else {
                    DomainController.getInstance().setListObjectDone(listObject, true);
                    strikeThroughRadioButton(holder);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listObjectList.size();
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(listObjectList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(listObjectList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    public void onItemDismiss(int position) {
        DomainController.getInstance().deleteListObject(listObjectList.get(position));
        notifyItemRemoved(position);
    }
}


