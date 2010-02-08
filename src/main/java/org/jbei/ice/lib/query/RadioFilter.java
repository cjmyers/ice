package org.jbei.ice.lib.query;

import java.util.Map;

public class RadioFilter extends Filter {
    private static final long serialVersionUID = 1L;

    private Map<String, String> choices;

    public RadioFilter(String key, String name, String method, Map<String, String> choices) {
        super(key, name, method);

        this.choices = choices;
    }

    public Map<String, String> getChoices() {
        return choices;
    }
}