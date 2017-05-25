package sabate.albert.todolist.Presentation;


import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Date;

import sabate.albert.todolist.Domain.DomainController;
import sabate.albert.todolist.Exceptions.TagCreatorThrowable;
import sabate.albert.todolist.R;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private DomainController domainController;
    private TagListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        domainController = DomainController.getInstance();

        mListView = (ListView) findViewById(R.id.list);
        adapter = new TagListAdapter(this,domainController.getTags());
        mListView.setAdapter(adapter);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.addTagButton);
        floatingActionButton.setOnClickListener(new NewTagPopup(domainController,adapter));
    }
}
