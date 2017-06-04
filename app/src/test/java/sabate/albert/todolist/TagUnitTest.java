package sabate.albert.todolist;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;


import sabate.albert.todolist.Domain.Tag;
import sabate.albert.todolist.Exceptions.InvalidDateLimitThrowable;
import sabate.albert.todolist.Exceptions.NoDateOfCreationThrowable;
import sabate.albert.todolist.Exceptions.NullIdThrowable;
import sabate.albert.todolist.Exceptions.NoNameThrowable;
import sabate.albert.todolist.Exceptions.TagCreatorThrowable;

public class TagUnitTest {

    private Tag tag;
    private Calendar calendar;

    @Before
    public void setUp() throws TagCreatorThrowable {
        tag = new Tag("new",new Date(),null);
        calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,Calendar.MONTH,Calendar.DATE-1);
    }

    @Test (expected = NoNameThrowable.class)
    public void noNameTest() throws TagCreatorThrowable {
        new Tag("",new Date(),null);
    }

    @Test (expected = NoNameThrowable.class)
    public void nullNameTest() throws TagCreatorThrowable {
        new Tag(null,new Date(),null);
    }

    @Test (expected = NoDateOfCreationThrowable.class)
    public void noDateOfCreationTest() throws TagCreatorThrowable {
        new Tag("new",null,null);
    }

    @Test (expected = InvalidDateLimitThrowable.class)
    public void invalidDateLimitTest() throws TagCreatorThrowable {
        new Tag("new",new Date(),calendar.getTime());
    }

    @Test (expected = NoNameThrowable.class)
    public void noNameOnSetTest() throws NoNameThrowable {
        tag.setName("");
    }

    @Test (expected = NoNameThrowable.class)
    public void nullNameOnSetTest() throws NoNameThrowable {
        tag.setName(null);
    }

    @Test (expected = NoDateOfCreationThrowable.class)
    public void noDateOfCreationOnSetTest() throws NoDateOfCreationThrowable {
        tag.setDateOfCreation(null);
    }

    @Test (expected = InvalidDateLimitThrowable.class)
    public void invalidDateLimitOnSetTest() throws TagCreatorThrowable {
        tag.setDateLimit(calendar.getTime());
    }

    @Test (expected = NullIdThrowable.class)
    public void nullIdOnSetTest() throws NullIdThrowable {
        tag.setId(null);
    }
}