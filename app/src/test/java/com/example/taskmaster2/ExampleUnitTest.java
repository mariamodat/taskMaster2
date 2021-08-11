package com.example.taskmaster2;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.manipulation.Ordering;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
//@RunWith(RobolectricTestRunner::class)
public class ExampleUnitTest {


  private Context context= ApplicationProvider.getApplicationContext();
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);

    }

//    @Test
//  public void  test1(){
//MainActivity main = new MainActivity();
//String res = main.findViewById(R.id.textView).toString();
//      assertEquals("h" , res);
//
//  }


}
