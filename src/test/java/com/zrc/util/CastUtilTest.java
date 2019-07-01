package com.zrc.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class CastUtilTest {

    @Test
    public void castString() {
        int ret = CastUtil.castString(int.class, "sdadsa");
        System.out.println(ret);
        int i = 3;
        Integer j = 4;
        i = j;
        System.out.println(i);
    }
}