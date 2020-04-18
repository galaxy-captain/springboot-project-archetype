package me.galaxy;

import generate.HelloSerivce;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.*;

import java.io.IOException;

/**
 * @Description
 * @Author duanxiaolei@bytedance.com
 * @Date 2020/4/17 7:05 下午
 **/
public class Client {

    public static void main(String[] args) throws TException, IOException {
        new Client().run();
    }

    public void run() throws TException, IOException {

        TTransport transport = new TFramedTransport(new TSocket("127.0.0.1", 9000));

        TProtocol protocol = new TBinaryProtocol(transport);

        HelloSerivce.Client helloService = new HelloSerivce.Client(protocol);

        String result = helloService.sayHello("Bob");

        System.out.println(result);

    }

}