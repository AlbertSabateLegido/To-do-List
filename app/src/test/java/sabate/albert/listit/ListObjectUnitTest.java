package sabate.albert.listit;

import org.junit.Before;
import org.junit.Test;

import sabate.albert.listit.Domain.ListObject;
import sabate.albert.listit.Exceptions.NullIdThrowable;
import sabate.albert.listit.Exceptions.NoNameThrowable;
import sabate.albert.listit.Exceptions.ListObjectCreatorThrowable;

public class ListObjectUnitTest {

    private ListObject listObject;

    @Before
    public void setUp() throws ListObjectCreatorThrowable {
        listObject = new ListObject("new");
    }

    @Test (expected = NoNameThrowable.class)
    public void noNameTest() throws ListObjectCreatorThrowable {
        new ListObject("");
    }

    @Test (expected = NoNameThrowable.class)
    public void nullNameTest() throws ListObjectCreatorThrowable {
        new ListObject(null);
    }

    @Test (expected = NoNameThrowable.class)
    public void noNameOnSetTest() throws NoNameThrowable {
        listObject.setName("");
    }

    @Test (expected = NoNameThrowable.class)
    public void nullNameOnSetTest() throws NoNameThrowable {
        listObject.setName(null);
    }

    @Test (expected = NullIdThrowable.class)
    public void nullIdOnSetTest() throws NullIdThrowable {
        listObject.setId(null);
    }
}