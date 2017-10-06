package sabate.albert.listit.Presentation;


import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;


import sabate.albert.listit.Domain.DomainController;
import sabate.albert.listit.R;

public class MainActivity extends AppCompatActivity {

    private DomainController domainController;
    private RecyclerView mRecyclerView;
    private ListObjectRecyclerViewAdapter adapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        domainController = DomainController.getInstance();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new ListObjectRecyclerViewAdapter(domainController.getListObjects());
        mRecyclerView.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new MyItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);

        FloatingActionButton floatingActionButton =
                (FloatingActionButton) findViewById(R.id.addListObjectButton);
        floatingActionButton.setOnClickListener(new NewListObjectListener(this));

    }
}
