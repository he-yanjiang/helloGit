package com.sst;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */


    @Test
    public void test()
    {
        String specStr = "11,3,5";
        String[] spec = specStr.split(",");


            for (int i=0;i<spec.length;i++) {
            System.out.println(spec[i]);
        }
        //ApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        //IUserService userService = (IUserService) applicationContext.getBean("userServiceImpl");

    }


}
