package cn.h3c.user.dao;

import cn.h3c.user.domain.User;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;

public class UserDao {
    final String path = "/user.xml";

    /**
     *   根据之前的分析可知：Dao层需要2个接口来实现整个项目的需求：1，根据用户名查找User对象
     *   2，添加用户信息
     * @param username
     * @return
     */
    public User findByUsername(String username){
        /*
        1,使用Dom4j方法，创建解析器：SAXReader = new
        2,使用XPATH查询用户名：//user[@username=' "+username+"']"
        3,将查找到的Element封装成User对象并返回
         */
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(path);
        }catch (Exception e){
            throw new RuntimeException(e);//为了使得上层调用者不必关心该函数，将异常转化为运行时异常
        }
        Element ele = (Element)document.selectSingleNode("//user[@username='"+username+"']");
        //查找到ele时必须要对其判断空
        if(ele==null)
            return null;
        String uname = ele.attributeValue("username");
        String password = ele.attributeValue("password");
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
         return user;
    }

    public void add(User user){
        /*
        1,获得Document
        2，获得根节点
        3，在根节点添加一个user元素
        4，设置元素的属性：username、password
        5，回写文件
        */
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(path);
            Element root = document.getRootElement();
//            Element ele = root.element("user"); //这样会导致直接在原来的地址上更改
            Element ele = root.addElement("user");
            ele.addAttribute("username",user.getUsername());
            ele.addAttribute("password",user.getPassword());

            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(path),format);
            xmlWriter.write(document);
            xmlWriter.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
