package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class MyAsyncTaskTest {
    private String result = null;
    private String TAG = "TEST_TAG";

    @Test
    public void LoadTest() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
        AsyncResponseHandler handler = new AsyncResponseHandler() {

            @Override
            public void goodResult(String output) {
                result = output;
                countDownLatch.countDown();
                assertNotNull(output);
                Log.d(TAG, output);
            }

            @Override
            public void badResult(Exception exception) {
                countDownLatch.countDown();
                Log.d(TAG, String.valueOf(exception));
            }
        };
        Context context = getInstrumentation().getContext();
        endpointsAsyncTask.setResponseHandler(handler);
        endpointsAsyncTask.execute(new Pair<Context, String>(context, "Test text"));

        try {
            countDownLatch.await();
            assertNotNull("Problem with receiving joke", result);
            assertFalse("No result in joke", TextUtils.isEmpty(result));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
