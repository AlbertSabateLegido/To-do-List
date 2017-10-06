package sabate.albert.listit.Domain;

import java.util.ArrayList;
import java.util.List;

import sabate.albert.listit.Exceptions.ListObjectCreatorThrowable;
import sabate.albert.listit.Persistence.DatabaseController;

/* Singleton */
public class DomainController {

    private static DomainController domainController;
    private DatabaseController databaseController;
    private List<ListObject> listObjectList;

    private DomainController() {
        databaseController = new DatabaseController();
        try {
            listObjectList = databaseController.getListObjects();
        } catch (ListObjectCreatorThrowable listObjectCreatorThrowable) {
            listObjectList = new ArrayList<>();
            listObjectCreatorThrowable.printStackTrace();
        }
    }

    public static DomainController getInstance() {
        if(domainController == null)
            domainController = new DomainController();
        return domainController;
    }

    public ListObject createListObject(String name) throws ListObjectCreatorThrowable {
        ListObject listObject = new ListObject(name);
        listObject.setId(databaseController.createListObject(listObject));
        listObjectList.add(listObject);
        return listObject;
    }

    public void deleteListObject(ListObject listObject) {
        listObjectList.remove(listObject);
        databaseController.deleteListObject(listObject.getId());
    }

    public void setListObjectDone(ListObject listObject, boolean done) {
        listObject.setDone(done);
        databaseController.setListObjectDone(listObject.getId(), listObject.getDone());
    }

    public List<ListObject> getListObjects() {
        return listObjectList;
    }


}
