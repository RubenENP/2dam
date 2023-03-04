package ui.main;

import jakarta.inject.Named;

public class Produces {
    @jakarta.enterprise.inject.Produces
    @Named("url")
    public String getUrl()
    {
        return "jjj";
    }

    @jakarta.enterprise.inject.Produces
    @Named("configDB")
    public String getDB()
    {
        return "jjj";
    }
}
