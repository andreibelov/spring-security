package edu.teldir.web.bean;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 14.04.12
 */
public class KeyValueBean {
    private final String key;
    private final String value;

    public KeyValueBean(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
