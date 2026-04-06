package com.jobtracker;

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

}
