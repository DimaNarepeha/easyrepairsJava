package com.softserve.demo.util;

/**
 * Created by Illia Chenchak
 */
public final class Constant {
    private Constant() {
    }

    public static final String DEFAULT_PHOTO = "nophoto.png";
    public static final Integer FEEDBACK_TIME_OUT = 72;
    public static final Integer OFFER_DATE_OUT = 8;
    public static final Integer DEFAULT_LOCATION = 1001;
    public static final String STR_ZERO = "0";
    public static final String STR_FOUR = "4";
    public static final Integer ONE = 1;
    public static final Integer TWO = 2;
    public static final String COMMA = ",";
    public static final String ORDER_NOT_FOUND = "Order with id: [%d] not found";
    public static final String CONTRACT_WAS_CREATED = "New contract was created";
    public static final String CONTRACT_WAS_UPDATED = "Contract was updated";
    public static final String CONTRACT_WAS_DELETED = "Contract was deleted";
    public static final String BY = " by %s %s";
    public static final String WITH = " with %s";
    public static final String TEXT_CREATE_CONTRACT = "Dear %s,\n" +
            "Congratulations! You have a new contract with %s. Please follow this link, to view the details, " +
            "edit, reject or sign it.\n" +
            "If you received this message by mistake, please ignore it. If you want to complain, " +
            "please contact Admin.\n" +
            "Thank you for using our services!\n" +
            "Your easyrepairs.com";
    public static final String TEXT_DELETE_CONTRACT = "Dear %s,\n" +
            "Your contract with %s has been deleted. If you want to restore it, please contact Admin.\n" +
            "Thank you for using our services!\n" +
            "Your easyrepairs.com";
    public static final Double DEFAULT_RATING = 0.;
    public static final Double DOUBLE_ZERO = 0.;
    public static final String OFFER_WAS_DELETED = "Your job offer was deleted!";
    public static final String AUTOMATICALLY_DELETED = "Due to the fact that your offer expired, it was " +
            "automatically deleted";
    public static final String RECEIVED_CONTRACT = "You received contract";
    public static final String YOU_RECEIVED_CONTRACT_BY_EMAIL = "Your contract is attached to the email.\n" +
            "\n" +
            "Thank you for using our services!\n" +
            "Your easyrepairs.com";


}
