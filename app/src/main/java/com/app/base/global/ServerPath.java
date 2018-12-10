package com.app.base.global;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 10:32 AM
 */
public class ServerPath {
    public static final String OAUTH_CLIENT_ID = "pivis_application_server";
    public static final String OAUTH_CLIENT_SECRET = "99B9BE34F8CFF192C728646A35034D93C2AB4346E44ECF224E88445B0F3E3100";
    public static final String GRANT_TYPE = "client_credentials";
    public static final int HTTP_CONNECTION_TIME_OUT = 10;
    public static final int HTTP_READ_TIME_OUT = 10;
    public static final int HTTP_WRITE_TIME_OUT = 10;
    public static final int OAUTH_TIMED_OUT = 500;
    public static final String SERVER_PATH = "https://pivis.online:1081/pi/";
    public static final String SERVER_PATH_OAUTH = SERVER_PATH + "oauth/token";
}
