/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datis.irc.kryo;

import com.datis.irc.entity.User;
import com.datis.irc.pojo.JsonPOJOSerializer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author jeus
 */
public class KryoSerializerTest extends TestCase {

    private final String addressFolder = "/home/jeus/project/Datis/kryo_serde/test1/";
    final int SERDE_NUM = 1000;
    private StringBuilder benchLog = new StringBuilder("number of object Serializer:" + SERDE_NUM + "\n");
    byte[] ser = null;

    public KryoSerializerTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        try {
            System.out.println("DELETE and Create Folder");
            File f = new File(addressFolder);
            File fKryo = new File(addressFolder + "/kryo");
            File fpojo = new File(addressFolder + "/pojo");
            FileUtils.cleanDirectory(f); //clean out directory (this is optional -- but good know)
            FileUtils.forceDelete(f); //delete directory
            FileUtils.forceMkdir(f); //create directory
            FileUtils.forceMkdir(fKryo); //create directory
            FileUtils.forceMkdir(fpojo); //create directory
        } catch (IOException e) {
            System.out.println("ERROR in Create And Delete DirectoryS");
            e.printStackTrace();
        }
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
        System.out.println("BYTE CODE IS:" + byteCodeSer);

        Map<String, Object> serdeProps = new HashMap<>();
        serdeProps.put("Kryo", User.class);
        KryoDesrializer instanceD = new KryoDesrializer();
        instanceD.configure(serdeProps, true);
        User result = (User) instanceD.deserialize("topic1", byteCodeSer);
        assertTrue(except.equals(result));
        assertEquals(except, result);
    }

    public void testPerformance() {

        //pojo vs kryo test it 
        User user1 = getUserObj();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < SERDE_NUM; i++) {
            KryoSerializer ks = new KryoSerializer();
            ser = ks.serialize("topic1", user1);
            byteToFile(ser, "kryo/Byte" + i + ".bin");
        }
        long endTime = System.currentTimeMillis();
        benchmarkOutput("Kryo Serializer Test:", startTime, endTime);
        benchLog.append("EveryFile is:" + ser.length+" Byte\n\n");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            System.out.println("EXCEPTION " + ex.getMessage());
        }

        startTime = System.currentTimeMillis();
        for (int i = 0; i < SERDE_NUM; i++) {
            JsonPOJOSerializer jpojos1 = new JsonPOJOSerializer();
            ser = jpojos1.serialize("topic1", user1);
            byteToFile(ser, "pojo/Byte" + i + ".bin");
        }
        endTime = System.currentTimeMillis();

        benchmarkOutput("Pojo Serializer Test:", startTime, endTime);
        benchLog.append("EveryFile is:" + ser.length+" Byte\n\n");
        System.out.println(benchLog.toString());
    }

    private void byteToFile(byte[] data, String fileName) {

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(addressFolder + fileName);
            fos.write(data);
            fos.close();
        } catch (Exception ex) {
            Logger.getLogger(KryoSerializerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void benchmarkOutput(String type, long startTime, long endTime) {
        benchLog.append(type + "");
        benchLog.append((double) (endTime - startTime) / 1000);
        benchLog.append("\n");
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
