package com.tadiwa.backend.shared.utilities;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.tadiwa.backend.shared.tranferable.DtoUtil;
import com.tadiwa.backend.shared.tranferable.Transferable;

public class RestControllerUtils extends DtoUtil {

    

    /** Takes An of type `Optional<T>`. T should implement `Transferable`. If the optional is empty then a `404` reponse will be returned else the DTO of that that type T (which extends Transferable) */
    public <T> ResponseEntity<T> might404(Optional<? extends Transferable<T>> optional) {
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optional.get().toDTO());
    }


}
