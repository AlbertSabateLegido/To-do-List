package sabate.albert.todolist.Presentation;

import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import sabate.albert.todolist.Domain.DomainController;
import sabate.albert.todolist.Exceptions.TagCreatorThrowable;
import sabate.albert.todolist.R;



public class NewTagPopup implements View.OnClickListener {

    private DomainController domainController;
    private TagListAdapter adapter;

    public NewTagPopup(DomainController domainController, TagListAdapter adapter) {
        this.domainController = domainController;
        this.adapter = adapter;
    }

    @Override
    public void onClick(View view) {
        final Dialog newTagDialogue = new Dialog(view.getContext(), android.R.style.Theme_DeviceDefault_Light_Dialog);
        newTagDialogue.requestWindowFeature(Window.FEATURE_NO_TITLE);
        newTagDialogue.setContentView(R.layout.new_tag_dialogue);
        newTagDialogue.setCancelable(true);
        newTagDialogue.show();

        final EditText etName = (EditText) newTagDialogue.findViewById(R.id.etName);

        Button bAccept = (Button) newTagDialogue.findViewById(R.id.bAccept);
        bAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    adapter.addTag(domainController.createTag(etName.getText().toString(), Calendar.getInstance().getTime(),null));
                    newTagDialogue.cancel();
                } catch (TagCreatorThrowable tagCreatorThrowable) {
                    Toast.makeText(view.getContext(),"The name is empty",Toast.LENGTH_SHORT).show();
                    tagCreatorThrowable.printStackTrace();
                }
            }
        });

        Button bCancel = (Button) newTagDialogue.findViewById(R.id.bCancel);
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newTagDialogue.cancel();
            }
        });
    }

}
