package dat250.votingapp.service;

import dat250.votingapp.model.AppUser;

public class UserValidationResult {
    private AppUser user;
    private String message;

    public UserValidationResult(AppUser user, String message) {
        this.user = user;
        this.message = message;
    }

    public AppUser getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
