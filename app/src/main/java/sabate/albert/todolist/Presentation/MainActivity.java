package sabate.albert.todolist.Presentation;


import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import sabate.albert.todolist.Domain.DomainController;
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

        mListView = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, domainController.getTagNames());
        mListView.setAdapter(adapter);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.addTagButton);
        floatingActionButton.setOnClickListener(new NewTagPopup(domainController,adapter));
    }
}
