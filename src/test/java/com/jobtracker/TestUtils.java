package com.jobtracker;

import com.fasterxml.jackson.core.io.JsonEOFException;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;

public class TestUtils {

    public <T> String fromResult(ResultActions result) throws Exception {
     return result.andReturn().getResponse().getContentAsString();

    }

    public static String getFileContent(String classPathLocation) throws IOException {
        return Files.readString(ResourceUtils.getFile("classpath:" + classPathLocation).toPath());
    }

    public static void assertThatJsonResponseEqualsToExpected(ResultActions apiCallResult,String expectedJsonContent)throws IOException, JSONException {
        String jsonResponseContent=apiCallResult.andReturn().getResponse().getContentAsString();

    //use JSONASsert with LENIENT mode to handle numeric type difference (int vs double)
        //and ignore whitespace/formatting difference
        JSONAssert.assertEquals(expectedJsonContent,jsonResponseContent, JSONCompareMode.LENIENT);
    }

}
