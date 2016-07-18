/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datis.irc.kryo;

import com.datis.irc.entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

    public void testSerializeDeserialize() {
        System.out.println("serialize<----------Test");
        KryoSerializer instance = new KryoSerializer();

        User except = getUserObj();
        byte[] byteCodeSer = instance.serialize("UserSerialize", except);
        System.out.println("BYTE CODE IS:"+byteCodeSer);
        
        
        Map<String,Object> serdeProps = new HashMap<>();
        serdeProps.put("Kryo", User.class);
        KryoDesrializer instanceD = new KryoDesrializer();
        instanceD.configure(serdeProps, true);
        User result = (User) instanceD.deserialize("topic1", byteCodeSer);
        assertTrue(except.equals(result));
        assertEquals(except, result);
    }

    private User getUserObj() {
        Date dt = new Date();
        List<String> phone = new ArrayList<String>();
        phone.add("0913333212");
        phone.add("0913333888");
        phone.add("091222883");
        phone.add("091222883");
        phone.add("091222883");
        phone.add("091222883");
        List<Integer> rates = new ArrayList<>();
        rates.add(1);
        rates.add(2);
        rates.add(3);
        User except = new User("jeus", "Geek", 29, dt.getTime(), phone, rates);
        return except;
    }
   
}
