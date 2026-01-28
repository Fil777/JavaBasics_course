package JavaBasics_06_04_04;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * The class enables list of messages to/from person
 */
public class MailBox<V>
        extends LinkedHashMap<String, List<V>> {

    public MailBox() {

        super(new HashMap<String, List<V>>());

    }

    @Override
    public List<V> get(Object key) {

        ParameterizedType parameterizedType = (ParameterizedType) this.values().getClass().getGenericSuperclass();
        List list = setParametersList(parameterizedType);
        list = super.getOrDefault(key, list);
        return list;

    }

    private List setParametersList(ParameterizedType parameterizedType) {

        return ((Class) parameterizedType.getActualTypeArguments()[0]).toString().equals("String")
                ? new ArrayList<String>() : new ArrayList<Integer>();

    }

}
