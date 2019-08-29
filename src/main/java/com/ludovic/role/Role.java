package com.ludovic.role;

public class Role {

    /**
     * Find a role with an id
     * @param id
     * @return
     */
    public static RoleEnum getRoleById(int id) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.getId() == id) {
                return roleEnum;
            }
        }

        return RoleEnum.MJ;
    }
}
