package com.epam.finaltask.university.controller.util;


/**
 * The type Paginator.
 */
public class Paginator {

    public static final int DEFAULT_PAGE = 1;
    public static final int RECORDS_ON_PAGE = 5;

    /**
     * Calculate pages number int.
     *
     * @param recordsNumber the records number
     * @return the number of pages int
     */
    public static int calculatePagesNumber(int recordsNumber) {
        return (int) Math.ceil(recordsNumber * 1.0 / RECORDS_ON_PAGE);
    }

    /**
     * Calculate offset int.
     *
     * @param currentPage the current page
     * @return the offset int
     */
    public static int calculateOffset(int currentPage) {
        return (currentPage - 1) * RECORDS_ON_PAGE;
    }
}
