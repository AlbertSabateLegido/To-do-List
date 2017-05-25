package sabate.albert.todolist.Domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sabate.albert.todolist.Exceptions.TagCreatorException;

/**
 * Created by Albert on 25/05/2017.
 */

public class DomainController {

    private List<Tag> tagList;

    public DomainController() {
        tagList = new ArrayList<>();
    }

    public String createTag(String name,Date dateOfCreation,Date dateLimit) throws TagCreatorException {
        Tag tag = new Tag(name,dateOfCreation,dateLimit);
        tagList.add(tag);
        return tag.getName();
    }

    public List<String> getTagNames () {
        List<String> tagList = new ArrayList<>();
        for (Tag tag:this.tagList) {
            tagList.add(tag.getName());
        }
        return tagList;
    }
}
