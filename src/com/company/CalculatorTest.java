package com.company;


import static org.junit.Assert.*;

public class CalculatorTest {
    private Calculator calc=new Calculator();

    @org.junit.Test
    public void SimpleCalculate() throws IllegalArgumentException {
        assertEquals(calc.calculate("3+2"),(double)5,0.011);
        assertEquals(calc.calculate("1.1212+3.654"),4.7752,0.011);
        assertEquals(calc.calculate("1-2"),(double)-1,0.011);
        assertEquals(calc.calculate("7.999-5.888"),2.111,0.011);
        assertEquals(calc.calculate("3*8"),(double)24,0.011);
        assertEquals(calc.calculate("3.568*4.568"),16.298,0.011);
        assertEquals(calc.calculate("1/2"),0.5,0.011);
        assertEquals(calc.calculate("4.568/5.958"),0.766,0.011);
    }

    @org.junit.Test
    public void SimpleBracketsCalculate() throws IllegalArgumentException {
        assertEquals(calc.calculate("(1+2)"),(double)3,0.11);
        assertEquals(calc.calculate("((1+2))"),(double)3,0.11);
        assertEquals(calc.calculate("((1+2)*4)"),(double)12,0.11);
        assertEquals(calc.calculate("(4*(1+2))"),(double)12,0.11);
        assertEquals(calc.calculate("4*((1+2))"),(double)12,0.11);
    }

    @org.junit.Test
    public void LettersFailure(){
        try {
            calc.calculate("1+5dfgfdg-(10+89*95)/25");
        } catch (IllegalArgumentException e) {
            assertSame("1","1");
            assertSame(e.getMessage(),"your expression contains letters");
        }
        try {
            calc.calculate("(1+5-asdsd(10+89*95)/25)");
        } catch (IllegalArgumentException e) {
            assertEquals("1+5dfgfdg-(10+89*95)/25",e.getMessage(),"your expression contains letters");
        }
        try {
            calc.calculate("(1+5-(dssg10+89*95)/25)");
        } catch (IllegalArgumentException e) {
            assertEquals("(1+5-(dssg10+89*95)/25)",e.getMessage(),"your expression contains letters");
        }
        try {
            calc.calculate("1+5-(1fsdfs0+89*95)/25");
        } catch (IllegalArgumentException e) {
            assertEquals("1+5-(1fsdfs0+89*95)/25",e.getMessage(),"your expression contains letters");
        }
        try {
            calc.calculate("1+5-(10+89*95)fsdfsd/25");
        } catch (IllegalArgumentException e) {
            assertEquals("1+5-(10+89*95)fsdfsd/25",e.getMessage(),"your expression contains letters");
        }
        try {
            calc.calculate("1+5-(10+89*95)/sdasd25");
        } catch (IllegalArgumentException e) {
            assertEquals("1+5-(10+89*95)fsdfsd/25",e.getMessage(),"your expression contains letters");
        }
        try {
            calc.calculate("1+5-(10+89*95)/25asdasd");
        } catch (IllegalArgumentException e) {
            assertEquals("1+5-(10+89*95)/25asdasd",e.getMessage(),"your expression contains letters");
        }
        try {
            calc.calculate("asdasdasd1+5-(10+89*95)/25");
        } catch (IllegalArgumentException e) {
            assertEquals("asdasdasd1+5-(10+89*95)/25",e.getMessage(),"your expression contains letters");
        }
    }

    @org.junit.Test
    public void DevideOn0(){
        try {
            calc.calculate("2/0");
        } catch (IllegalArgumentException e) {
            assertEquals("2/0",e.getMessage(),"Deviding on 0 is black magic");
        }
    }

    @org.junit.Test
    public void DoubleWithComma(){
        assertEquals(calc.calculate("1,25"),1.25,0.11);
        assertEquals(calc.calculate("3,05"),3.05,0.11);
        assertEquals(calc.calculate("4,595"),4.595,0.11);
    }

    @org.junit.Test
    public void CalculateManyActions() {
        assertEquals(calc.calculate("10.12315 + 456489 - (145 / 56987.1564 * 78549.1234) * ((((45.268 * 789 + 65.241))))"),-6694936.66499,0.011);
    }

}