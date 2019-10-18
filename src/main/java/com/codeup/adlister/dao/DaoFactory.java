package com.codeup.adlister.dao;

public class DaoFactory {

    private static Ads adsDao;
    private static Users usersDao;
    private static Config config = new Config();

    // Return a Dao object that can get ads from the db
    public static Ads getAdsDao() {
        if (adsDao == null) {
            adsDao = new MySQLAdsDao(config);
        }
        return adsDao;
    }

    // Return a Dao object that can get users from the db
    public static Users getUsersDao() {
        if (usersDao == null) {
            usersDao = new MySQLUsersDao(config);
        }
        return usersDao;
    }
}
