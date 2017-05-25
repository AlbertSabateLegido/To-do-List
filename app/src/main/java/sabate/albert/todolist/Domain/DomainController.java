package sabate.albert.todolist.Domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sabate.albert.todolist.Exceptions.TagCreatorThrowable;

public class DomainController {

    private List<Tag> tagList;

    public DomainController() {
        tagList = new ArrayList<>();
    }

    public String createTag(String name,Date dateOfCreation,Date dateLimit) throws TagCreatorThrowable {
        Tag tag = new Tag(name,dateOfCreation,dateLimit);
        tagList.add(tag);
        return tag.getName();
    }

    public List<String> getTagNames () {
        List<String> tagNamesList = new ArrayList<>();
        for (Tag tag:this.tagList) {
            tagNamesList.add(tag.getName());
        }
        return tagNamesList;
    }
}
