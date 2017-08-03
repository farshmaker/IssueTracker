package by.epamlab.bugtracker.model.enums;

public enum EPosition {

    JUNIOR(new String[]{"ROLE_USER"}),
    MIDDLE(new String[]{"ROLE_USER"}),
    SENIOR(new String[]{"ROLE_USER"}),
    TEAM_LEAD(new String[]{"ROLE_TEAM_LEAD", "ROLE_USER"}),
    PROJECT_MANAGER(new String[]{"ROLE_PROJECT_MANAGER", "ROLE_USER"}),
    ADMIN(new String[]{"ROLE_ADMIN"});

    private final String[] ROLES;

    EPosition(String[] roles) {
        this.ROLES = roles;
    }

    public String[] getRole() {
        return ROLES;
    }

}
