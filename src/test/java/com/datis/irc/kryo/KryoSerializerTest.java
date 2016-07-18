/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datis.irc.kryo;

import com.datis.irc.entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import junit.framework.TestCase;

/**
 *
 * @author jeus
 */
public class KryoSerializerTest extends TestCase {

    public KryoSerializerTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of configure method, of class KryoSerializer.
     */
//    public void testConfigure() {
//        System.out.println("configure");
//        Map arg0 = null;
//        boolean arg1 = false;
//        KryoSerializer instance = new KryoSerializer();
//        instance.configure(arg0, arg1);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of serialize method, of class KryoSerializer.
     */
    public void testSerialize() {
        System.out.println("serialize<----------Test");
        Date dt = new Date();
        List<String> phone = new ArrayList<String>();
        phone.add("0913333212");
        phone.add("0913333888");
        phone.add("091222883");
        List<Integer> rates = new ArrayList<>();
        rates.add(1);
        rates.add(2);
        rates.add(3);
        User user = new User("jeus", "Geek", 29, dt.getTime(), phone, rates);
        KryoSerializer instance = new KryoSerializer();
        byte[] expResult = null;
        byte[] result = instance.serialize("serializeKryo", user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(true);
    }

    /**
     * Test of close method, of class KryoSerializer.
     */
//    public void testClose() {
//        System.out.println("close");
//        KryoSerializer instance = new KryoSerializer();
//        instance.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

}
