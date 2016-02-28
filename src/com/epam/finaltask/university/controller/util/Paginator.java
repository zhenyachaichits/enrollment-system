package com.epam.finaltask.university.controller.util;

/**
 * Created by Zheny Chaichits on 28.02.2016.
 */
public class Paginator {
    public static final int DEFAULT_PAGE = 1;
    public static final int RECORDS_ON_PAGE = 5;

    public static int calculatePagesNumber(int recordsNumber) {
        return (int) Math.ceil(recordsNumber * 1.0 / RECORDS_ON_PAGE);
    }

    public static int calculateOffset(int currentPage) {
        return (currentPage - 1) * RECORDS_ON_PAGE;
    }
}
