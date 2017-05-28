package sabate.albert.todolist.Domain;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import sabate.albert.todolist.Exceptions.InvalidDateLimitThrowable;
import sabate.albert.todolist.Exceptions.NullIdThrowable;
import sabate.albert.todolist.Exceptions.NoNameThrowable;
import sabate.albert.todolist.Exceptions.TagCreatorThrowable;
import sabate.albert.todolist.Exceptions.NoDateOfCreationThrowable;

public class Tag {

    /* primary key, not null and automatically generated */
    private String id;
    /* not null */
    private String name;
    /* not null */
    private Date dateOfCreation;
    /* --- */
    private Date dateLimit;

    public Tag() {}

    public Tag(String name, Date dateOfCreation, Date dateLimit) throws TagCreatorThrowable {
        if(name == null || name.isEmpty())
            throw new NoNameThrowable();
        if(dateOfCreation == null)
            throw new NoDateOfCreationThrowable();
        if(dateLimit != null && dateLimit.compareTo(Calendar.getInstance().getTime()) < 0)
            throw new InvalidDateLimitThrowable();
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.dateOfCreation = dateOfCreation;
        this.dateLimit = dateLimit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws NullIdThrowable {
        if(id == null || id.isEmpty())
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

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) throws NoDateOfCreationThrowable {
        if(dateOfCreation == null)
            throw new NoDateOfCreationThrowable();
        this.dateOfCreation = dateOfCreation;
    }

    public Date getDateLimit() {
        return dateLimit;
    }

    public void setDateLimit(Date dateLimit) throws InvalidDateLimitThrowable {
        if(dateLimit != null && dateLimit.compareTo(Calendar.getInstance().getTime()) < 0)
            throw new InvalidDateLimitThrowable();
        this.dateLimit = dateLimit;
    }

}
