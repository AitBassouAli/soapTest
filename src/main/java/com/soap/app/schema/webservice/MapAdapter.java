package com.soap.app.schema.webservice;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class MapAdapter extends XmlAdapter<TestApp.Params, Map<String, Object>>{
    @Override
    public Map<String, Object> unmarshal(TestApp.Params v) {
        Map<String, Object> map = new HashMap<>();

        if(v != null && v.item != null){
            for(TestApp.Params.Item e : v.item){
                if(e.getValue() instanceof XMLGregorianCalendar)
                    map.put(e.getKey(), ((XMLGregorianCalendar)e.getValue()).toGregorianCalendar().getTime());
                else
                    map.put(e.getKey(), e.getValue());
            }
        }
        return map;
    }

    @Override
    public TestApp.Params marshal(Map<String, Object> v) {
        TestApp.Params b = new TestApp.Params();
        if(v == null)
            return null;
        for(java.util.Map.Entry<String, Object> e : v.entrySet()){
            TestApp.Params.Item newItem = new TestApp.Params.Item();
            newItem.setKey(e.getKey());
            newItem.setValue( e.getValue());
            b.getItem().add(newItem);
        }
        return b;
    }
}