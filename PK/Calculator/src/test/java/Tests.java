import Calc.Calculator;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.awt.*;

public class Tests {

    Calculator calc = new Calculator();

    Calculator calc2 = new Calculator();

    @Test
    public void testFontSetter() {
        calc2.setTextAreaFont(new Font("Calibri", Font.PLAIN, 12));
        assertEquals(calc2.getTextAreaFont(),new Font("Calibri", Font.PLAIN, 12));
    }
    @Test
    public void testTextSetter() {
        calc.setTextAreaText("Hello World!");
        assertEquals(calc.getTextAreaText(),"Hello World!");
    }
}
