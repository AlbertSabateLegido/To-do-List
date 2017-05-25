package sabate.albert.todolist;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;


import sabate.albert.todolist.Domain.Tag;
import sabate.albert.todolist.Exceptions.InvalidDateLimitException;
import sabate.albert.todolist.Exceptions.NoDateOfCreationException;
import sabate.albert.todolist.Exceptions.NoNameException;
import sabate.albert.todolist.Exceptions.TagCreatorException;

public class TagUnitTest {

    private Tag tag;
    private Calendar calendar;

    @Before
    public void setUp() throws TagCreatorException {
        tag = new Tag("new",new Date(),null);
        calendar = Calendar.getInstance();
        calendar.set(calendar.YEAR,calendar.MONTH,calendar.DATE-1);
    }

    @Test (expected = NoNameException.class)
    public void noNameTest() throws TagCreatorException {
        new Tag("",new Date(),null);
    }

    @Test (expected = NoNameException.class)
    public void nullNameTest() throws TagCreatorException {
        new Tag(null,new Date(),null);
    }

    @Test (expected = NoDateOfCreationException.class)
    public void noDateOfCreationTest() throws TagCreatorException {
        new Tag("new",null,null);
    }

    @Test (expected = InvalidDateLimitException.class)
    public void invalidDateLimitTest() throws TagCreatorException {
        new Tag("new",new Date(),calendar.getTime());
    }

    @Test (expected = NoNameException.class)
    public void noNameOnSetTest() throws NoNameException {
        tag.setName("");
    }

    @Test (expected = NoNameException.class)
    public void nullNameOnSetTest() throws NoNameException {
        tag.setName(null);
    }

    @Test (expected = NoDateOfCreationException.class)
    public void noDateOfCreationOnSetTest() throws NoDateOfCreationException {
        tag.setDateOfCreation(null);
    }

    @Test (expected = InvalidDateLimitException.class)
    public void invalidDateLimitOnSetTest() throws TagCreatorException {
        tag.setDateLimit(calendar.getTime());
    }
}