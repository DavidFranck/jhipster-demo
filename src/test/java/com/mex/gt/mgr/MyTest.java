package com.mex.gt.mgr;

import com.mex.gt.mgr.common.utils.OSSUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GtMgrApp.class)
public class MyTest {
    @Autowired
    private OSSUtils ossUtils;

    @Test
    public void testOss() {
        ossUtils.putObject("gt-test", new File("/Users/wangdavid/Downloads/jhipster-jdl.jh"));
    }
}
