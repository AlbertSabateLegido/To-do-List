package sabate.albert.todolist.Domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sabate.albert.todolist.Exceptions.TagCreatorThrowable;

/* Singleton */
public class DomainController {

    private static DomainController domainController;
    private List<Tag> tagList;

    private DomainController() {
        tagList = new ArrayList<>();
    }

    public static DomainController getInstance() {
        if(domainController == null)
            domainController = new DomainController();
        return domainController;
    }

    public Tag createTag(String name,Date dateOfCreation,Date dateLimit) throws TagCreatorThrowable {
        Tag tag = new Tag(name,dateOfCreation,dateLimit);
        tagList.add(tag);
        return tag;
    }

    public void deleteTag(Tag tag) {
        tagList.remove(tag);
    }

    public List<Tag> getTags () {
        return tagList;
    }
}
