import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class timer {
    /** value of the current time in minutes */
    private int minutes;

    /** value of the current time in seconds */
    private int seconds;

    /***************************************************************
     * Default constructor that sets a new StopWatch to 0:00.
     * Zero minutes and zero seconds.
     **************************************************************/
    public timer() {
        this.minutes = 0;
        this.seconds = 0;
    }

    /******************************************************************
     *
     *  A constructor that accepts a string as a parameter with the
     *  following format: "1:21" where 1 indicates minutes and 21
     *  indicates seconds.
     *
     *
     * @param startTime is the input string the represents
     * the starting time
     * @throws IllegalArgumentException when the input string
     * does not match the proper format (see description above)
     */

    public timer(String startTime) {
        String[] list;
        int temp;

        //checks to see if the startTime is null
        if (startTime == null)
            throw new IllegalArgumentException();

        //splits the start time into an array
        list = startTime.split(":");
        //if there is three different numbers separated by :
        if (list.length > 2) {
            temp = Integer.parseInt(list[0]);
            //if the minutes is out of range
            if (temp < 0)
                throw new IllegalArgumentException("constructor with 3 params");
            minutes = temp;

            temp = Integer.parseInt(list[1]);
            //if the seconds is out of range
            if (temp < 0 || temp > 59)
                throw new IllegalArgumentException();
            seconds = temp;

        } else if (list.length > 1) {
            temp = Integer.parseInt(list[0]);
            //if the seconds is out of range
            if (temp < 0 || temp > 59)
                throw new IllegalArgumentException();
            seconds = temp;
        }
    }
    /********************************************************************
     * Constructor that creates a Stopwatch at a specific minute and second
     *
     * @param minutes amount of minutes to set the stopwatch to
     * @param seconds amount of seconds to set the stopwatch to
     *********************************************************************/
    public timer(int minutes, int seconds) {
        //if the minutes is out of range
        if (minutes < 0)
            throw new IllegalArgumentException("constructor with 3 params");

        //if the seconds is out of range
        if (seconds < 0 || seconds > 59)
            throw new IllegalArgumentException();

        this.minutes = minutes;
        this.seconds = seconds;
    }


    /*********************************************************************
     * Increases the amount in the timer by one second each
     * time it is called
     *********************************************************************/
    public void inc() {
        if(seconds < 59) {
            seconds += 1;
        }
        //else change minutes
        else {
            minutes += 1;
            seconds = 0;
        }
    }

    /**********************************************************************
     * Changes the minutes and seconds to a string
     *
     * @return returns the created string
     **********************************************************************/
    public String toString() {
        String string = minutes + ":" +
                String.format("%02d", seconds);
        return string;
    }
}
