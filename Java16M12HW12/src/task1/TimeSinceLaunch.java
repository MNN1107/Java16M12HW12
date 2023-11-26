package task1;

import java.util.concurrent.TimeUnit;

public class TimeSinceLaunch {
    public static void main(String[] args){
        TimeSinceLaunch timeSinceLaunch = new TimeSinceLaunch();

        Thread timeThread = new Thread(timeSinceLaunch::displayTime);
        timeThread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread messageThread = new Thread(timeSinceLaunch::messageTime);
        messageThread.start();
    }
    private void displayTime(){
        long startTime = System.currentTimeMillis();
        while(true){
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - startTime;
            System.out.println(formatElapsedTime(elapsedTime));
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

    }
    private void messageTime(){
        while(true){
            System.out.println("5 seconds have passed");
            try{
                Thread.sleep(5000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

    }
    private String formatElapsedTime(long elapsedTime) {
        long hours = TimeUnit.MILLISECONDS.toHours(elapsedTime);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(elapsedTime) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTime) % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
    }


