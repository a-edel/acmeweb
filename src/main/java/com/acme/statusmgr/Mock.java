package com.acme.statusmgr;

public class Mock implements FacadeInterface {

    @Override
    public String getServerStatus() {
        return "up";
    }

    @Override
    public int getAvailableProcessors() {
        return 4;
    }

    @Override
    public long getFreeJVMMemory() {
        return 127268272L;
    }

    @Override
    public long getTotalJVMMemory() {
        return 159383552L;
    }

    @Override
    public String getJREVersion() {
        return "15.0.2+7-27";
    }

    @Override
    public String getTempLocation() {
        return "M:\\\\AppData\\\\Local\\\\Temp";
    }
}
