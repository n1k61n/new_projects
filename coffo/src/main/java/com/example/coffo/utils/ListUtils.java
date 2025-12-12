package com.example.coffo.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    /**
     * Böyük listi verilmiş ölçüdə (chunkSize) kiçik listlərə ayırır.
     * Məsələn: 12 elementli listi 4 elementli 3 listə ayırır.
     */
    public static <T> List<List<T>> splitList(List<T> originalList, int chunkSize) {
        List<List<T>> groupedLists = new ArrayList<>();
        if (originalList == null || originalList.isEmpty() || chunkSize <= 0) {
            return groupedLists;
        }

        for (int i = 0; i < originalList.size(); i += chunkSize) {
            int endIndex = Math.min(i + chunkSize, originalList.size());
            // orijinal listdən i-dən (başlanğıc) endIndex-ə (son) qədər yeni bir alt-list yaradır
            List<T> chunk = originalList.subList(i, endIndex);
            groupedLists.add(chunk);
        }
        return groupedLists;
    }
}