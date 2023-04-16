package org.example.taskfirst;
import com.thoughtworks.xstream.security.TypePermission;

public class ListSubwStreamPermission implements TypePermission {
    public boolean allows(Class type) {
        return type.getName().equals("org.example.taskfirst.ListSubwStream") ||
                type.getName().equals("org.example.taskfirst.HourWithDates");
    }

}

