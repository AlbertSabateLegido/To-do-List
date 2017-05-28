package sabate.albert.todolist.Domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sabate.albert.todolist.Exceptions.TagCreatorThrowable;
import sabate.albert.todolist.Persistence.DatabaseController;

/* Singleton */
public class DomainController {

    private static DomainController domainController;
    private DatabaseController databaseController;
    private List<Tag> tagList;

    private DomainController() {
        databaseController = new DatabaseController();
        try {
            tagList = databaseController.getTags();
        } catch (TagCreatorThrowable tagCreatorThrowable) {
            tagList = new ArrayList<>();
            tagCreatorThrowable.printStackTrace();
        }
    }

    public static DomainController getInstance() {
        if(domainController == null)
            domainController = new DomainController();
        return domainController;
    }

    public Tag createTag(String name,Date dateOfCreation,Date dateLimit) throws TagCreatorThrowable {
        Tag tag = new Tag(name,dateOfCreation,dateLimit);
        tag.setId(databaseController.createTag(tag));
        tagList.add(tag);
        return tag;
    }

    public void deleteTag(Tag tag) {
        databaseController.deleteTag(tag.getId());
        tagList.remove(tag);
    }

    public List<Tag> getTags () {
        return tagList;
    }
}
