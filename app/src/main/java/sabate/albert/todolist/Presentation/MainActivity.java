package sabate.albert.todolist.Presentation;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Calendar;

import sabate.albert.todolist.Domain.DomainController;
import sabate.albert.todolist.Exceptions.TagCreatorException;
import sabate.albert.todolist.R;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private DomainController domainController;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        domainController = new DomainController();

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.addTagButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog newTagDialogue = new Dialog(view.getContext(), android.R.style.Theme_DeviceDefault_Light_Dialog);
                newTagDialogue.setContentView(R.layout.new_tag_dialogue);
                newTagDialogue.setCancelable(true);
                newTagDialogue.show();

                final EditText etName = (EditText) newTagDialogue.findViewById(R.id.etName);

                Button bAccept = (Button) newTagDialogue.findViewById(R.id.bAccept);
                bAccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            adapter.add(domainController.createTag(etName.getText().toString(), Calendar.getInstance().getTime(),null));
                            newTagDialogue.cancel();
                        } catch (TagCreatorException e) {
                            System.out.println("NoNameException");
                            e.printStackTrace();
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
        });

        mListView = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, domainController.getTagNames());
        mListView.setAdapter(adapter);
    }
}
