package com.charles.address.model;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 * Helper class to save list of person. This is used for saving the list of person to XML
 */
public class PersonListWrapper {
    private List<Person> persons;

    @XmlElement
    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
