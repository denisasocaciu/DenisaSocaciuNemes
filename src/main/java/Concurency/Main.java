package Concurency;
import java.util.Random;

public class Main {

        public static void main(String[] args) throws InterruptedException {


            FestivalGate gate1 = new FestivalGate();
            FestivalStatisticsThread statisticsThread = new FestivalStatisticsThread(gate1);

            gate1.setClosed(false);
            statisticsThread.start();


            for (int i = 1; i <=100; i++) {
                TicketType ticketType = TicketType.values()[new Random().nextInt(TicketType.values().length)];
                Thread festivalAttendee = new FestivalAttendeeThread(ticketType, gate1);
                festivalAttendee.start();
                Thread.sleep(200);
                festivalAttendee.join();
            }


            gate1.setClosed(true);

        }
    }

