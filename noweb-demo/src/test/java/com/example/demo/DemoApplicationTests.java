package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testSerial(){
        Serial serial1 = new Serial(1,"song");
        System.out.println("Object Serial "+serial1);
        try {
            FileOutputStream fos = new FileOutputStream("serialTest.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(serial1);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {    // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {    // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    void testDeserial(){
        Serial serial2 ;
        try {
            FileInputStream fis = new FileInputStream("serialTest.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            serial2 = (Serial)ois.readObject();
            ois.close();
            System.out.println("Object Deserial "+serial2);
        } catch (FileNotFoundException e) {    // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {    // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {    // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
