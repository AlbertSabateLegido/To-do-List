package sabate.albert.todolist.Domain;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import sabate.albert.todolist.Exceptions.InvalidDateLimitException;
import sabate.albert.todolist.Exceptions.NoNameException;
import sabate.albert.todolist.Exceptions.TagCreatorException;
import sabate.albert.todolist.Exceptions.NoDateOfCreationException;

/**
 * Created by Albert on 24/05/2017.
 */

public class Tag {

    /* primary key, not null and automatically generated */
    private String id;
    /* not null */
    private String name;
    /* not null */
    private Date dateOfCreation;
    /* --- */
    private Date dateLimit;

    public Tag(String name, Date dateOfCreation, Date dateLimit) throws TagCreatorException {
        if(name == null || name.isEmpty()) throw new NoNameException();
        if(dateOfCreation == null) throw new NoDateOfCreationException();
        if(dateLimit != null && dateLimit.compareTo(Calendar.getInstance().getTime()) < 0) throw new InvalidDateLimitException();
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.dateOfCreation = dateOfCreation;
        this.dateLimit = dateLimit;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws NoNameException {
        if(name == null || name.isEmpty()) throw new NoNameException();
        this.name = name;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) throws NoDateOfCreationException {
        if(dateOfCreation == null) throw new NoDateOfCreationException();
        this.dateOfCreation = dateOfCreation;
    }

    public Date getDateLimit() {
        return dateLimit;
    }

    public void setDateLimit(Date dateLimit) throws InvalidDateLimitException {
        if(dateLimit != null && dateLimit.compareTo(Calendar.getInstance().getTime()) < 0) throw new InvalidDateLimitException();
        this.dateLimit = dateLimit;
    }

}
