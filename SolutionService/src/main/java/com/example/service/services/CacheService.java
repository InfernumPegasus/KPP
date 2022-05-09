package com.example.service.services;

import com.example.service.logger.MyLogger;
import com.example.service.input.InputParams;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CacheService {
    private static final Map<InputParams, Integer> solutions = new ConcurrentHashMap<>();

    public void add(@NotNull InputParams params,
                    @NotNull Integer root) {
        if (!solutions.containsKey(params)) {
            solutions.put(params, root);
            var message = """
            Value %s@root=%d added to cache!
            """.formatted(params, root);

            MyLogger.log(Level.INFO, message);
        }
    }

    public Integer find(InputParams params) {
        if (params == null) {
            MyLogger.log(Level.ERROR, "No params provided!");
            return null;
        }

        if (solutions.containsKey(params)) {
            MyLogger.log(Level.WARN, "Root for " + params + " found in cache!");
            return solutions.get(params);
        }
        MyLogger.log(Level.WARN, "Root for " + params + " was not found in cache!");
        return null;
    }

    public static Map<InputParams, Integer> getSolutions() {
        return solutions;
    }

    @PreDestroy
    void preDestroy() {
        solutions.clear();
        MyLogger.log(Level.WARN, "Cache: destroyed!");
    }
}