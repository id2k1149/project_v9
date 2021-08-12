package org.id2k1149.project_v9.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "It's late to vote")
public class LateToVoteException extends RuntimeException {
    public LateToVoteException (String message) {
        super(message);
    }
}
