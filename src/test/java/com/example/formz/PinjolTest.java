package com.example.formz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PinjolTest {
    @Test
    void checkForm_InvalidBecauseOfNameOrAgeInvalid(){
        Pinjol pinjol = new Pinjol("", 5, 300000);
        VerificatorPinjol vp = new VerificatorPinjol();

        Assertions.assertThrows(FormInvalidException.class, () -> vp.getFormInfo(pinjol));
    }

    @Test
    void checkForm_Valid() throws Exception{
        String expectedResult = "name: Asep Hermanosky | age: 19 | income: 300000 | tax: 12500 | ";
        Pinjol pinjol = new Pinjol("Asep Hermanosky", 19, 300000);
        VerificatorPinjol vp = new VerificatorPinjol();

        Assertions.assertEquals(expectedResult, vp.getFormInfo(pinjol));
    }

}
