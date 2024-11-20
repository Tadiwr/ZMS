package com.tadiwa.backend.shared.tranferable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DtoUtil {
    
    /** Takes a `Transferable` and returns its Data Transfer Object form  */
    public static <T> T transform(Transferable<T> transferable) {
        return transferable.toDTO();
    }

    /** Takes a List of `Transferable` and returns its Data Transfer Object form  */
    public static <T> List<T> transform(List<? extends Transferable<T>> transferableList) {
        return transferableList.stream()
            .map(obj -> obj.toDTO())
            .collect(Collectors.toList())
        ;
    }

    /** Takes an Iterable of `Transferable` and returns its Data Transfer Object form  */
    public static <T> List<T> transform(Iterable<? extends Transferable<T>> transferableIterable) {
        return StreamSupport.stream(transferableIterable.spliterator(), false)
            .map(transferable -> transferable.toDTO())
            .collect(Collectors.toList());
    }

}
