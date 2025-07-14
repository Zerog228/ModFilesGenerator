package zerog.modfilesgenerator;

public enum ConsoleNotification {

    STARTED("StartedApp!"),
    SIZE_UNSELECTED("Entity size is unselected or incorrect! Used \"1\" instead!"),
    FAILED_TO_LOAD_PATH("Failed to load path!"),
    CREATED_NEW_PATH("Created new path!"),
    SAVED_TO_CONFIG("Saved to config!"),
    CREATED_NEW_LANG_FILE("Created new lang file!"),
    EMPTY();

    private String notification;

    ConsoleNotification(String notification){
        this.notification = notification;
    }

    ConsoleNotification(){
         this.notification = "Reconsider your life choices";
    }

    public String getNotification(){
        if(Controller.isNotificationDisabled(this)) return null;
        return this.notification;

    }

    public String completeNotification(String additional){
         if(getNotification() != null){
             return getNotification() + " " + additional;
         }else {
             return null;
         }
    }
}