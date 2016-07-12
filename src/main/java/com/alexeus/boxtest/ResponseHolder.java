package com.alexeus.boxtest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by imala_000 on 12.07.2016.
 */
public class ResponseHolder {
    private Map<String, String> success;
    private List<String> error;
    /**
     * StatisticsServiceInternal is loaded on the first execution of StatisticsService.getInstance()
     * or the first access to StatisticsServiceInternal.INSTANCE, not before.
     */
    private static class ResponseHolderInternal {
        private static final ResponseHolder INSTANCE = new ResponseHolder();
    }

    /**
     * Даёт доступ к объекту, реализующему интерфейс JmxControlRegistrar
     * @return ссылку на реализацию JmxControlRegistrar
     */
    public static ResponseHolder getResponseHolder() {
        return ResponseHolderInternal.INSTANCE;
    }

    // Private constructor prevents instantiation from other classes
    private ResponseHolder() {
        success = new ConcurrentHashMap<String, String>();
        error = new ArrayList<String>();
    }

    public void addSuccess(final String code, final String state) {
        success.put(code, state);
    }

    public void addError(final String error, final String errorDescription) {
        this.error.add(error + " " + errorDescription);
    }

    public Map<String, String> getSuccess() {
        return success;
    }

    public List<String> getError() {
        return error;
    }
}
