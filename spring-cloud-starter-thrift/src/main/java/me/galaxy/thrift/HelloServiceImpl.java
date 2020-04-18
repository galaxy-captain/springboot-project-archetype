package me.galaxy.thrift;

import generate.HelloSerivce;
import org.apache.thrift.TException;

/**
 * @Description
 * @Author duanxiaolei@bytedance.com
 * @Date 2020/4/17 7:03 下午
 **/
public class HelloServiceImpl implements HelloSerivce.Iface {

    public String sayHello(String name) throws TException {
        return "hello " + name + "!";
    }

}