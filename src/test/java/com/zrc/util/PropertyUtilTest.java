package com.zrc.util;

import static org.junit.Assert.*;

import com.zrc.configuration.ConfigureConstant;
import org.junit.Test;

import java.util.Properties;

public class PropertyUtilTest {
    @Test
    public void loadProps() {
        Properties props = PropertyUtil.loadProps(ConfigureConstant.LITE_PROPERTIES);
        assertNotNull(props);
        System.out.println(props);
    }

    @Test
    public void getValue() {
        Properties props = PropertyUtil.loadProps(ConfigureConstant.LITE_PROPERTIES);
        System.out.println(PropertyUtil.getValue(props, "lite.framework.base_package"));
    }

}