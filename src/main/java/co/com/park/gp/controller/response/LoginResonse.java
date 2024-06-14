package co.com.park.gp.controller.response;

import java.util.ArrayList;

import co.com.park.gp.dto.LoginDTO;

public class LoginResonse extends Response<LoginDTO> {

    private boolean success;

    public LoginResonse() {
        setMensajes(new ArrayList<>());
        setDatos(new ArrayList<>());
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
