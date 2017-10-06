package sabate.albert.listit.Presentation;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import sabate.albert.listit.Domain.DomainController;
import sabate.albert.listit.Exceptions.ListObjectCreatorThrowable;
import sabate.albert.listit.R;

public class NewListObjectListener extends Activity implements View.OnClickListener {

    private final Activity activity;

    public NewListObjectListener(Activity activity) {
        this.activity = activity;
    }

    private void hideKeyboard(Activity activity) {
        if (activity.getCurrentFocus()!= null) {
            InputMethodManager inputMethodManager = (InputMethodManager)
                    activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public void onClick(View view) {
        View parent = (View)view.getParent();
        EditText listObjectName = (EditText) parent.findViewById(R.id.addListObjectInput);

        try {
            DomainController.getInstance().createListObject(listObjectName.getText().toString());
            listObjectName.setText("");
            hideKeyboard(activity);
        } catch (ListObjectCreatorThrowable listObjectCreatorThrowable) {
            hideKeyboard(activity);
            Toast.makeText(view.getContext(), R.string.empty_name, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
