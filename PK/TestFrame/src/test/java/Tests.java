import Calc.Calculator;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.awt.*;

public class Tests {

    Calculator calc = new Calculator();

    Calculator calc2 = new Calculator();

    @Test
    public void testFontSetter() {
        calc.setTextAreaFont(new Font("Calibri", Font.PLAIN, 12));
        assertEquals(calc.getTextAreaFont(),new Font("Calibri", Font.PLAIN, 12));
    }
    @Test
    public void testTextSetter() {
        calc2.setTextAreaText("Hello World!");
        assertEquals(calc2.getTextAreaText(),"Hello World!");
    }
}
