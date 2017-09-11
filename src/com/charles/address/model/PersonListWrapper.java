package com.charles.address.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 * Helper class to save list of person. This is used for saving the list of person to XML
 */
@XmlRootElement
// 定义根元素名称
public class PersonListWrapper {
    private List<Person> persons;

    @XmlElement
    //一个可选的名称，用来指定元素
    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
