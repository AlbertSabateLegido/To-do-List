package sabate.albert.todolist.Presentation;


import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import sabate.albert.todolist.Domain.DomainController;
import sabate.albert.todolist.R;

public class MainActivity extends AppCompatActivity {

    private DomainController domainController;
    private RecyclerView mRecyclerView;
    private TagRecyclerViewAdapter adapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        domainController = DomainController.getInstance();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new TagRecyclerViewAdapter(domainController.getTags());
        mRecyclerView.setAdapter(adapter);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.addTagButton);
        floatingActionButton.setOnClickListener(new NewTagPopup(domainController,adapter));
    }
}
