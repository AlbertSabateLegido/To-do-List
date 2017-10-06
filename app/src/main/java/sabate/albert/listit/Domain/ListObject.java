package sabate.albert.listit.Domain;

import sabate.albert.listit.Exceptions.NullIdThrowable;
import sabate.albert.listit.Exceptions.NoNameThrowable;
import sabate.albert.listit.Exceptions.ListObjectCreatorThrowable;

public class ListObject {

    /* primary key, not null and automatically generated by the database */
    private Long id;
    /* not null */
    private String name;
    /* not null */
    private Boolean done;

    public ListObject() {}

    /* This constructor doesn't initialize the id, it must be set in the controller */
    public ListObject(String name) throws ListObjectCreatorThrowable {
        if(name == null || name.isEmpty())
            throw new NoNameThrowable();
        this.name = name;
        this.done = false;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) throws NullIdThrowable {
        if(id == null)
            throw new NullIdThrowable();
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws NoNameThrowable {
        if(name == null || name.isEmpty())
            throw new NoNameThrowable();
        this.name = name;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

}
